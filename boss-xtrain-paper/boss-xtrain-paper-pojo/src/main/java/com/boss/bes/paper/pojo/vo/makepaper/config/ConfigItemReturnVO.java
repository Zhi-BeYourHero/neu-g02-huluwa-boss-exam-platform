package com.boss.bes.paper.pojo.vo.makepaper.config;

import lombok.Data;

import java.math.BigDecimal;



/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.vo.makepaper.config
 * @Description:
 * @Date: 2020/7/11 20:50
 * @since: 1.0
 */
@Data
public class ConfigItemReturnVO {

    /**
     * 题型
     */
    private String subjectTypeName;

    /**
     * 题目类别
     */
    private String categoryName;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 难度ID
     */
    private Long difficulty;

    /**
     * 难度
     */
    private String difficultyName;

    /**
     * 分值
     */
    private BigDecimal score;
}
