package com.boss.bes.system.query;

import boss.xtrain.core.data.convention.base.dto.BaseQueryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统参数的查询DTO类
 * @create 2020-07-09 01:27
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class SystemParamQuery extends BaseQueryDTO implements Serializable {

    /**
     * 参数id
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

    /** 创建者 */
    private Long createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新者 */
    private Long updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    /** 状态*/
    private Integer status;

    /**
     * 版本
     */
    private Long version;

    private static final long serialVersionUID = 1L;

}