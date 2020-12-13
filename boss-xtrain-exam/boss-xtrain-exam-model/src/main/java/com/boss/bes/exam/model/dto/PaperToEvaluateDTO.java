package com.boss.bes.exam.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 返回考试详细记录的
 * @create 2020-07-11 15:46
 * @since 1.0
 */
@Data
public class PaperToEvaluateDTO {
    /**
     * 存储用来判断当前阅卷属于哪次考试记录的ID
     * */
    private Long recordId;
    /**
     * 考试试卷ID
     * */
    private Long paperId;
    /**
     * 试卷名称
     * */
    private String title;
    /**
     * 试卷类型
     * */
    private String paperType;
    /**
     * 试卷难度
     */
    private Long difficulty;
    /**
     * 试卷总分
     */
    private BigDecimal score;
    /**
     * 试卷描述
     */
    private String discript;

    /**
     * 主观题
     * */
    private List<PaperSubjectToEvaluateDTO> subjectDTOS;
    /**
     * 客观题
     * */
    private List<PaperSubjectToEvaluateDTO> objectDTOs;
}
