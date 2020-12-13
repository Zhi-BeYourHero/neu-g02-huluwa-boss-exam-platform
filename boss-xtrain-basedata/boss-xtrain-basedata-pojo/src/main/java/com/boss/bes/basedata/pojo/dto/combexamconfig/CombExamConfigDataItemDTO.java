package com.boss.bes.basedata.pojo.dto.combexamconfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CombExamConfigDataItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 组卷配置ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long combExamId;
    /**
     * 配置名
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 标记位
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
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private Long updatedBy;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 难度ID
     */
    private Long difficulty;
    /**
     * 版本
     */
    private Long version;
    /**
     * 组卷配置明细项
     */
    private List<CombExamConfigItemDTO> configItems;
    private Integer start;
    private Integer size;
}
