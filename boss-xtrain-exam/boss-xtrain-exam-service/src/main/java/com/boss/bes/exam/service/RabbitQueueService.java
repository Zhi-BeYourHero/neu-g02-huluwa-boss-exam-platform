package com.boss.bes.exam.service;

import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.exam.dao.ExamPublishRecordDao;
import com.boss.bes.exam.model.dto.RabbitMessageDTO;
import com.boss.bes.exam.model.po.ExamPublishRecord;
import com.boss.bes.exam.model.query.ExamPublishRecordQueryWithId;
import com.boss.bes.exam.service.api.SystemFeignService;
import com.boss.bes.exam.service.api.model.dto.MailMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 08:59
 * @since 1.0
 */
@Slf4j
@Service
public class RabbitQueueService {
    @Value("${rabbitmq.delay.exchange}")
    private String delayExchange;
    @Value("${rabbitmq.delay.queue}")
    private String delayQueue;
    @Value("${rabbitmq.delay.key}")
    private String delayKey;
    @Resource
    ExamPublishRecordService examPublishRecordService;
    @Resource
    ExamPublishRecordDao examPublishRecordDao;
    @Resource
    ExamRecordService examRecordService;
    @Resource
    SystemFeignService systemFeignService;
    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * */
    public void sendMessage(RabbitMessageDTO<?> messageDTO){
        //第一个参数是前面RabbitMqConfig的交换机名称 第二个参数的路由名称 第三个参数是传递的参数 第四个参数是配置属性
        rabbitTemplate.convertAndSend(
                delayExchange,
                delayKey,
                messageDTO,
                message -> {
                    //配置消息的过期时间
                    message.getMessageProperties().setDelay(messageDTO.getDelayTime());
                    return message;
                }
        );
    }

    /**
     * 监听消息队列函数
     * */
    @RabbitListener(queues = "delay_queue")
    public void consumeMessage(RabbitMessageDTO<?> rabbitMessageDTO) throws IOException {

        if(rabbitMessageDTO.getMessageType() == 1){
            Long id = (Long) rabbitMessageDTO.getData();
            int examNumber = examPublishRecordService.setReviewTimeOut(id);
            if(examNumber != 1){
                log.info("修改失败");
            }
            //将该考试发布记录下的所有考试记录均修改为过期
            examRecordService.setExamRecordReviewTimeout(id);
            log.info("当前时间：{} 将考试：{}以及{}个考试记录，修改为过期状态",LocalDateTime.now(),id,examNumber);
        }
        //给阅卷官发消息
        if(rabbitMessageDTO.getMessageType() == 2){
            Long id = (Long) rabbitMessageDTO.getData();
            //首先通过ID查询阅卷官
            List<Long> reviews = examPublishRecordDao.queryAllReviewer(id);
            //封装消息到DTO中调用系统服务发送邮件
            MailMessageDTO mailMessageDTO = new MailMessageDTO();
            mailMessageDTO.setContent("您有新的试卷需要进行评阅 点击链接进入评卷系统:https://www.baidu.com/");
            mailMessageDTO.setTitle("博思阅卷系统通知");
            CommonRequest<MailMessageDTO> commonRequest = new CommonRequest<>();
            commonRequest.setBody(mailMessageDTO);
            //TODO 等待测试
            systemFeignService.sendEmailToUser(commonRequest);
        }
    }
}
