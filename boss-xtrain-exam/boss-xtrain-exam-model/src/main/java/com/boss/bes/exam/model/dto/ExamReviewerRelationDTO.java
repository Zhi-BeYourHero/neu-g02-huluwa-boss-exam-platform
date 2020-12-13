package com.boss.bes.exam.model.dto;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-09 15:03
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamReviewerRelationDTO extends BaseDTO {
    /**
     * 考试ID
     * */
    private Long examId;
    /**
     * 阅卷官ID
     * */
    private Long reviewer;
}
