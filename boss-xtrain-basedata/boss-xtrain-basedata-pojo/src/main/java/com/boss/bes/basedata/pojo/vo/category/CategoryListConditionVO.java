package com.boss.bes.basedata.pojo.vo.category;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryListConditionVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orgId;
    public Long getOrgId() {
        return orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    @Override
    public String toString() {
        return "CategoryListConditionVO{" +
                "orgId=" + orgId +
                '}';
    }
}
