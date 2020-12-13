package com.boss.bes.exam.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Column;


/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-12 09:32
 * @since 考试人员信息
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamUserInfoDTO {

    /**
     * 考试人员ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 发布考试id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long pId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0代表女，1代表男）
     */
    private Boolean sex;

    /**
     * 毕业院校
     */
    private String university;

    /**
     * 专业
     */
    private String major;

    /**
     * 照片URL
     */
    private String photoUrl;

    /**
     * 是否登录成功
     */
    private Boolean flag;
}
