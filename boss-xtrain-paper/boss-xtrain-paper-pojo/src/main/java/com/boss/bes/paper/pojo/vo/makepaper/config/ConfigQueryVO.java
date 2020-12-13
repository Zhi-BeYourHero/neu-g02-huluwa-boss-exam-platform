package com.boss.bes.paper.pojo.vo.makepaper.config;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.vo.makepaper.config
 * @Description:
 * @Date: 2020/7/11 20:48
 * @since: 1.0
 */
@Data
public class ConfigQueryVO {

    /**
     * 配置名
     * */
    private String name;
    /**
     * 配置难度
     * */
    private String difficulty;
    /**
     * 查询页数
     * */
    @NotNull
    @Min(1)
    private Integer pageIndex;
    /**
     * 页面大小
     * */
    @NotNull
    @Min(1)
    private  Integer pageSize;
}
