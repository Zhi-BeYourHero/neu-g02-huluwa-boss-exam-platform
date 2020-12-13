package com.boss.bes.exam.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用来提交评卷结果的DTO
 * @create 2020-07-12 08:31
 * @since 1.0
 */
@Data
public class EvaluateSubmitDTO {
    /**
     * 评卷所属考试记录ID
     */
    @NotNull(message = "考试记录ID不能为空")
    private Long examRecordId;

    /**
     * 主观题分数
     */
    @NotNull(message = "主观题分数不能为空")
    private BigDecimal subjectiveScore;

    /**
     * 评卷总分
     */
    @NotNull(message = "总分不能为空")
    private BigDecimal score;

    /**
     * 评价信息
     */
    private String markingEvaluate;

    /**
     * 批卷记录的列表
     */
    private List<EvaluateDTO> evaluateList;
}
