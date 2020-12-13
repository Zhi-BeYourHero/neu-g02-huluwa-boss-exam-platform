package com.boss.bes.basedata.pojo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 题目类别
 */
@Data
@Table(name = "t_category")
public class Category implements Serializable {
    /**
     * 题目类别ID
     */
    @Id
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 类别名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 父类别ID
     */
    @Column(name = "parent_id")
    private Long parentId;

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