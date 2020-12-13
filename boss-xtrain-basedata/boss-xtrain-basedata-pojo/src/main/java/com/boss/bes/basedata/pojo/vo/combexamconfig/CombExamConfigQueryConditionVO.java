package com.boss.bes.basedata.pojo.vo.combexamconfig;

import lombok.Data;

@Data
public class CombExamConfigQueryConditionVO {
    /**
     * 组卷配置ID
     */
    private Long combExamId;
    /**
     * 配置名
     */
    private String name;
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
    private Integer start;
    private Integer size;
}
