package com.boss.bes.exam.dao;

import com.boss.bes.exam.model.dto.EvaluateDTO;
import com.boss.bes.exam.model.po.ExamAnswerRecord;

import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 15:19
 * @since 1.0
 */
public interface ExamAnswerRecordDao {

    /**
     * 通过考试记录ID查询考试记录详细
     * @param examRecordId 考试详细记录所属考试的ID
     * @return 返回某次考试记录的id
     * */
    public List<ExamAnswerRecord> getExamAnswerRecordById(Long examRecordId);

    /**
     * 添加考试记录
     *
     * @param examAnswerRecord 考试信息
     * @return 是否添加成功
     */
    boolean insert(ExamAnswerRecord examAnswerRecord);
    /*
     * 更新试卷记录的评价信息
     * */
    public int updateSubjectEvaluate(List<EvaluateDTO> evaluateDTOS);
}
