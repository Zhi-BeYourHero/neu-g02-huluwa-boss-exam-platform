package com.boss.bes.basedata.pojo.vo.combexamconfig;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Data
public class InsertCombExamConfigVO {
    /**
     * 组卷配置ID
     */
    private Long combExamId;
    /**
     * 配置名
     */
    @NotNull
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 标记位
     */
    @NotNull
    private Integer status;
    @NotNull
    private Long difficulty;
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
     * 版本
     */
    private Long version;
    /**
     * 组卷配置明细项
     */
    private List<CombExamConfigItemDTO> configItems;
}
