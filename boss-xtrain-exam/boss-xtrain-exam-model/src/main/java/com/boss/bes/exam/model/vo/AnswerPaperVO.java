package com.boss.bes.exam.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 答卷查询结果
 * @create 2020-07-10 17:32
 * @since 1.0
 */
@Data
public class AnswerPaperVO {

    /**
     * 考试标题
     */
    private String title;

    /**
     * 考试场次
     */
    private String examBatchNo;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 考试截止时间
     */
    private LocalDateTime endTime;

    /**
     * 开始考试时间
     */
    private LocalDateTime actualStartTime;

    /**
     * 交卷时间
     */
    private LocalDateTime actualEndTime;

    /**
     * 客观题得分
     */
    private BigDecimal objectiveSubjectScore;

    /**
     * 主观题得分
     */
    private BigDecimal subjectiveSubjectScore;

    /**
     * 总分
     */
    private BigDecimal score;

    /**
     * 系统评价(能力标签)
     */
    private String systemEvaluate;
}
