package com.boss.bes.exam.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.exam.model.dto.ExamReviewerRelationDTO;
import com.boss.bes.exam.model.po.ExamPublishRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 16:18
 * @since 1.0
 */
@Mapper
public interface ExamPublishRecordMapper extends CommonMapper<ExamPublishRecord> {

    public List<Long> getAllReviewer(Long examId);

    public int insertReviewerRelation(@Param("examReviewerRelationDTOS")List<ExamReviewerRelationDTO> examReviewerRelationDTOS);

    public int deleteReviewerRelation(Long examId);

    public int publishExam(ExamPublishRecord examPublishRecord);

    public int updateExam(ExamPublishRecord examPublishRecord);

    ExamPublishRecord getById(Long id);

    public int setReviewTimeOut(@Param("id") Long id);

    ExamPublishRecord getPaperIdById(Long id);

    ExamPublishRecord getByPaperId(Long id);

    List<Long> getExamReviewById(Long ID);

}