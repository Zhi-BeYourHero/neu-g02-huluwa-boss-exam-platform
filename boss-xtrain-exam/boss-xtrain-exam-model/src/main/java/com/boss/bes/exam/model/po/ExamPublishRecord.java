package com.boss.bes.exam.model.po;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "t_exam_publish_record")
public class ExamPublishRecord extends BaseEntity {
    /**
     * 考试发布记录ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 试卷ID
     */
    @Column(name = "t_p_id")
    private Long tPId;

    /**
     * 考试标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 考试场次
     */
    @Column(name = "exam_batch_no")
    private String examBatchNo;

    /**
     * 发布人
     */
    @Column(name = "publisher")
    private Long publisher;

    /**
     * 考试开始时间
     */
    @Column(name = "start_time")
    private LocalDateTime startTime;

    /**
     * 考试结束时间
     */
    @Column(name = "end_time")
    private LocalDateTime endTime;

    /**
     * 计划人数
     */
    @Column(name = "plan_people_num")
    private Integer planPeopleNum;

    /**
     * 考试时长
     */
    @Column(name = "limit_time")
    private Integer limitTime;

    /**
     * 考试说明
     */
    @Column(name = "descript")
    private String descript;

    /**
     * 阅卷方式（试卷归类，题型归类）
     */
    @Column(name = "marking_mode")
    private Long markingMode;

    /**
     * 阅卷停止时间
     */
    @Column(name = "marking_stop_time")
    private LocalDateTime markingStopTime;

    /**
     * 二维码链接
     */
    @Column(name = "qrcode_url")
    private String qrcodeUrl;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 预留字段1
     */
    @Column(name = "field1")
    private String field1;

    /**
     * 预留字段2
     */
    @Column(name = "field2")
    private String field2;
}