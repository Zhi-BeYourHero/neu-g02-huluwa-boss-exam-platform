package com.boss.bes.exam.service.api;

import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.exam.service.api.fallback.SystemFeignServiceFallback;
import com.boss.bes.exam.service.api.model.dto.MailMessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用来调用feign远程接口的客户端
 * @create 2020-07-12 11:11
 * @since 1.0
 */
@Service
@FeignClient(name = "system-center",fallback = SystemFeignServiceFallback.class)
public interface SystemFeignService {

    /**
     * 调用系统管理服务获得模糊查询的用户ID
     * @param username 参数名为 用户名 模糊查询写法
     * @return 返回值为用户Id和用户名的map
     */
    @PostMapping("feign/getAllPublisher")
    public Map<Long, String> getAllPublisher(@RequestParam(value = "username") String username);

    /**
     * 调用系统管理微服务获得所有阅卷官
     * @return 返回值为所有阅卷官Id和阅卷官名称
     */
    @PostMapping("feign/getAllReviewer")
    public Map<Long, String> getAllReviewer();

    /**
     * 给指定的用户发送邮件
     * @param mailMessage 包含用户ID列表和邮件信息的DTO
     * */
    @PostMapping("feign/sendEmailToUsers")
    public void sendEmailToUser(@RequestBody CommonRequest<MailMessageDTO> mailMessage);

}
