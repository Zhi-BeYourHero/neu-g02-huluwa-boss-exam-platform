package com.boss.bes.paper.pojo.dto.makepaper;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 配置参数DTO
 * @Date: 2020/7/8 8:25
 * @since: 1.0
 */
@ToString
public class ConfigDTO {
    /**
     * 配置id
     * */
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
     * 修改人
     */
    @Getter
    @Setter
    private Long updatedBy;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    /**
     * 难度ID
     */
    @Getter
    @Setter
    private Long difficulty;



    public Date getUpdateTime(){
        return (updatedTime!=null)?(Date) updatedTime.clone():null;
    }

    public void setUpdatedTime(Date updatedTime){
        this.updatedTime=(updatedTime!=null)?(Date)updatedTime.clone():null;
    }

}
