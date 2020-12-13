package com.boss.bes.basedata.pojo.vo.subjecttype;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeleteSubjectTypeVO implements Serializable {
    /**
     * 题型ID
     */
    @NotNull
    private Long subjectTypeId;
    /**
     * 题型名称
     */
    private String subjectTypeName;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 机构ID
     */
    private Long orgId;
    /**
     * 版本
     */
    private Long version;
}
