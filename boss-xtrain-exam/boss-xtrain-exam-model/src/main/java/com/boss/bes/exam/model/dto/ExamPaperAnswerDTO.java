package com.boss.bes.exam.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试人员的试卷信息
 * @create 2020-07-12 09:08
 * @since 1.0
 */
@Data
public class ExamPaperAnswerDTO {

    /**
     * 试卷id
     */
    private Long pid;

    /**
     * 考试人员id
     */
    private Long id;

    /**
     * 发布考试记录id
     */
    private Long rid;

    /**
     * 开始答卷时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 交卷时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 试卷题目信息
     */
    private List<ExamSubjectDTO> subjects;
}
