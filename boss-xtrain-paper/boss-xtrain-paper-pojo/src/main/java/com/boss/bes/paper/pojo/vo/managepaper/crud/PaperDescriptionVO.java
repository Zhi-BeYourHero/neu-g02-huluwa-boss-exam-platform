package com.boss.bes.paper.pojo.vo.managepaper.crud;

import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷详细VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
public class PaperDescriptionVO {

    /**
     * 试卷名
     */
    private String name;

    /**
     * 试卷类型
     */
    private String paperType;

    /**
     * 试卷难度
     */
    private String difficulty;

    /**
     * 组卷人
     */
    private String combExamMan;

    /**
     * 组卷时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date combExamTime;

    /**
     * 试卷总分
     */
    private BigDecimal score;

    /**
     * 试卷描述
     */
    private String discript;

    /**
     * 模板标记(0不是模板，1是模板）
     */
    private Boolean template;

    /**
     * 发布次数
     */
    private Integer publishTimes;

    /**
     * 状态位
     */
    private String status;

    /**
     * 机构id
     */
    private String orgId;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 组卷人
     */
    private String createdBy;

    /**
     * 组卷试卷
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;



    public Date getUpdatedTime() {
        return (Date) updatedTime.clone();
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = (Date) updatedTime.clone();
    }

    public Date getCombExamTime() {
        return (Date) combExamTime.clone();
    }

    public void setCombExamTime(Date combExamTime) {
        this.combExamTime = (Date) combExamTime.clone();
    }

    public Date getCreatedTime() {
        return (Date) createdTime.clone();
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = (Date) createdTime.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaperType(){
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCombExamMan() {
        return combExamMan;
    }

    public void setCombExamMan(String combExamMan) {
        this.combExamMan = combExamMan;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getDiscript() {
        return discript;
    }

    public void setDiscript(String discript) {
        this.discript = discript;
    }

    public Boolean getTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public Integer getPublishTimes() {
        return publishTimes;
    }

    public void setPublishTimes(Integer publishTimes) {
        this.publishTimes = publishTimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "PaperDescriptionVO{" +
                "name='" + name + '\'' +
                ", paperType='" + paperType + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", combExamMan='" + combExamMan + '\'' +
                ", combExamTime=" + combExamTime +
                ", score=" + score +
                ", discript='" + discript + '\'' +
                ", template=" + template +
                ", publishTimes=" + publishTimes +
                ", status='" + status + '\'' +
                ", orgId='" + orgId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
