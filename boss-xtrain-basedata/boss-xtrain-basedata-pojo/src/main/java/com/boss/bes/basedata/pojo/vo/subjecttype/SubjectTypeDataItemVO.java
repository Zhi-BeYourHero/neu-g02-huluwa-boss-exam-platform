package com.boss.bes.basedata.pojo.vo.subjecttype;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectTypeDataItemVO {
    /**
     * 题型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    /**
     * 版本
     */
    private Long version;
    private Integer start;
    private Integer size;
}
