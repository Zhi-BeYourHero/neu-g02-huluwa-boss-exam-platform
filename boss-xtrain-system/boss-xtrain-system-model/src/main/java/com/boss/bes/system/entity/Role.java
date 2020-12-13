package com.boss.bes.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的服务接口
 * @create 2020-07-07
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_role")
@JsonInclude()
public class Role extends BaseEntity implements Serializable {
    /**
     * 角色id
     */
    @Id
    @Column(name = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 公司id
     */
    @Column(name = "t_c_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    /**
     * 组织机构id
     */
    @Column(name = "t_o_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long organizationId;

    /**
     * 角色名
     */
    @Column(name = "name")
    private String name;

    /**
     * 角色代码
     */
    @Column(name = "code")
    private String code;

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

}