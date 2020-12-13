package com.boss.bes.paper.pojo.vo.managepaper.crud;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷信息封装VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@ToString
public class PaperListVO {
    /**
     * 试卷id
     */
    @Getter
    @Setter
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long id;
    /**
     * 试卷名
     */
    @Getter
    @Setter
    private String name;
    /**
     * 试卷类型
     */
    @Getter
    @Setter
    private String paperType;
    /**
     * 试卷难度
     */
    @Getter
    @Setter
    private String difficulty;
    /**
     * 组卷人
     */
    @Getter
    @Setter
    private String combExamMan;
    /**
     * 组卷时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date combExamTime;
    /**
     * 试卷总分
     */
    @Getter
    @Setter
    private BigDecimal score;
    /**
     * 试卷描述
     */
    @Getter
    @Setter
    private String discript;
    /**
     * 状态
     * */
    @Getter
    @Setter
    private Integer status;
    /**
     *版本
     * */
    @Getter
    @Setter
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long version;
    /**
     * 下载次数
     */
    @Getter
    @Setter
    private Integer downloadTimes;

    public Date getCombExamTime() {
        return (Date) combExamTime.clone();
    }

    public void setCombExamTime(Date combExamTime) {
        this.combExamTime = (Date) combExamTime.clone();
    }

}
