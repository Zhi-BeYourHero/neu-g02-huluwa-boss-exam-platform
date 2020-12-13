package com.boss.bes.paper.pojo.vo.makepaper.config;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@ToString
public class ConfigVO {

    @Getter
    @Setter
    private Long combExamId;
    /**
     * 配置名
     */
    @Getter
    @Setter
    private String name;
    /**
     * 配置难度
     * */
    @Getter
    @Setter
    private Long difficulty;
    /**
     * 备注
     */
    @Getter
    @Setter
    private String remark;
    /**
     * 修改人
     */
    @Getter
    @Setter
    private Long updatedBy;

    /**
     * 版本
     * */
    @Getter
    @Setter
    private Long version;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    public Date getUpdatedTime() {
        return (updatedTime!=null)?(Date) updatedTime.clone():null;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = (updatedTime!=null)?(Date) updatedTime.clone():null;
    }
}
