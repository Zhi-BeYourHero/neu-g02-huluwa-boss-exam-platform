package com.boss.bes.system.vo;


import boss.xtrain.core.data.convention.base.vo.BaseVO;

import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @description 部门的VO类
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DepartmentVo extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 部门id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 部门编号
     */
    private String code;

    /**
     * 部门级别
     */
    private String level;

    /**
     * 上级部门
     */
    private Long parentId;

    /**
     * 负责人
     */
    private String master;

    /**
     * 部门描述
     */
    private String descript;
    public DepartmentVo(Department department){
        BeanUtil.copyBeanProp(this,department);
    }
}
