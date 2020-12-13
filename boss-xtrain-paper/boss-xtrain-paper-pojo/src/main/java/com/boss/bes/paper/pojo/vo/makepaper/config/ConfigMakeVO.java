package com.boss.bes.paper.pojo.vo.makepaper.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.vo.makepaper.config
 * @Description:
 * @Date: 2020/7/11 20:49
 * @since: 1.0
 */
@ToString
public class ConfigMakeVO {

    @Getter
    @Setter
    private Long id;
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
    private Date updatedTime;

    @Getter
    @Setter
    @NotNull
    private List<ConfigItemReturnVO> configItems;

    public Date getUpdatedTime() {
        return (updatedTime!=null)?(Date) updatedTime.clone():null;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = (updatedTime!=null)?(Date) updatedTime.clone():null;
    }
}
