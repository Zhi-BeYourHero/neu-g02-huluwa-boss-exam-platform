package com.boss.bes.basedata.pojo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 题型
 */
@Data
@Table(name = "t_subject_type")
public class SubjectType implements Serializable {
    /**
     * 题型ID
     */
    @Id
    @Column(name = "subject_type_id")
    private Long subjectTypeId;

    /**
     * 题型名称
     */
    @Column(name = "subject_type_name")
    private String subjectTypeName;

    /**
     * 属性列
     */
    @Column(name = "`attribute`")
    private String attribute;

    /**
     * 状态位
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 机构ID
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Long createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private Long updatedBy;

    /**
     * 修改时间
     */
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    /**
     * 版本
     */
    @Column(name = "version")
    private Long version;

    private static final long serialVersionUID = 1L;
}