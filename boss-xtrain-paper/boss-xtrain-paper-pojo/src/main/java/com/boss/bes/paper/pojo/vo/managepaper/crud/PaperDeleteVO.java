package com.boss.bes.paper.pojo.vo.managepaper.crud;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷删除VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class PaperDeleteVO{
    /**
     * id
     */
    @NotNull
    private List<Long> id;
    /**
     * version
     * */
    @NotNull
    private List<Long> version;

    public List<Long> getId() {
        return id;
    }

    public void setId(List<Long> id) {
        this.id = id;
    }

    public List<Long> getVersion() {
        return version;
    }

    public void setVersion(List<Long> version) {
        this.version = version;
    }
}
