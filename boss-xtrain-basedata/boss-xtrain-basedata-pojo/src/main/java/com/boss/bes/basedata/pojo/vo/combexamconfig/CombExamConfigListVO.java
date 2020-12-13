package com.boss.bes.basedata.pojo.vo.combexamconfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CombExamConfigListVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long combExamId;
    /**
     * 配置名
     */
    private String name;
    /**
     * 修改人
     */
    private Long updatedBy;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 难度ID
     */
    private Long difficulty;

    public Long getcombExamId() {
        return combExamId;
    }
    public void setcombExamId(Long combExamId) {
        this.combExamId = combExamId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Date getUpdatedTime(){
        return (updatedTime!=null)?(Date) updatedTime.clone():null;
    }
    public void setUpdatedTime(Date updatedTime){
        this.updatedTime=(updatedTime!=null)?(Date)updatedTime.clone():null;
    }
    public Long getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }
    @Override
    public String toString() {
        return "CombExamConfigListVO{" +
                "combExamId=" + combExamId +
                ", name='" + name + '\'' +
                ", updatedBy=" + updatedBy +
                ", updatedTime=" + updatedTime +
                ", difficulty=" + difficulty +
                '}';
    }
}
