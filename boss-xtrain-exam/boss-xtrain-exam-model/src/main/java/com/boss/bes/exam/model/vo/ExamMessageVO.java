package com.boss.bes.exam.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试信息视图对象
 * @create 2020-07-11 21:43
 * @since 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamMessageVO {

    /**
     * 考试ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 考试名称
     */
    private String title;

    /**
     * 考试批次号
     */
    private String examBatchNo;

    /**
     * 考试说明
     */
    private String descript;

    /**
     * 考试时长
     */
    private Integer limitTime;

    /**
     * 考试开始时间
     */
    private LocalDateTime startTime;

    /**
     * 考试结束时间
     */
    private LocalDateTime endTime;

    /**
     * 计划人数
     */
    private Integer planPeopleNum;

}
