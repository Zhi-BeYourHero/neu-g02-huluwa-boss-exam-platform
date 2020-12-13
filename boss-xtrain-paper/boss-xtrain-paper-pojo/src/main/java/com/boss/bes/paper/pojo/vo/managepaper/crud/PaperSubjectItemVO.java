package com.boss.bes.paper.pojo.vo.managepaper.crud;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷题目数据VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class PaperSubjectItemVO {
    /**
     *  id
     *  */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;
    /**
     * 题目
     * */
    private String subject;
    /**
     * 题型
     * */
    private String subjectTypeId;
    /**
     * 题型
     * */
    private String categoryId;

    /**
     * 难度
     * */
    private String difficulty;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 试题答案列表
     * */
    private List<SubjectAnswerItemVO> answers;
    /**
     * 版本
     * */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long version;
}

