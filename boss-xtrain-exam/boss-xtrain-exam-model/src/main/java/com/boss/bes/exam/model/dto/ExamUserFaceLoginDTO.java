package com.boss.bes.exam.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试人员通过人脸识别登录的DTO
 * @create 2020-07-09 22:22
 * @since 1.0
 */
@Data
public class ExamUserFaceLoginDTO {

    @NotNull(message = "人脸照片不能为空")
    private String photoUrl;

    private String photoName;
}
