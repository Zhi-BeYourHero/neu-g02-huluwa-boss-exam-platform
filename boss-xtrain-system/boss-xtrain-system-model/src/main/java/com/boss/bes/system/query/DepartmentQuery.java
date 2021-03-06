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
 * @description 部门的查询DTO类
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DepartmentQuery  extends BaseQueryDTO implements Serializable {
    /**
     * 部门id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 部门编号
     */
    private String code;

    /**
     * 部门级别
     */
    private String level;

    /**
     * 上级部门
     */
    private Long parentId;

    /**
     * 负责人
     */
    private String master;

    /**
     * 部门描述
     */
    private String descript;
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
