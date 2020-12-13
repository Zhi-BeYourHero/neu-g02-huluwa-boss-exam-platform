package com.boss.bes.basedata.pojo.vo.combexamconfig;
import javax.validation.constraints.NotNull;
/**
 * 删除多个组卷配置
 **/

public class DeleteCombExamConfigsVO {
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
        return "DeleteCombExamConfigsVO{" +
                "ids='" + ids + '\'' +
                '}';
    }
}
