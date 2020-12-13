package com.boss.bes.exam.service;

import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.query.ExamRecordQuery;
import com.boss.bes.exam.model.query.PaperQuery;
import com.boss.bes.exam.service.api.model.dto.PaperSubjectDTO;
import com.boss.bes.exam.service.api.model.po.PaperSubject;
import com.boss.bes.exam.service.api.model.vo.PaperContentExamVO;

import java.util.List;
import java.util.Map;

/**
 * @author fuhaifei
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用来用来查询为评卷提供服务的service
 * @create 2020-07-06 21:00
 * @since 1.0
 */
public interface ExamRecordService{

    /**
     * 通过条件查询指定考试记录信息
     * */
    public List<ExamRecordQueryResultDTO> getExamRecordByCondition(ExamRecordQuery query);


    /**
     * 通过考试记录ID查询考试记录详细
     * */
    public Map<Long,ExamAnswerRecordDTO> getAllExamAnswerRecord(Long examRecordId);

    /**
     * 传输评卷结果，用于临时存储分题评卷的评卷结果以及暂存评卷结果
     *
     * */
    public void updateExamEvaluation(List<EvaluateDTO> evaluateDTOS);
    /**
     * 通过试卷Id获取缓存的答题信息
     * */
    public Map<Long,EvaluateDTO> getEvaluate(Long id);
    /**
     * 按照题型方式进行评卷，获取题型
     * */
    public List<PaperSubjectToEvaluateDTO> getPaperSubject(SubjectTypeEvaluateDTO subjectTypeEvaluateDTO);
    /**
     * 按照提醒方式提交试卷
     * */
    public void submitEvaluateByType(List<EvaluateDTO> evaluateDTOS);
    /**
     * 组合试卷返回评价试卷
     * */
    public PaperToEvaluateDTO getPaper(PaperQuery paperQuery);

    /**
     *  提交评价结果
     * */
    public void submitEvaluation(EvaluateSubmitDTO evaluateSubmitDTO);

    /**
     * 更新考试状态为评卷过期
     * */
    public int setExamRecordReviewTimeout(Long examId);

    /**
     * 获取所有主观题信息
     * */
    public List<String> getAllSubjectType();

    /**
     * 获取所有指定类型题目
     * */
    public Map<Long, PaperSubjectDTO> getAllPaperSubjectByType(List<Long> paperIds, String subjectType);
    /**
     * 查询Paper
     * */
    public PaperToEvaluateDTO getPaperInformation(Long paperId);
    /**
     * 将考试服务试题dto转换为本系统试题DTO
     * */
    PaperSubjectToEvaluateDTO convertToPaperSubject(PaperSubjectDTO origin);
}
