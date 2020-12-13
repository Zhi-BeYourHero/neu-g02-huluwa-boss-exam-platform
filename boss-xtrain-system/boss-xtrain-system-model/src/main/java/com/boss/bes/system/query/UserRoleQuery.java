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
 * @description 用户和角色的查询类
 * @create 2020-07-20 01:25
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleQuery extends BaseQueryDTO implements Serializable {
    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    private static final long serialVersionUID = 1L;
}