package com.boss.bes.paper.pojo.vo.managepaper.answer;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: Base答案操作VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
class BaseAnswerVO {
    /**
     * 试题id
     * */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long subjectId;
    /**
     * 答案
     */
    @NotEmpty(message = "答案不能为空")
    private String answer;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 正确答案
     */
    @NotNull(message = "正确答案标识不能为空")
    private Boolean rightAnswer;
    /**
     * 状态位
     */
    private Integer status;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
