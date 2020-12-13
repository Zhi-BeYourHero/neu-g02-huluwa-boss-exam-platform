package com.boss.bes.paper.pojo.vo.makepaper.config;

import lombok.Data;

import java.math.BigDecimal;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.vo.makepaper.config
 * @Description:
 * @Date: 2020/7/11 20:51
 * @since: 1.0
 */
@Data
public class ConfigItemVO {

    /**
     * 题型
     */
    private String subjectType;

    /**
     * 题目类别
     */
    private String category;

    /**
     * 数量
     */
    private Integer subjectNum;

    /**
     * 难度ID
     */
    private String difficulty;

    /**
     * 分值
     */
    private BigDecimal score;
}
