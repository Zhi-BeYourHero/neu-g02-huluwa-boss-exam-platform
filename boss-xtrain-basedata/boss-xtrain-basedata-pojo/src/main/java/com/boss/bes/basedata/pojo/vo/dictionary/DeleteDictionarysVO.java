package com.boss.bes.basedata.pojo.vo.dictionary;

import javax.validation.constraints.NotNull;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 删除多个字典
 * @create 2020-07-08 22:37
 * @since
 */
public class DeleteDictionarysVO {
    /**
     * ID字符串，以逗号隔开
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
        return "DeleteDictionarysVO{" +
                "ids='" + ids + '\'' +
                '}';
    }
}
