package com.boss.bes.paper.pojo.vo.managepaper.crud;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

import lombok.Data;
/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷查询VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class PaperSearchVO {
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
     * 查询页数
     * */
    @NotNull
    @Min(1)
    private Integer pageIndex;
    /**
     * 页面大小
     * */
    @NotNull
    @Min(1)
    private  Integer pageSize;
    /**
     * 模板标志
     * */
    @NotNull
    private Boolean template;

    public Date getCombExamStartTime() {
        return (combExamStartTime!=null)?(Date) combExamStartTime.clone():null;
    }

    public void setCombExamStartTime(Date combExamStartTime) {
        this.combExamStartTime = combExamStartTime!=null?(Date) combExamStartTime.clone():null;
    }

    public Date getCombExamEndTime() {
        return (combExamEndTime!=null)?(Date) combExamEndTime.clone():null;
    }

    public void setCombExamEndTime(Date combExamEndTime) {
        this.combExamEndTime = combExamEndTime!=null?(Date) combExamEndTime.clone():null;
    }
}
