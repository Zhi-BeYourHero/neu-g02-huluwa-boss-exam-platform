package com.boss.bes.exam.model.po;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 16:18
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_exam_answer_record")
public class ExamAnswerRecord extends BaseEntity {
    /**
     * 答案明细ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 试卷试题ID
     */
    @Column(name = "t_p_id")
    private Long tPId;

    /**
     * 考试记录ID
     */
    @Column(name = "t_r_id")
    private Long tRId;

    /**
     * 我的答案
     */
    @Column(name = "my_answer")
    private String myAnswer;

    /**
     * 标准答案
     */
    @Column(name = "standard_answer")
    private String standardAnswer;

    /**
     * 分值
     */
    @Column(name = "subject_score")
    private BigDecimal subjectScore;

    /**
     * 得分
     */
    @Column(name = "score")
    private BigDecimal score;

    /**
     * 评价
     */
    @Column(name = "evaluate")
    private String evaluate;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 机构ID
     */
    @Column(name = "org_id")
    private Long orgId;
}