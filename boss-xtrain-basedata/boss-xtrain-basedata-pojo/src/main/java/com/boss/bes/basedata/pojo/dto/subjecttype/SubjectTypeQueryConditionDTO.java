package com.boss.bes.basedata.pojo.dto.subjecttype;
import lombok.Data;

import java.io.Serializable;
@Data
public class SubjectTypeQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题型名称
     */
    private String subjectTypeName;
    private Integer start;
    private Integer size;
}
