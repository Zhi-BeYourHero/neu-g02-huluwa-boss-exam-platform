package com.boss.bes.basedata.pojo.vo.combexamconfig;

public class CombExamConfigListConditionVO {
    private String name;
    private Long difficulty;
    private Integer pageIndex;
    private Integer pageSize;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }
    public Integer getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    @Override
    public String toString() {
        return "CombExamConfigListConditionVO{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
