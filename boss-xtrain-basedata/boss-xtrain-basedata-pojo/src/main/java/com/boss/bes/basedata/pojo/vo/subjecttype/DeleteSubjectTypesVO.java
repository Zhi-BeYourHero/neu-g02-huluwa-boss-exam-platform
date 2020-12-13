package com.boss.bes.basedata.pojo.vo.subjecttype;
import javax.validation.constraints.NotNull;

public class DeleteSubjectTypesVO {
    /**
     * 题目类别ID字符串，以逗号隔开
     */
    @NotNull
    private String ids;
    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    }
    @Override
    public String toString() {
        return "DeleteSubjectTypesVO{" +
                "ids='" + ids + '\'' +
                '}';
    }
}
