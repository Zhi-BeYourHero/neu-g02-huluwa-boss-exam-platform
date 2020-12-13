package com.boss.bes.basedata.pojo.vo.subject;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class DeleteSubjectVO {
    /**
     * 题目ID
     */
    @NotNull
    private Long subjectId;
    /**
     * 题目
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
