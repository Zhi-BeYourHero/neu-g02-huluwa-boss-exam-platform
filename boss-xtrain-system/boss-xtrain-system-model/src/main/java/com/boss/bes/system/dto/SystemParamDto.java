package com.boss.bes.system.dto;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import com.boss.bes.system.entity.SystemParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统参数的共同属性
 * @create 2020-07-09 01:14
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SystemParamDto extends BaseDTO implements Serializable {

    /**
     * 参数ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 参数项
     */
    private String param;

    /**
     * 参数值
     */
    private String value;

    private static final long serialVersionUID = 1L;

    public SystemParamDto(SystemParam systemParam){
        this.id = systemParam.getId();
        this.paramType = systemParam.getParamType();
        this.param = systemParam.getParam();
        this.value = systemParam.getValue();
        this.setCreatedBy(systemParam.getCreatedBy());
        this.setCreatedTime(systemParam.getCreatedTime());
        this.setUpdatedBy(systemParam.getUpdatedBy());
        this.setUpdatedTime(systemParam.getUpdatedTime());
        this.setStatus(systemParam.getStatus());
        this.setVersion(systemParam.getVersion());
    }
}