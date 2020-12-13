package com.boss.bes.exam.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 试卷题目信息
 * @create 2020-07-12 09:24
 * @since 1.0
 */
@Data
public class ExamSubjectDTO implements Serializable {

    /**
     * 试卷试题id
     */
    private Long id;

    /**
     * 题型
     */
    private String type;

    /**
     * 分值
     */
    private BigDecimal subjectScore;

    /**
     * 正确答案
     */
    private String standardAnswer;

    /**
     * 我的答案
     */
    private String myAnswer;

}
