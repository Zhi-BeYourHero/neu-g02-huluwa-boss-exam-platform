package com.boss.bes.basedata.pojo.dto.combexamconfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class CombExamConfigItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private Long combExamConfigItemId;
    /**
     * 题型名称
     */
    private String subjectTypeName;
    /**
     * 组卷配置ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long combExamId;
    /**
     * 题目类别名称
     */
    private String categoryName;
    /**
     * 难度ID
     */
    private Long difficult;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 分值
     */
    private BigDecimal score;
}
