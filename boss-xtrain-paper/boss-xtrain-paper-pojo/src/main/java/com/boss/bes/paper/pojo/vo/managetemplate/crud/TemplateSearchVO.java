package com.boss.bes.paper.pojo.vo.managetemplate.crud;

import javax.validation.constraints.Min;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 查询模板VO
 * @Date: 2020/7/7 8:25
 * @since: 1.0
 */
@ToString
public class TemplateSearchVO {

    /**
     * 模板名
     * */
    @Getter
    @Setter
    private String name;
    /**
     * 组卷时间--起始时间
     */
    private Date combExamStartTime;

    /**
     * 组卷时间--终止时间
     */
    private Date combExamEndTime;
    /**
     * 启用状态
     * */
    @Getter
    @Setter
    private Long status;
    /**
     * 模板标记默认
     * */
    @Getter
    @Setter
    private Boolean template;
    /**
     * 查询页数
     * */
    @Getter
    @Setter
    @Min(0)
    private Integer pageIndex;
    /**
     * 页面大小
     * */
    @Getter
    @Setter
    @Min(0)
    private  Integer pageSize;

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
