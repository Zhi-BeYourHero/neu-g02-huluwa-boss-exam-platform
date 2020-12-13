package com.boss.bes.exam.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 试卷试题信息
 * @create 2020-07-18 21:26
 * @since 1.0
 */
@Data
public class ExamPaperSubjectDTO {
    /**
     * 试卷试题id
     */
    private Long id;

    /**
     * 题目
     */
    private String subject;

    /**
     * 题目类别
     */
    private String category;

    /**
     * 题型
     */
    private String subjectType;

    /**
     * 难度
     */
    private BigDecimal difficult;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 多选题列表
     */
    private List<String> list = new ArrayList<>();

    /**
     * 正确答案
     */
    private String answer;

    /**
     * 我的答案
     */
    private String myAnswer;

    /**
     * 题目选项
     */
    private List<String> options;
}
