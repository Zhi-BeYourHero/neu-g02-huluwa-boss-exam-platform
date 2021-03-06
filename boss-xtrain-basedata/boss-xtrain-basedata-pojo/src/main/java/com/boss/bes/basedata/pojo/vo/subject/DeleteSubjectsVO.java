package com.boss.bes.basedata.pojo.vo.subject;
import javax.validation.constraints.NotNull;
/**
 * 删除多个题目VO
 **/
public class DeleteSubjectsVO {
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
        return "DeleteSubjectsVO{" +
                "ids='" + ids + '\'' +
                '}';
    }
}
