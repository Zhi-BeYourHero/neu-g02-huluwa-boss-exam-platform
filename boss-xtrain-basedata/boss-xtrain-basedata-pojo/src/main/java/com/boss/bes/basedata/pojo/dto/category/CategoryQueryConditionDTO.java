package com.boss.bes.basedata.pojo.dto.category;
import java.io.Serializable;

public class CategoryQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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
        return "CategoryQueryConditionDTO{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", size=" + size +
                '}';
    }
}
