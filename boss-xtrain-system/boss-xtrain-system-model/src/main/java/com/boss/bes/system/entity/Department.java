package com.boss.bes.system.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.CompanyDto;
import com.boss.bes.system.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_department")
public class Department extends BaseEntity implements Serializable {
    /**
     * 部门id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 公司id
     */
    @Column(name = "t_c_id")
    private Long companyId;

    /**
     * 部门名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 助记码
     */
    @Column(name = "mnemonic_code")
    private String mnemonicCode;

    /**
     * 部门编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 部门级别
     */
    @Column(name = "`level`")
    private String level;

    /**
     * 上级部门
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 负责人
     */
    @Column(name = "master")
    private String master;

    /**
     * 部门描述
     */
    @Column(name = "descript")
    private String descript;



    private static final long serialVersionUID = 1L;
    public Department(DepartmentDto departmentDto){
        BeanUtil.copyBeanProp(this,departmentDto);
    }
}