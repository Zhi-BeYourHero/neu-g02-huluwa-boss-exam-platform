package com.boss.bes.system.dto;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的DTO类
 * @create 2020-07-10 14:50
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends BaseDTO implements Serializable {

    /**
     * 角色id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 公司id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    /**
     * 组织机构id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long organizationId;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色代码
     */
    private String code;

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String remark;
}