package com.boss.bes.system.dto;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @description 组织机构的dto类
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrganizationDto extends BaseDTO implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
