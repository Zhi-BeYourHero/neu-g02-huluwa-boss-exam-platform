package com.boss.bes.basedata.pojo.vo.category;

import lombok.Data;

@Data
public class CategoryQueryConditionVO {
    /**
     * 类别名称
     */
    private String name;
    private Integer start;
    private Integer size;
    public Integer getStart() {
        return start;
    }
    public void setStart(Integer start) {
        this.start = start;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "CategoryQueryConditionVO{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", size=" + size +
                '}';
    }
}
