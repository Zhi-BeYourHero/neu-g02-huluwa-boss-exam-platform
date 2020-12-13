package com.boss.bes.system.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 资源和角色多表查询的Dto类
 * @create 2020-07-20 01:31
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResourceDto implements Serializable {

    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long resourceId;

    private static final long serialVersionUID = 1L;
}