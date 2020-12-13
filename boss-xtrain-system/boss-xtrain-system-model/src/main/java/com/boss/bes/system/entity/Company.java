package com.boss.bes.system.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_company")
public class Company extends BaseEntity  implements Serializable{
    /**
     * 公司id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 组织机构id
     */
    @Column(name = "t_o_id")
    private Long organizationId;

    /**
     * 公司名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 公司编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 助记码
     */
    @Column(name = "mnemonic_code")
    private String mnemonicCode;

    /**
     * 法人
     */
    @Column(name = "master")
    private String master;

    /**
     * 税号
     */
    @Column(name = "tax")
    private String tax;

    /**
     * 传真
     */
    @Column(name = "fax")
    private String fax;

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

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 网址
     */
    @Column(name = "website")
    private String website;



    //private static final long serialVersionUID = 1L;
    public Company(CompanyDto companyDto){
        BeanUtil.copyBeanProp(this,companyDto);
    }
}