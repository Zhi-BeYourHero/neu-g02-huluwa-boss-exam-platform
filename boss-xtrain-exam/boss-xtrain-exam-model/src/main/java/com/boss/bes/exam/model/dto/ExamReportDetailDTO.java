package com.boss.bes.exam.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试报表详细数据传输对象
 * @create 2020-07-11 09:16
 * @since 1.0
 */
@Data
public class ExamReportDetailDTO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0代表女，1代表男）
     */
    private Boolean sex;

    /**
     * 考试标题
     */
    private String title;

    /**
     * 主观题得分
     */
    private BigDecimal subjectiveSubjectScore;

    /**
     * 客观题得分
     */
    private BigDecimal objectiveSubjectScore;

    /**
     * 总分
     */
    private BigDecimal score;

    /**
     * 排名
     */
    private Long ranking;

    /**
     * 考试耗时
     */
    private Long timeConsuming;

    /**
     * 系统评价(能力标签)
     */
    private String systemEvaluate;
}
