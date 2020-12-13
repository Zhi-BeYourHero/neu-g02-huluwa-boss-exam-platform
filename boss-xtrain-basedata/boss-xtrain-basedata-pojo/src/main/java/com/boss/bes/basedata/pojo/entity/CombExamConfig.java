package com.boss.bes.basedata.pojo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 组卷配置
 */
@Data
@Table(name = "t_comb_exam_config")
public class CombExamConfig implements Serializable {
    /**
     * 组卷配置ID
     */
    @Id
    @Column(name = "comb_exam_id")
    private Long combExamId;

    /**
     * 配置名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 标记位
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

    private static final long serialVersionUID = 1L;
}