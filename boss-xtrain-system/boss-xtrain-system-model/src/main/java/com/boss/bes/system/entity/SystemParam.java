package com.boss.bes.system.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.boss.bes.system.dto.SystemParamDto;
import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统参数的实体类 tkMyBatis生成
 * @create 2020-07-07
 * @since 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_system_param")
@AllArgsConstructor
@NoArgsConstructor
public class SystemParam extends BaseEntity implements Serializable{

    /**
     * 参数ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 参数类型
     */
    @Column(name = "param_type")
    private String paramType;

    /**
     * 参数项
     */
    @Column(name = "param")
    private String param;

    /**
     * 参数值
     */
    @Column(name = "`value`")
    private String value;

    private static final long serialVersionUID = 1L;

    public SystemParam(SystemParamDto systemParamDto){
        this.id = systemParamDto.getId();
        this.paramType = systemParamDto.getParamType();
        this.param = systemParamDto.getParam();
        this.value = systemParamDto.getValue();
        this.setCreatedBy(systemParamDto.getCreatedBy());
        this.setCreatedTime(systemParamDto.getCreatedTime());
        this.setUpdatedBy(systemParamDto.getUpdatedBy());
        this.setUpdatedTime(systemParamDto.getUpdatedTime());
        this.setStatus(systemParamDto.getStatus());
        this.setVersion(systemParamDto.getVersion());
    }
}