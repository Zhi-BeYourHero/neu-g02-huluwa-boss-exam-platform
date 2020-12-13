package com.boss.bes.system.vo;

import boss.xtrain.core.data.convention.base.vo.BaseVO;

import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.entity.Company;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.io.Serializable;


/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @description 公司的VO类
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompanyVo extends BaseVO implements Serializable{
    @JsonSerialize(using = ToStringSerializer.class)
    /**
     * 公司id
     */
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    /**
     * 组织机构id
     */
    private Long organizationId;

    /**
     * 公司名
     */
    private String name;

    /**
     * 公司编号
     */
    private String code;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 法人
     */
    private String master;

    /**
     * 税号
     */
    private String tax;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网址
     */
    private String website;
    //private static final long serialVersionUID = 1L;
    public CompanyVo(Company company){
        BeanUtil.copyBeanProp(this,company);
    }
}
