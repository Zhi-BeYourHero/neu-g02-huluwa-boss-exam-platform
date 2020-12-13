package com.boss.bes.paper.pojo.dto.managetemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.dto.managetemplate
 * @Description:
 * @Date: 2020/7/8 9:45
 * @since: 1.0
 */
public class TemplateSearchDTO {

    /**
     * 模板名称
     */
    @Getter
    @Setter
    private String name;
    /**
     * 组卷时间（起始时间）
     */
    private Date combExamStartTime;

    /**
     * 组卷时间（终止时间）
     */
    private Date combExamEndTime;
    /**
     * 启用状态
     * */
    @Getter
    @Setter
    private Boolean status;
    /**
     * 模板标记默认
     * */
    @Getter
    @Setter
    private Boolean template;

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

}
