package com.boss.bes.basedata.pojo.vo.subject;

public class SubjectQueryConditionVO {
    private String name;
    /**
     * 题型名称
     */
    private String subjectTypeName;
    /**
     * 题类别
     */
    private String categoryName;
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
    public String getSubjectTypeName() {
        return subjectTypeName;
    }
    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    @Override
    public String toString() {
        return "SubjectQueryConditionVO{" +
                "name='" + name + '\'' +
                ", subjectTypeName='" + subjectTypeName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", start=" + start +
                ", size=" + size +
                '}';
    }
}
