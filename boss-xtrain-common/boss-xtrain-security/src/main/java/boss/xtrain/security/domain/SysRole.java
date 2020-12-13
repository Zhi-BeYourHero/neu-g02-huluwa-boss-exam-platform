package boss.xtrain.security.domain;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-06
 * @since
 */
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 377266944823192946L;
    /**
     * 角色id
     */
    private Long id;
    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 组织机构id
     */
    private Long orgId;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色代码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", orgId=" + orgId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
