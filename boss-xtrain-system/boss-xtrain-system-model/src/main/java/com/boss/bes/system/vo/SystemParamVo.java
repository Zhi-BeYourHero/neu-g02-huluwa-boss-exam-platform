package com.boss.bes.system.vo;

import boss.xtrain.core.data.convention.base.vo.BaseVO;
import com.boss.bes.system.entity.SystemParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统参数的VO类
 * @create 2020-07-09 11:20
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SystemParamVo extends BaseVO implements Serializable {

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

    public SystemParamVo(SystemParam systemParam){
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