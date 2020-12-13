package com.boss.bes.exam.dao;

import com.boss.bes.exam.model.dto.ExamPublishRecordDeleteDTO;
import com.boss.bes.exam.model.dto.ExamReviewerRelationDTO;
import com.boss.bes.exam.model.po.ExamPublishRecord;
import com.boss.bes.exam.model.query.ExamPublishRecordQueryWithId;
import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 15:09
 * @since
 */
public interface ExamPublishRecordDao {
    /**
     * 通过条件查询指定考试记录
     * @param query 带有ID以及查询条件的查询考试记录对象
     * */
    public List<ExamPublishRecord> queryByCondition(ExamPublishRecordQueryWithId query);
    /**
     * 通过考试ID查询考试评卷管ID列表
     * @param examId 考试ID
     * @return ID列表
     * */
    public List<Long> queryAllReviewer(Long examId);
    /**
     * 添加考试记录
     * @param examPublishRecord 考试记录entity
     * @return 返回受影响的记录条数，用来判断是否插入成功
     * */
    public int insertExam(ExamPublishRecord examPublishRecord);
    /**
     * 插入考试和评卷管关系表
     * @param examReviewerRelationDTOS id关系列表
     * @return 返回受影响的记录条数
     * */
    public int insertReviewerRelation(List<ExamReviewerRelationDTO> examReviewerRelationDTOS);

    /**
     * 删除考试发布记录
     *
     * @param deleteDTO 考试记录
     * @return 返回删除记录条数
     */
    public int deletePublishRecord(ExamPublishRecordDeleteDTO deleteDTO);

    /**
     * 通过id查询考试发布记录
     *
     * */
    public ExamPublishRecord getExamPublishRecordById(Long id);

    /**
     * 通过考试ID删除考试记录
     * */
    public int deleteReviewerRelation(Long id);

    /**
     * 发布考试
     * */
    public int publishExam(ExamPublishRecord examPublishRecord);

    /**
     * 更新考试
     * */
    public int updateExam(ExamPublishRecord examPublishRecord);
    /**
     * 修改考试状态为过期
     * */
    public int setReviewTimeOut(Long id);
    /**
     * 通过指定条件获取考试发布记录的id
     *
     * @param examPublishRecord 指定条件实体类对象
     * @return 考试发布记录的id
     */
    List<ExamPublishRecord> getId(ExamPublishRecord examPublishRecord);

    /**
     * 通过发布考试id获取发布考试记录
     *
     * @param id 发布考试id
     * @return 考试截止时间
     */
    ExamPublishRecord getById(Long id);

    /**
     * 通过发布考试id获取考试试卷id
     *
     * @param id 发布考试id
     * @return 考试试卷id
     */
    ExamPublishRecord getPaperIdById(Long id);

    /**
     * 通过试卷id获取发布考试记录
     *
     * @param id 考试试卷id
     * @return 发布考试记录
     */
    ExamPublishRecord getByPaperId(Long id);
    /**
     * 通过考试ID获取阅卷人ID列表
     * @param examId 考试ID
     * @return 阅卷人Id列表
     * */
    List<Long> getReviewsByExamId(Long examId);
}
