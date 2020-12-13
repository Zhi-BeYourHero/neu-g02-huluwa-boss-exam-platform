package com.boss.bes.basedata.pojo.vo.category;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class DeleteCategoryVO {
    /**
     * 题目类别ID
     */
    @NotNull
    private Long categoryId;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 版本
     */
    private Long version;
}
