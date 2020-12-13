package com.boss.bes.basedata.pojo.vo.subjecttype;

public class SubjectTypeListConditionVO {
    private Long orgId;
    public Long getOrgId() {
        return orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    @Override
    public String toString() {
        return "SubjectTypeListConditionVO{" +
                "orgId=" + orgId +
                '}';
    }
}
