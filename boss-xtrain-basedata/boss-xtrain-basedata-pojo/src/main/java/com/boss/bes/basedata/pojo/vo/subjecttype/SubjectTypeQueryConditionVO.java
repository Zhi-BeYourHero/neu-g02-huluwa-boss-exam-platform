package com.boss.bes.basedata.pojo.vo.subjecttype;

import lombok.Data;

@Data
public class SubjectTypeQueryConditionVO {
    /**
     * 题型ID
     */
    private Long subjectTypeId;
    /**
     * 题型名称
     */
    private String subjectTypeName;
    /**
     * 机构ID
     */
    private Long orgId;
    /**
     * 版本
     */
    private Long version;
    private Integer start;
    private Integer size;
}
