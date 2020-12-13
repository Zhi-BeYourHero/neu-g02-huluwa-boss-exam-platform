package com.boss.bes.exam.model.dto;

import com.boss.bes.exam.validator.Mobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试人员通过密码登录的DTO
 * @create 2020-07-09 22:18
 * @since 1.0
 */
@Data
public class ExamUserPwdLoginDTO {

    @NotNull(message = "手机号不能为空")
    @Mobile
    private String mobile;

    /**
     * 密码
     */
    @NotNull
    @Length(min = 8, max = 12, message = "密码长度应为8~12位")
    private String password;

}
