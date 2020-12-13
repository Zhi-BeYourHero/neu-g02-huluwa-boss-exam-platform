package com.boss.bes.basedata.pojo.dto.category;
import lombok.Data;

import java.io.Serializable;
@Data
public class DeleteCategoryDTO implements Serializable {
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
     * 状态位
     */
    private Integer status;
    /**
     * 版本
     */
    private Long version;
}
