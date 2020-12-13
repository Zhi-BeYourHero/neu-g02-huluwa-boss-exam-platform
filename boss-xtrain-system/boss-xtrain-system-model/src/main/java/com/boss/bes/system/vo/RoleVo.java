package com.boss.bes.system.vo;

import boss.xtrain.core.data.convention.base.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的VO类
 * @create 2020-07-10 15:03
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleVo extends BaseVO implements Serializable {

    /**
     * 角色id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 公司id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    /**
     * 组织机构id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long organizationId;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色代码
     */
    private String code;

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String remark;

    /** 创建者 */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新者 */
    @JsonSerialize(using = ToStringSerializer.class)
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
}