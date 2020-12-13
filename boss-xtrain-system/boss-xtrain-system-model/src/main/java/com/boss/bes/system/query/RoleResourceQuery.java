package com.boss.bes.system.query;

import boss.xtrain.core.data.convention.base.dto.BaseQueryDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色资源Query类
 * @create 2020-07-20 01:29
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleResourceQuery extends BaseQueryDTO implements Serializable {
    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 角色id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long resourceId;

    private static final long serialVersionUID = 1L;
}