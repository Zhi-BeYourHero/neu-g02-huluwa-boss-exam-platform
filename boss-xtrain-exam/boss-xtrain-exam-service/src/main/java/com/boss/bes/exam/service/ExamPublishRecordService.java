package com.boss.bes.exam.service;

import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.po.ExamPublishRecord;
import com.boss.bes.exam.model.query.ExamPublishRecordQuery;

import java.util.List;
import java.util.Map;

//import io.swagger.models.auth.In;

public interface ExamPublishRecordService{
    /**
     * 获取考试发布记录相关信息
     * */
    public List<ExamPublishRecordResultDTO> getPublishRecord(ExamPublishRecordQuery queryDTO);
    /**
     * 删除考试发布记录相关信息
     * */
    public int deletePublishRecord(List<ExamPublishRecordDeleteDTO> examPublishRecordDeleteDTOS);

    /**
     * 发布已经插入考试
     * */
    public int publishExam(List<PublishExamDTO> publishExamDTOS);
    /**
     * 插入考试
     * */
    public int addExam(ExamAddDTO examAddDTO);
    /**
     * 更新考试记录
     * */
    public int updateExam(ExamUpdateDTO examUpdateDTO);
    /**
     * 插入考试和阅卷关联表
     */
    public void insertRelation(List<Long> reviewerIds,Long id);

    /**
     * 获取所有阅卷人信息
     * */
    public Map<Long,String> getAllReviewer();

    /**
     * 获取所有试卷信息
     * */
    public Map<Long,String> getAllPaper(Long companyId);

    /**
     * 获取所有试卷，不通过公司Id
     * */
    public Map<Long,String> getAllPaperWithoutCompany();
    /**
     * 更新考试记录为停止阅卷
     * */
    public int setReviewTimeOut(Long id);

    /**
     * 通过id获取考试发布记录
     *
     * @param id id
     * @return 考试发布记录
     */
    ExamPublishRecordResultDTO getById(Long id);

    /**
     * 通过id获取考试试卷id
     *
     * @param id id
     * @return 考试试卷id
     */
    ExamInfoDTO getPaperIdById(Long id);

    /**
     * 测试考试发布次数加一方法
     * fhf
     * */
    Boolean publishPaper(Long id);

}
