package com.boss.bes.paper.pojo.vo.managepaper.crud;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 考试试卷细节VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class PaperDetailExamVO {

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
     * 试题列表
     * */
    private List<PaperSubjectItemVO> subjects1;

    /**
     * 试题列表
     * */
    private List<PaperSubjectItemVO> subjects2;


}

