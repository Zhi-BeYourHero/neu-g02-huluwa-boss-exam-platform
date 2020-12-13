package com.boss.bes.system.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.DepartmentDto;
import com.boss.bes.system.dto.ResourceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_resource")
public class Resource extends BaseEntity implements Serializable {
    /**
     * 资源id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "tenant_name")
    private String tenantName;

    /**
     * 编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 顺序号
     */
    @Column(name = "order_index")
    private Integer orderIndex;

    /**
     * 父亲节点
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * URL
     */
    @Column(name = "url")
    private String url;

    /**
     * 打开图标
     */
    @Column(name = "open_img")
    private String openImg;

    /**
     * 关闭图标
     */
    @Column(name = "close_img")
    private String closeImg;

    /**
     * 资源类型
     */
    @Column(name = "resource_type")
    private Integer resourceType;

    /**
     * 叶子节点
     */
    @Column(name = "leaf")
    private Integer leaf;

    /**
     * 动作
     */
    @Column(name = "`action`")
    private Integer action;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;



    private static final long serialVersionUID = 1L;
    public Resource(ResourceDto resourceDto){
        BeanUtil.copyBeanProp(this,resourceDto);
    }
}