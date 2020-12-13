package com.boss.bes.basedata.pojo.vo.combexamconfig;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CombExamConfigDataItemVO {
    /**
     * 组卷配置ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long combExamConfigItemId;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
    private Long difficult;
    /**
     * 版本
     */
    private Long version;
    /**
     * 组卷配置明细项
     */
    @NotNull
    private List<CombExamConfigItemDTO> configItems;
    private Integer pageIndex;
    private Integer pageSize;
    public List<CombExamConfigItemDTO> getConfigItems() {
        return configItems;
    }
    public void setConfigItems(List<CombExamConfigItemDTO> configItems) {
        this.configItems = configItems;
    }
}
