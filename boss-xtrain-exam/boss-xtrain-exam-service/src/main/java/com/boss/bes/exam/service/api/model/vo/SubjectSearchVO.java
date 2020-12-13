package com.boss.bes.exam.service.api.model.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: fhf
 * @program: boss-xtrain-paper-pojo
 * @Description: 题目查询VO 调用题目查询时的VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class SubjectSearchVO {

    /**
     * 题目类型
     * */
    @NotNull(message = "题目类型不能为空")
    private String subjectType;

    /**
     * 试卷id列表
     * */
    @NotEmpty(message = "试卷id列表不能为空")
    private List<Long>  paperList;
}
