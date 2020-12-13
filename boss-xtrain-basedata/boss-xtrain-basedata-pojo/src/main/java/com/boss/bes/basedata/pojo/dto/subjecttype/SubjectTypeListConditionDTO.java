package com.boss.bes.basedata.pojo.dto.subjecttype;

public class SubjectTypeListConditionDTO {
    private Long orgId;
    public Long getOrgId() {
        return orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    @Override
    public String toString() {
        return "SubjectTypeListConditionDTO{" +
                "orgId=" + orgId +
                '}';
    }
}
