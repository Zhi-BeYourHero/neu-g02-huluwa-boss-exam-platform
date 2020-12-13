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
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @description 组织机构的查询DTO类
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrganizationQuery extends BaseQueryDTO implements Serializable {
    /**
     * 组织机构id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 组织名
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 法人
     */
    private String master;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;
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
