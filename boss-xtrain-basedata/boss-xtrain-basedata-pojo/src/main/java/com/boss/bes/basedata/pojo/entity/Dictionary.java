package com.boss.bes.basedata.pojo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 数据字典
 */
@Data
@Table(name = "t_dictionary")
public class Dictionary implements Serializable {
    /**
     * 数据字典id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 组织机构id
     */
    @Column(name = "t_o_id")
    private Long tOId;

    /**
     * 字典名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 字典类型
     */
    @Column(name = "category")
    private String category;

    /**
     * 参数值
     */
    @Column(name = "`value`")
    private String value;

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
    private Long updatedTime;

    /**
     * 版本
     */
    @Column(name = "version")
    private Long version;

    private static final long serialVersionUID = 1L;
}