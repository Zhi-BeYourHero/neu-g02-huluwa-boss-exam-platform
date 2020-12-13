package com.boss.bes.exam.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试试题,用来放置评卷时用到的考试试题
 * @create 2020-07-11 16:32
 * @since 1.0
 */
@Data
public class PaperSubjectToEvaluateDTO {

    /**
     * 题目 题目内容
     * */
    private String subject;
    /**
     * 试题分数
     */
    private BigDecimal score;
    /**
     * 试题难度
     * */
    private String difficult;
    /**
     * 题型
     * */
    private String subjectType;

    /**
     * 试题答案
     * */
    private List<SubjectAnswerDTO> answers;

    /**
     * 放置评价信息
     * 试题ID
     * */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 我的答案
     */

    private String myAnswer;

    /**
     * 标准答案
     */
    private String standardAnswer;

    /**
     * 所属考试记录id
     * */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long recordId;
    /**
     * 得分
     */
    private BigDecimal myScore;

    /**
     * 评价
     */
    private String evaluate;
}
