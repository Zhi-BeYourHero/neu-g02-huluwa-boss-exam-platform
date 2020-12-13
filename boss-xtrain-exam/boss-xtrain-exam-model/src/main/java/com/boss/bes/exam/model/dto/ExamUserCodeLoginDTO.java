package com.boss.bes.exam.model.dto;

import com.boss.bes.exam.validator.Mobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试人员通过手机验证码登录的DTO
 * @create 2020-07-09 22:21
 * @since 1.0
 */
@Data
public class ExamUserCodeLoginDTO {

    @NotNull(message = "手机号不能为空")
    @Mobile
    private String mobile;

    /**
     * 密码
     */
    @NotNull(message = "验证码不能为空")
    @Length(min = 6, max = 6, message = "验证码的长度为6位")
    private String code;
}
