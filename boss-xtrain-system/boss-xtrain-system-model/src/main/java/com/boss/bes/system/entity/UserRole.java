package com.boss.bes.system.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用户和角色多表查询实体类
 * @create 2020-07-07
 * @since 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user_role")
public class UserRole implements Serializable {
    /**
     * 用户id
     */
    @Column(name = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色id
     */
    @Column(name = "t_r_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    private static final long serialVersionUID = 1L;
}