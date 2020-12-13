package com.boss.bes.basedata.pojo.vo.category;
import javax.validation.constraints.NotNull;
/**
 * 删除多个题目类别
 **/
public class DeleteCategorysVO {
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
        return "DeleteCategorysVO{" +
                "ids='" + ids + '\'' +
                '}';
    }
}
