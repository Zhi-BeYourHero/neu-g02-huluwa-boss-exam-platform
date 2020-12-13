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
 * @description 资源和角色多表查询实体类
 * @create 2020-07-07
 * @since 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_role_resource")
public class RoleResource implements Serializable {
    /**
     * 角色id
     */
    @Id
    @Column(name = "t_r_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 资源id
     */
    @Column(name = "t_res_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long resourceId;

    private static final long serialVersionUID = 1L;
}