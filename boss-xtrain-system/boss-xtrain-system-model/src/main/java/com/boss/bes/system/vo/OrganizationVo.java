package com.boss.bes.system.vo;


import boss.xtrain.core.data.convention.base.vo.BaseVO;

import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.entity.Organization;
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
 * @description 组织机构的VO类
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrganizationVo extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 组织机构id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 组织名
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 法人
     */
    private String master;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    public OrganizationVo(Organization organization){
        BeanUtil.copyBeanProp(this,organization);
    }
}
