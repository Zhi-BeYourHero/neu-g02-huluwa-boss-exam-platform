package com.boss.bes.paper.pojo.vo.managepaper.answer;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 题目添加VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AnswerAddVO extends BaseAnswerVO {
    /**
     * 公司id
     */
    private Long companyId;



}

