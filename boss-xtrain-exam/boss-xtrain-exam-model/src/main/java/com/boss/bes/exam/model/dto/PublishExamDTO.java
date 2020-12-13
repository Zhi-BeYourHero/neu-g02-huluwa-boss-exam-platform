package com.boss.bes.exam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 15:57
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PublishExamDTO {
    /**
     * 发布考试记录ID
     * */
    @NotNull(message = "发布考试ID不能为空")
    private Long id;

    /**
     * 发布考试记录的version
     * */
    @NotNull(message = "考试信息版本不能为空")
    private Long version;
}
