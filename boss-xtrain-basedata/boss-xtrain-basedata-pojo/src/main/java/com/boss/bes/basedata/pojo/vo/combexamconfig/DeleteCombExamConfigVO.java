package com.boss.bes.basedata.pojo.vo.combexamconfig;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class DeleteCombExamConfigVO {
    /**
     * 组卷配置ID
     */
    @NotNull
    private Long combExamId;
    /**
     * 配置名
     */
    private String name;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 组织机构ID
     */
    private Long orgId;
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 版本
     */
    private Long version;
}
