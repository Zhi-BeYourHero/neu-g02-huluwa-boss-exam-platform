package com.boss.bes.system.entity;

import java.io.Serializable;
import javax.persistence.*;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的DTO类
 * @create 2020-07-10 14:50
 * @since 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_position")
@JsonInclude()
public class Position extends BaseEntity implements Serializable {
    /**
     * 职位id
     */
    @Id
    @Column(name = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "t_u_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 公司id
     */
    @Column(name = "t_c_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    /**
     * 职位名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 职位编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}