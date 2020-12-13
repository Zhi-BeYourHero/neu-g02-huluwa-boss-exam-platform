package com.boss.bes.exam.model.query;

import boss.xtrain.core.data.convention.base.dto.BaseQueryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试报表查询
 * @create 2020-07-10 22:47
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExamReportQuery extends BaseQueryDTO {
    /**
     * 考试标题
     */
    private String title;

    /**
     * 考试场次
     */
    private String examBatchNo;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 考试开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 考试结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
