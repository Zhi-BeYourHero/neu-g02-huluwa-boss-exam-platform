package com.boss.bes.exam.service.api.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.boss.bes.exam.service.api.model.dto.PaperSubjectDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.vo.managepaper.crud
 * @Description:
 * @Date: 2020/7/14 18:10
 * @since: 1.0
 */
@Data
public class PaperContentExamVO {
    /**
     * 试卷id
     * */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;
    /**
     * 试卷名
     */
    private String name;
    /**
     * 试卷类型
     */
    private Long paperType;
    /**
     * 试卷难度
     */
    private Long difficulty;
    /**
     * 试卷总分
     */
    private BigDecimal score;
    /**
     * 试卷描述
     */
    private String discript;
    /**
     * 状态
     * */
    private Integer status;
    /**
     *版本
     * */
    private Long version;
    /**
     * 主观试题列表
     * */
    private List<PaperSubjectDTO> subjects1;

    /**
     * 客观题列表
     */
    private List<PaperSubjectDTO> subjects2;
}
