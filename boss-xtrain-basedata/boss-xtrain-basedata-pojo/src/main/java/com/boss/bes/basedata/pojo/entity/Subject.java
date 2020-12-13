package com.boss.bes.basedata.pojo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 题目
 */
@Data
@Table(name = "t_subject")
public class Subject implements Serializable {
    /**
     * 题目ID
     */
    @Id
    @Column(name = "subject_id")
    private Long subjectId;

    /**
     * 题型ID
     */
    @Column(name = "subject_type_id")
    private Long subjectTypeId;

    /**
     * 题目类别ID
     */
    @Column(name = "catetory_id")
    private Long catetoryId;

    /**
     * 题目
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 图片路径
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 状态位
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 组织机构ID
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

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

    /**
     * 保留字段1
     */
    @Column(name = "field1")
    private String field1;

    /**
     * 保留字段2
     */
    @Column(name = "field2")
    private String field2;

    /**
     * 保留字段3
     */
    @Column(name = "field3")
    private String field3;

    private static final long serialVersionUID = 1L;
}