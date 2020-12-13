package com.boss.bes.exam.dao;

import com.boss.bes.exam.model.dto.EvaluateSubmitDTO;
import com.boss.bes.exam.model.po.ExamRecord;
import com.boss.bes.exam.model.po.ExamRecordQueryResult;
import com.boss.bes.exam.model.query.ExamRecordQuery;

import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-10 18:10
 * @since 1.0
 */
public interface ExamRecordDao {
    /**
     * 通过发布考试记录的id获取考试记录信息
     *
     * @param ids 发布考试记录的id
     * @return 考试记录信息
     */
    List<ExamRecord> getById(List<Long> ids);
     /**
     * 通过发布考试记录id获取参加考试人数
     *
     * @param id 发布考试记录id
     * @return 参加考试人数
     */
    int getExamPeopleNum(long id);

    /**
     * 通过组合查询条件获取复合数据
     * @param query 查询条件
     * */
    List<ExamRecordQueryResult> getExamRecordByCondition(ExamRecordQuery query);

    /**
     * 插入考试记录
     *
     * @param examRecord 考试记录信息
     * @return 是否插入成功
     */
    boolean insert(ExamRecord examRecord);

    /*
     * 通过考试记录ID以及题目ID查询对应的考试详细记录
     * */
    ExamRecord getAimExamAnswerRecord(Long recordId,Long subjectId);

    /**
     * 更新考试记录的评卷信息
     * */
    int submitPaperEvaluation(EvaluateSubmitDTO evaluateSubmitDTO);

    /**
     * 更新能力标签
     *
     * @param id 考试记录id
     * @param tag 能力标签
     * @return 是否更新成功
     */
    boolean updateTag(Long id, String tag);

    /*
     * 设置考试发布记录的考试记录均为超时
     * */
    int setExamRecordReviewTimeout(Long examId);

    /**
     * 通过考试记录ID查询考试记录
     * */
    ExamRecord getExamRecordById(Long recordId);
}
