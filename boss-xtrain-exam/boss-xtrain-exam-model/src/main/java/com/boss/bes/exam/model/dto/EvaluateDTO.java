package com.boss.bes.exam.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 18:25
 * @since
 */
@Data
public class EvaluateDTO implements Serializable {
    /**
     * 该部分数据仅仅是为了便于存储
     * 评价所属试题id
     * */
    @NotNull(message = "评价所属试题ID不能为空")
    private Long subjectId;

    /**
     * 评价所属的考试记录id
     * */
    @NotNull(message = "考试记录ID不能为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long recordId;

    /**
     * 下部分是真正的评价信息
     * 得分
     */
    private BigDecimal score;

    /**
     * 评价
     */
    private String evaluate;
}
