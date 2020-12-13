package com.boss.bes.basedata.pojo.vo.category;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class UpdateCategoryVO {
    /**
     * 题目类别ID
     */
    @NotNull
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
     * 修改人
     */
    private Long updatedBy;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 版本
     */
    @NotNull
    private Long version;
}
