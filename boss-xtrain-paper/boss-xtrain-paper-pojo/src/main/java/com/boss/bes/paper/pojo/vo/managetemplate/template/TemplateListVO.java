package com.boss.bes.paper.pojo.vo.managetemplate.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.ToString;
import lombok.Setter;
import lombok.Getter;

import java.util.Date;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 模板列表VO
 * @Date: 2020/7/7 8:25
 * @since: 1.0
 */
@ToString
public class TemplateListVO {
    /**
     * 试卷id
     */
    @Getter
    @Setter
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long id;
    /**
     * 试卷名
     */
    @Getter
    @Setter
    private String name;

    /**
     * 修改人
     * */
    @Getter
    @Setter
    private String updatedBy;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    /**
     * 试卷描述
     */
    @Getter
    @Setter
    private String discript;
    /**
     * 状态
     * */
    @Getter
    @Setter
    private Long status;
    /**
     * 公司
     * */
    @Getter
    @Setter
    private String companyId;

    /**
     *版本
     * */
    @Getter
    @Setter
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long version;

    /**
     * 下载次数
     */
    @Getter
    @Setter
    private Integer downloadTimes;

    public Date getUpdatedTime() {
        return (updatedTime!=null)?(Date) updatedTime.clone():null;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = (updatedTime!=null)?(Date) updatedTime.clone():null;
    }
}

