package com.boss.bes.exam.model.dto;

import com.boss.bes.exam.validator.Mobile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 13:51
 * @since 1.0
 */
@Data
public class ExamUserDTO {

    private Long id;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Mobile
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    @NotNull
    @Length(min = 8, max = 12, message = "密码长度应为8~12位")
    private String password;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 性别（0代表女，1代表男）
     */
    @NotNull(message = "性别不能为空")
    private Boolean sex;

    /**
     * 毕业院校
     */
    @NotNull(message = "毕业院校不能为空")
    private String university;

    /**
     * 学历
     */
    @NotNull(message = "学历不能为空")
    private String educational;

    /**
     * 英语水平
     */
    @NotNull(message = "英语水平不能为空")
    private String englishLevel;

    /**
     * 专业
     */
    @NotNull(message = "专业不能为空")
    private String major;

    /**
     * 毕业时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "毕业时间不能为空")
    private LocalDate graduateTime;

    /**
     * 在校职务说明
     */
    private String schoolPosition;

    /**
     * 实习工作经历
     */
    private String internshipInfo;

    /**
     * 获奖情况
     */
    private String prizeInfo;

    /**
     * 照片URL
     */
    @NotNull(message = "照片不能为空")
    private String photoUrl;

    /**
     * 备注
     */
    private String remark;
}
