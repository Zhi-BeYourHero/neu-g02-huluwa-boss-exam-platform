package com.boss.bes.paper.pojo.vo.updownpaper;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 上传试卷VO
 * @Date: 2020/7/7 8:25
 * @since: 1.0
 */
@Data
public class PaperUploadVO {
    /**
     * 试卷id
     * */
    @NotNull
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;
    /**
     * 模板名称
     * */
    @NotEmpty(message = "模板名称不能为空")
    private String name;
    /**
     * 试卷类型
     * */
    @NotNull(message = "试卷类别不能为空")
    private Long paperType;
    /**
     * 试卷难度
     * */
    @NotNull(message = "试卷难度不能为空")
    private Long difficulty;
    /**
     * 备注
     * */
    private String discript;
}

