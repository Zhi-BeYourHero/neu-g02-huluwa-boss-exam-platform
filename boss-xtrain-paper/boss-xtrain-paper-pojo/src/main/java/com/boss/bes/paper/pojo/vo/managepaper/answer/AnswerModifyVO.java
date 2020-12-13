package com.boss.bes.paper.pojo.vo.managepaper.answer;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 答案修改VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AnswerModifyVO extends BaseAnswerVO{

    /**
     * 答案id
     * */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 版本
     * */
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
