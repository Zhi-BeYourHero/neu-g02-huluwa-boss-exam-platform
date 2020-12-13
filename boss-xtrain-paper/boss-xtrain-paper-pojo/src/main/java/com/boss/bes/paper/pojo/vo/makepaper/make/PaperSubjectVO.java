package com.boss.bes.paper.pojo.vo.makepaper.make;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;
import com.boss.bes.paper.pojo.vo.managepaper.crud.SubjectAnswerItemVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.vo.makepaper.make
 * @Description:
 * @Date: 2020/7/11 20:54
 * @since: 1.0
 */
@Data
public class PaperSubjectVO {

    /**
     * 题目
     * */
    private String subject;
    /**
     * 题型
     * */
    private Long subjectTypeId;
    /**
     * 题型
     * */
    private Long categoryId;
    /**
     * 难度
     * */
    private Long difficulty;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 试题答案列表
     * */
    private List<SubjectAnswerItemVO> answerList;
    /**
     * 版本
     * */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long version;
}
