package com.boss.bes.paper.pojo.vo.managepaper.crud;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试题答案VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class SubjectAnswerItemVO {
    /**
     * id
     * */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;
    /**
     * 答案
     */
    private String answer;

    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 正确答案
     */
    private Boolean rightAnswer;

    /**
     * 状态位
     */
    private Boolean status;
    /**
     * 版本
     * */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long version;
}
