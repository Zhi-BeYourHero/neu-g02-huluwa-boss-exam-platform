package com.boss.bes.paper.pojo.dto.managepaper.subject;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.dto.managepaper.subject
 * @Description:
 * @Date: 2020/7/9 12:09
 * @since: 1.0
 */
@Data
public class SubjectSearchDTO {

    /**
     * 题目类型
     * */
    private String subjectType;

    /**
     * 试卷id列表
     * */
    private List<Long> paperList;
}
