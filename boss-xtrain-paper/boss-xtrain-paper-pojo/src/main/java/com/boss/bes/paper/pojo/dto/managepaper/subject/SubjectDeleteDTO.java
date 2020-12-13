package com.boss.bes.paper.pojo.dto.managepaper.subject;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试题删除
 * @Date: 2020/7/5 8:25
 * @since: 1.0
 */
@Data
public class SubjectDeleteDTO {

    /**
     * 试卷id
     * */
    private Long paperId;
    /**
     * 试题id列表
     * */
    private List<Long> id;
    /**
     * 试题版本
     * */
    private List<Long> version;

    public Long getPaperId() {
        return paperId;
    }

    public void setId(List<Long> id) {
        this.id = id;
    }

    public List<Long> getId() {
        return id;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }


}