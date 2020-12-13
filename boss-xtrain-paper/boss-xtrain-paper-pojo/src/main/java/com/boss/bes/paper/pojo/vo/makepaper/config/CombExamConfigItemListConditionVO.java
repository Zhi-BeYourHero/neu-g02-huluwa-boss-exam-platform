package com.boss.bes.paper.pojo.vo.makepaper.config;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.vo.makepaper.config
 * @Description:
 * @Date: 2020/7/11 20:52
 * @since: 1.0
 */
@Data
public class CombExamConfigItemListConditionVO {

    /**
     * 配置id
     * */
    @NotNull
    private Long combExamConfigItemId;
}
