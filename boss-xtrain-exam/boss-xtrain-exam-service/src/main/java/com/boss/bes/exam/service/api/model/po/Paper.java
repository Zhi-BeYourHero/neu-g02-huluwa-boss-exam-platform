package com.boss.bes.exam.service.api.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷实体类
 * @Date: 2020/7/2 8:25
 * @since: 1.0
 */
@Data
@Table(name="t_paper")
public class Paper implements Serializable {

    /**
     * 试卷id
     */
    @Id
    private Long id;

    /**
     * 试卷名
     */
    @Column(name = "name")
    private String name;

    /**
     * 试卷类型
     */
    @Column(name = "paper_type")
    private Long paperType;

    /**
     * 试卷难度
     */
    @Column(name = "difficuty")
    private Long difficulty;

    /**
     * 组卷人
     */
    @Column(name = "comb_exam_man")
    private String combExamMan;

    /**
     * 组卷时间
     */
    @Column(name = "comb_exam_time")
    private Date combExamTime;

    /**
     * 试卷总分
     */
    @Column(name = "score")
    private BigDecimal score;

    /**
     * 试卷描述
     */
    @Column(name = "discript")
    private String discript;

    /**
     * 模板标记(0不是模板，1是模板）
     */
    @Column(name = "template")
    private Boolean template;

    /**
     * 下载次数
     */
    @Column(name = "download_times")
    private Integer downloadTimes;

    /**
     * 发布次
     */
    @Column(name = "publish_times")
    private Integer publishTimes;

    /**
     * 组织机构ID
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 公司id
     */
    @Column(name = "company_id")
    private Long companyId;

    /** 创建者 */
    private Long createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新者 */
    private Long updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    /** 状态*/
    private Integer status;

    /** 版本 */
    private Long version;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPaperType(){
        return paperType;
    }

    public void setPaperType(Long paperType) {
        this.paperType = paperType;
    }

    public Long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    public String getCombExamMan() {
        return combExamMan;
    }

    public void setCombExamMan(String combExamMan) {
        this.combExamMan = combExamMan;
    }

    public Date getCombExamTime() {
        return (combExamTime!=null)?(Date) combExamTime.clone():null;
    }

    public void setCombExamTime(Date combExamTime) {
        this.combExamTime = (combExamTime!=null)?(Date) combExamTime.clone():null;
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

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public Integer getPublishTimes() {
        return publishTimes;
    }

    public void setPublishTimes(Integer publishTimes) {
        this.publishTimes = publishTimes;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString(){
        return "Paper{"+
                "id="+id+
                "name="+name+
                "paperType="+paperType+
                "difficulty="+difficulty+
                "combExamMan="+combExamMan+
                "combExamTime="+combExamTime+
                "score="+score+
                "discript="+discript+
                "template="+template+
                "downloadTimes="+downloadTimes+
                "publishTimes="+publishTimes+
                "orgId="+orgId+
                "companyId="+companyId+
                "}";
    }

    public Paper() {
    }

    private static final long serialVersionUID = 1L;

}