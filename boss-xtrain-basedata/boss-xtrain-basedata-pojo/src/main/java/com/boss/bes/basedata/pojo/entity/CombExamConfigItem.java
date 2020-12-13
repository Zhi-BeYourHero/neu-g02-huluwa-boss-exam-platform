package com.boss.bes.basedata.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;

/**
 * 组卷配置明细
 */
@Data
@Table(name = "t_comb_exam_config_item")
public class CombExamConfigItem implements Serializable {
    /**
     * 组卷配置明细项ID
     */
    @Id
    @Column(name = "comb_exam_config_item_id")
    private Long combExamConfigItemId;

    /**
     * 题型ID
     */
    @Column(name = "subject_type_id")
    private Long subjectTypeId;

    /**
     * 题目类别ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 组卷配置ID
     */
    @Column(name = "comb_exam_id")
    private Long combExamId;

    /**
     * 数量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 难度
     */
    @Column(name = "difficult")
    private Long difficult;

    /**
     * 分值
     */
    @Column(name = "score")
    private BigDecimal score;

    private static final long serialVersionUID = 1L;
}