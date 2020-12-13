package com.boss.bes.exam.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.exam.model.dto.EvaluateDTO;
import com.boss.bes.exam.model.po.ExamAnswerRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 16:18
 * @since 1.0
 */
@Mapper
public interface ExamAnswerRecordMapper extends CommonMapper<ExamAnswerRecord> {

    /**
     *  将评卷记录更新到评卷记录表中
     *  按照 试题ID比较的方式进行更新 更新内容为得分和评价
     */
    public int updateSubjectEvaluation(EvaluateDTO evaluateDTO);
}