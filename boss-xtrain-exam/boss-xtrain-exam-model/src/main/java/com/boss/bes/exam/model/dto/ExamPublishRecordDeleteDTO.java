package com.boss.bes.exam.model.dto;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用来删除考试记录的DTO
 * @create 2020-07-07 15:26
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamPublishRecordDeleteDTO {
    /**
     * 删除考试记录ID
     * */
    @NotNull(message = "必须传入考试发布记录ID")
    private Long id;
    /**
     * 删除考试记录状态
    * */
    @NotNull(message = "必须传入考试发布记录version")
    private Long version;
}
