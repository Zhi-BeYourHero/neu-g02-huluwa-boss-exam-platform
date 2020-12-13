package com.boss.bes.basedata.pojo.vo.subject;
import com.boss.bes.basedata.pojo.entity.SubjectAnswer;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class InsertSubjectVO {
    /**
     * 题目ID
     */
    private Long id;
    @NotNull
    private String subjectTypeName;
    @NotNull
    private String categoryName;
    /**
     * 题目
     */
    @NotNull
    private String name;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 难度ID
     */
    @NotNull
    private Long difficulty;
    /**
     * 备注
     */
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
    private List<SubjectAnswer> answers;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSubjectTypeName() {
        return subjectTypeName;
    }
    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Long getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
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
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
    public List<SubjectAnswer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<SubjectAnswer> answers) {
        this.answers = answers;
    }
    @Override
    public String toString() {
        return "AddSubjectVO{" +
                "id=" + id +
                ", subjectTypeName='" + subjectTypeName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", difficulty=" + difficulty +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", orgId=" + orgId +
                ", companyId=" + companyId +
                ", createdBy=" + createdBy +
                ", createdTime=" + createdTime +
                ", version=" + version +
                ", answers=" + answers +
                '}';
    }
}
