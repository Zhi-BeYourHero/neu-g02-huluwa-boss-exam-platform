package com.boss.bes.basedata.pojo.dto.subjecttype;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class InsertSubjectTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题型ID
     */
    private Long id;
    /**
     * 题型名称
     */
    private String subjectTypeName;
    /**
     * 属性列
     */
    private String attribute;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 机构ID
     */
    private Long orgId;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 版本
     */
    private Long version;
}
