package com.boss.bes.basedata.pojo.dto.category;

public class CategoryListConditionDTO {
    private Long orgId;
    public Long getOrgId() {
        return orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    @Override
    public String toString() {
        return "CategoryListConditionDTO{" +
                "orgId=" + orgId +
                '}';
    }
}
