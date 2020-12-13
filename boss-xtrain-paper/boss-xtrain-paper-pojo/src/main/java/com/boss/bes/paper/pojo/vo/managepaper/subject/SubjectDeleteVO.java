package com.boss.bes.paper.pojo.vo.managepaper.subject;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 题目删除VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class SubjectDeleteVO {
    /**
     * 试卷ID
     */
    private Long paperId;

    /**
     * 题目ID
     */
    private List<Long> id;
}
