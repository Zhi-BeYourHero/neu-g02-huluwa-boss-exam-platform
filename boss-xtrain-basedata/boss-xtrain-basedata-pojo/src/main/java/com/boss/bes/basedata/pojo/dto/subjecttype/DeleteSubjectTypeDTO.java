package com.boss.bes.basedata.pojo.dto.subjecttype;
import lombok.Data;

import java.io.Serializable;
@Data
public class DeleteSubjectTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题型ID
     */
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
