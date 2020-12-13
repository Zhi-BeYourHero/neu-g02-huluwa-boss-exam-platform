package com.boss.bes.exam.model.vo;

import com.boss.bes.exam.model.dto.SubjectAnswerDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 21:07
 * @since
 */
@Data
public class PaperSubjectVO {
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
    /**
     * 试题答案
     * */
    private List<SubjectAnswerDTO> answers;
}
