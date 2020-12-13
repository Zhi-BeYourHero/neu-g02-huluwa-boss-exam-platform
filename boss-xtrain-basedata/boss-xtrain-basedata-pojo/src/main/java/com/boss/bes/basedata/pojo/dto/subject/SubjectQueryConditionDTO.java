package com.boss.bes.basedata.pojo.dto.subject;
import lombok.Data;

import java.io.Serializable;
@Data
public class SubjectQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目ID
     */
    private Long subjectId;
    /**
     * 题型_题型ID
     */
    private Long subjectTypeId;
    /**
     * 题目类_题目类别ID
     */
    private Long categoryId;
    /**
     * 题目
     */
    private String name;
    /**
     * 题型名称
     */
    private String subjectTypeName;
    /**
     * 题类别
     */
    private String categoryName;
    /**
     * 组织机构ID
     */
    private Long orgId;
    /**
     * 公司ID
     */
    private Long companyId;
    private Integer start;
    private Integer size;
}
