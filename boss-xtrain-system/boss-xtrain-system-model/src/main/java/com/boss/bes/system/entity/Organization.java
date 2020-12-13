package com.boss.bes.system.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.OrganizationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_organization")
public class Organization extends BaseEntity implements Serializable {
    /**
     * 组织机构id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 组织名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 编码
     */
    @Column(name = "code")
    private String code;

    /**
     * 法人
     */
    @Column(name = "master")
    private String master;

    /**
     * 电话
     */
    @Column(name = "tel")
    private String tel;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;



    private static final long serialVersionUID = 1L;
    public Organization(OrganizationDto organizationDto){
        BeanUtil.copyBeanProp(this,organizationDto);
    }
}