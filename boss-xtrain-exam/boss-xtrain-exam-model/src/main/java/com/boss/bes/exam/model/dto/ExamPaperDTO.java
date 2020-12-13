package com.boss.bes.exam.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试试卷信息
 * @create 2020-07-12 08:59
 * @since 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamPaperDTO {

    /**
     * 试卷id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long tPId;

    /**
     * 考试标题
     */
    private String title;

    /**
     * 考试场次
     */
    private String examBatchNo;

    /**
     * 考试结束时间
     */
    private LocalDateTime endTime;

    /**
     * 考试时长
     */
    private Integer limitTime;

    /**
     * 题目列表
     */
    private List<ExamPaperSubjectDTO> subjects;
}
