package com.boss.bes.basedata.pojo.dto.subjecttype;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectTypeDataItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题型ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long subjectTypeId;
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
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 版本
     */
    private Long version;
    private Integer start;
    private Integer size;
}
