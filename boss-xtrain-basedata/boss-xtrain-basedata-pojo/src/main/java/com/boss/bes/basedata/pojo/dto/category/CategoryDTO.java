package com.boss.bes.basedata.pojo.dto.category;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;
@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目类别ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long categoryId;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    /**
     * 组织机构ID
     */
    private Long orgId;
    /**
     * 版本
     */
    private Long version;
    /**
     * 父类别id
     */
    private Long parentId;
    private Integer start;
    private Integer size;
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
