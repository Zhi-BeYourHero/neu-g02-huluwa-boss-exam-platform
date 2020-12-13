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
import java.time.LocalDateTime;
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
@Table(name = "t_exam_record")
public class ExamRecord extends BaseEntity {
    /**
     * 考试记录ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 考试发布记录ID
     */
    @Column(name = "t_p_id")
    private Long tPId;

    /**
     * 考试人员ID
     */
    @Column(name = "t_u_id")
    private Long tUId;

    /**
     * 阅卷官ID
     */
    @Column(name = "t_y_id")
    private Long tYId;

    /**
     * 计划开始时间
     */
    @Column(name = "plan_start_time")
    private LocalDateTime planStartTime;

    /**
     * 计划结束时间
     */
    @Column(name = "plan_end_time")
    private LocalDateTime planEndTime;

    /**
     * 实际开始时间
     */
    @Column(name = "actual_start_time")
    private LocalDateTime actualStartTime;

    /**
     * 实际结束时间
     */
    @Column(name = "actual_end_time")
    private LocalDateTime actualEndTime;

    /**
     * 客观题得分
     */
    @Column(name = "objective_subject_score")
    private BigDecimal objectiveSubjectScore;

    /**
     * 主观题得分
     */
    @Column(name = "subjective_subject_score")
    private BigDecimal subjectiveSubjectScore;

    /**
     * 阅卷分配时间
     */
    @Column(name = "marking_assign_time")
    private LocalDateTime markingAssignTime;

    /**
     * 总分
     */
    @Column(name = "score")
    private BigDecimal score;

    /**
     * 系统评价(能力标签)
     */
    @Column(name = "system_evaluate")
    private String systemEvaluate;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;
}