package com.boss.bes.exam.model.dto;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 17:16
 * @since 1.0
 */
@Data
public class ExamAnswerRecordDTO {
    /**
     * 答案明细ID
     */
    private Long id;

    /**
     * 试卷试题ID
     */
    private Long tPId;

    /**
     * 考试记录ID
     */
    private Long tRId;

    /**
     * 我的答案
     */
    private String myAnswer;

    /**
     * 标准答案
     */
    private String standardAnswer;

    /**
     * 分值
     */
    private BigDecimal subjectScore;

    /**
     * 得分
     */
    private BigDecimal score;
    /**
     * 评价
     */
    private String evaluate;
}
