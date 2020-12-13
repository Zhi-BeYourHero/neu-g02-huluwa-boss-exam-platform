package com.boss.bes.exam.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试信息
 * @create 2020-07-18 21:39
 * @since 1.0
 */
@Data
public class ExamInfoDTO {
    /**
     * 试卷ID
     */
    private Long tPId;

    /**
     * 考试标题
     */
    private String title;

    /**
     * 考试场次
     */
    private String examBatchNo;

    /**
     * 考试结束时间
     */
    private LocalDateTime endTime;

    /**
     * 考试时长
     */
    private Integer limitTime;
}
