package com.boss.bes.basedata.pojo.dto.category;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class InsertCategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目类别ID
     */
    private Long categoryId;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 父类别ID
     */
    private Long parentId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 组织机构ID
     */
    private Long orgId;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 版本
     */
    private Long version;
}
