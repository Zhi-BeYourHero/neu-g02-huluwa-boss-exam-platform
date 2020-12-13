package com.boss.bes.basedata.pojo.dto.subject;
import lombok.Data;

import java.io.Serializable;
@Data
public class DeleteSubjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目ID
     */
    private Long subjectId;
    /**
     * 题目
     */
    private String name;
    /**
     * 状态位
     */
    private Boolean status;
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
