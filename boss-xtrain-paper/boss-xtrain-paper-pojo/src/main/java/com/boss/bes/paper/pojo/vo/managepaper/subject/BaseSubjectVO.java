package com.boss.bes.paper.pojo.vo.managepaper.subject;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: Base题目VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
class BaseSubjectVO {

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long paperId;
    /**
     * 试题名称
     * */
    @NotEmpty(message = "题目不能为空")
    private String subject;
    /**
     * 试题题型
     * */
    @NotNull(message = "题目类型不能为空")
    private String subjectTypeId;
    /**
     * 题目类别
     * */
    @NotNull(message = "题目类别不能为空")
    private Long categoryId;
    /**
     * 难度
     * */
    @NotNull(message = "难度不能为空")
    private Long difficulty;

    /**
     * 分数
     */
    private BigDecimal score;
}

