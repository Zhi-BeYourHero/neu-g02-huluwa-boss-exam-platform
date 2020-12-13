package com.boss.bes.basedata.pojo.dto.combexamconfig;
import lombok.Data;

import java.io.Serializable;
@Data
public class CombExamConfigQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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
}
