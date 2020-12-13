package com.boss.bes.paper.pojo.dto.makepaper;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 配置明细DTO
 * @Date: 2020/7/8 8:30
 * @since: 1.0
 */
@Data
public class ConfigItemDTO {
    /**
     * 题类别
     * */
    private String category;
    /**
     * 题型
     * */
    private String subjectType;
    /**
     * 难度
     * */
    private Long difficulty;
    /**
     * 数量
     */
    private Integer subjectNum;


    /**
     * 分值
     */
    private BigDecimal score;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
}

