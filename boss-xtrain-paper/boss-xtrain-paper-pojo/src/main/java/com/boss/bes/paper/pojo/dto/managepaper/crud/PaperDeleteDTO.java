package com.boss.bes.paper.pojo.dto.managepaper.crud;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷删除类
 * @Date: 2020/7/5 8:25
 * @since: 1.0
 */
@Data
public class PaperDeleteDTO {
    /**
     * 试卷id
     * */
    private List<Long> id;
    /**
     * version
     * */
    private List<Long> version;


}
