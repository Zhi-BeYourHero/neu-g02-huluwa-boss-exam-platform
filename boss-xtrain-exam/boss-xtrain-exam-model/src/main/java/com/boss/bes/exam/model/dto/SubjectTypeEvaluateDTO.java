package com.boss.bes.exam.model.dto;

import lombok.Data;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用来请求分题答卷的DTO
 * @create 2020-07-11 20:07
 * @since 1.0
 */
@Data
public class SubjectTypeEvaluateDTO {
    /**
     * 按理说应该有
     * 阅卷人ID
     *
     * 试题类型
     * */
    private Long reviewerId;

    private String category;
}
