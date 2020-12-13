package com.boss.bes.basedata.pojo.vo.category;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class InsertCategoryVO {
    /**
     * 类别名称
     */
    @NotNull
    private String name;
    /**
     * 父类别ID
     */
    @NotNull
    private Long parentId;
    /**
     * 备注
     */
    @NotNull
    private String remark;
    /**
     * 状态位
     */
    @NotNull
    private Integer status;
    /**
     * 组织机构ID
     */
    private Long orgId;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /**
     * 版本
     */
    private Long version;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Long getOrgId() {
        return orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    public Long getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedTime(){
        return (createdTime!=null)?(Date) createdTime.clone():null;
    }
    public void setCreatedTime(Date createdTime){
        this.createdTime=(createdTime!=null)?(Date)createdTime.clone():null;
    }
    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    @Override
    public String toString() {
        return "AddCategoryVO{" +
                "name='" + name + '\'' +
                ", parentId=" + parentId +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", orgId=" + orgId +
                ", createdBy=" + createdBy +
                ", createdTime=" + createdTime +
                ", version=" + version +
                '}';
    }
}
