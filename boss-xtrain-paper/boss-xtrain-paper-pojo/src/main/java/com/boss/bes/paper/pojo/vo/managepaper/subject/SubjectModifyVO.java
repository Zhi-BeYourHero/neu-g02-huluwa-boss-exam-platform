package com.boss.bes.paper.pojo.vo.managepaper.subject;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试题查找VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SubjectModifyVO extends BaseSubjectVO{

    /**
     * 试卷试题ID
     */
    private Long id;

    /**
     * 试题version
     */
    private Long version;
}
