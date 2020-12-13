package com.boss.bes.exam.model.query;

import boss.xtrain.core.data.convention.base.dto.BaseQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 13:08
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamRecordQuery extends BaseQueryDTO {

    /**
     * 阅卷人ID
     * */
    @NotNull(message = "阅卷人ID不能为空")
    private Long reviewerId;

    /**
     * 交卷开始时间
     * */
    private LocalDateTime startTime;

    /**
     * 交卷结束结束时间
     * */
    private LocalDateTime endTime;

    /**
     * 考试场次号码
     * */
    private String batchNumber;
}
