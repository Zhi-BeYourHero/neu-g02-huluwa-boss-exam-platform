package com.boss.bes.paper.pojo.vo.managepaper.crud;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import java.math.BigDecimal;
import java.util.List;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷细节VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
public class PaperDetailVO {

    /**
     * 试卷id
     */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

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
     * 试卷总分
     */
    private BigDecimal score;

    /**
     * 试卷描述
     */
    private String discript;

    /**
     * 状态位
     */
    private String status;

    /**
     * 版本
     */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long version;

    /**
     * 试题列表
     */
    private List<PaperSubjectItemVO> subjects;

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

    public String getPaperType() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<PaperSubjectItemVO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<PaperSubjectItemVO> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "PaperDetailVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paperType='" + paperType + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", score=" + score +
                ", discript='" + discript + '\'' +
                ", status='" + status + '\'' +
                ", version=" + version +
                ", subjects=" + subjects +
                '}';
    }
}
