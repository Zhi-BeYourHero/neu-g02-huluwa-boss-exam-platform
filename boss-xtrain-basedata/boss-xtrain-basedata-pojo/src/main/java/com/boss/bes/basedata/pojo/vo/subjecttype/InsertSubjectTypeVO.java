package com.boss.bes.basedata.pojo.vo.subjecttype;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@Data
public class InsertSubjectTypeVO implements Serializable {
    /**
     * 题型ID
     */
    private Long id;
    /**
     * 题型名称
     */
    @NotNull
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
    @NotNull
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /**
     * 版本
     */
    private Long version;
}
