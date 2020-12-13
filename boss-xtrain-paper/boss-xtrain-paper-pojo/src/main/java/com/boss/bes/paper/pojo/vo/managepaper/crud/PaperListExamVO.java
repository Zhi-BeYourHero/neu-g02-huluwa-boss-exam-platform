package com.boss.bes.paper.pojo.vo.managepaper.crud;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 考试提供试卷列表VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
public class PaperListExamVO {

    /**
     * 试卷id
     * */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 试卷名字
     * */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PaperListExamVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
