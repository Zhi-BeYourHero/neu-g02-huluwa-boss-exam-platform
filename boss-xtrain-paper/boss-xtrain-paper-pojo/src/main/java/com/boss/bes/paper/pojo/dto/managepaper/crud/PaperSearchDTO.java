package com.boss.bes.paper.pojo.dto.managepaper.crud;

import java.util.Date;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷查询
 * @Date: 2020/7/5 8:25
 * @since: 1.0
 */
public class PaperSearchDTO {
    /**
     * 试卷名
     * */
    private String paperName;
    /**
     * 组卷人
     */
    private String combExamMan;

    /**
     * 组卷时间--起始时间
     */
    private Date combExamStartTime;

    /**
     * 组卷时间--终止时间
     */
    private Date combExamEndTime;

    /**
     * 试卷类型
     */
    private Long paperType;

    /**
     * 试卷难度
     */
    private Long difficulty;

    /**
     * 公司id
     * */
    private Long companyId;

    /**
     * 是否为模板标识
     * */
    private Boolean template;

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getCombExamMan() {
        return combExamMan;
    }

    public void setCombExamMan(String combExamMan) {
        this.combExamMan = combExamMan;
    }

    public Long getPaperType() {
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

    public Boolean getTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getCombExamStartTime() {
        return (combExamStartTime!=null)?(Date) combExamStartTime.clone():null;
    }

    public void setCombExamStartTime(Date combExamStartTime) {
        this.combExamStartTime = (combExamStartTime!=null)?(Date) combExamStartTime.clone():null;
    }

    public Date getCombExamEndTime() {
        return (combExamEndTime!=null)?(Date) combExamEndTime.clone():null;
    }

    public void setCombExamEndTime(Date combExamEndTime) {
        this.combExamEndTime = (combExamEndTime!=null)?(Date) combExamEndTime.clone():null;
    }

    @Override
    public String toString() {
        return "PaperSearchDTO{" +
                "paperName='" + paperName + '\'' +
                ", combExamMan='" + combExamMan + '\'' +
                ", combExamStartTime=" + combExamStartTime +
                ", combExamEndTime=" + combExamEndTime +
                ", paperType=" + paperType +
                ", difficulty=" + difficulty +
                ", companyId=" + companyId +
                ", template=" + template +
                '}';
    }
}
