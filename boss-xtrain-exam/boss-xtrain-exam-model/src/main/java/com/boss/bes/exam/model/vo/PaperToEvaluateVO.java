package com.boss.bes.exam.model.vo;

import com.boss.bes.exam.model.dto.PaperSubjectToEvaluateDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 19:48
 * @since
 */
@Data
public class PaperToEvaluateVO {
    /**
     * 存储用来判断当前阅卷属于哪次考试记录的ID
     * */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long recordId;
    /**
     * 考试试卷ID
     * */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paperId;
    /**
     * 试卷名称
     * */
    private String title;
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
