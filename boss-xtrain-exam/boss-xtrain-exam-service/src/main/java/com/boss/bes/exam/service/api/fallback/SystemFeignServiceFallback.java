package com.boss.bes.exam.service.api.fallback;

import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.exam.service.api.SystemFeignService;
import com.boss.bes.exam.service.api.model.dto.MailMessageDTO;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-12 14:40
 *
 * @since 1.0
 */
@Component
public class SystemFeignServiceFallback implements SystemFeignService {
    /**
     * 调用系统管理服务获得模糊查询的用户ID
     *
     * @param username 参数名为 用户名 模糊查询写法
     * @return 返回值为用户Id和用户名的map
     */
    @Override
    public Map<Long, String> getAllPublisher(String username) {
        throw new ExamException(ExamExceptionCode.CALL_SYSTEM_SERVICE_FAILED.getCode(),
                ExamExceptionCode.CALL_SYSTEM_SERVICE_FAILED.getMessage());
    }

    /**
     * 调用系统管理微服务获得所有阅卷官
     *
     * @return 返回值为所有阅卷官Id和阅卷官名称
     */
    @Override
    public Map<Long, String> getAllReviewer() {
        throw new ExamException(ExamExceptionCode.CALL_SYSTEM_SERVICE_FAILED.getCode(),
                ExamExceptionCode.CALL_SYSTEM_SERVICE_FAILED.getMessage());
    }

    /**
     * 给指定的用户发送邮件
     *
     * @param mailMessage 包含用户ID列表和邮件信息的DTO
     */
    @Override
    public void sendEmailToUser(CommonRequest<MailMessageDTO> mailMessage) {
        throw new ExamException(ExamExceptionCode.CALL_SYSTEM_SERVICE_FAILED.getCode(),
                ExamExceptionCode.CALL_SYSTEM_SERVICE_FAILED.getMessage());
    }
}
