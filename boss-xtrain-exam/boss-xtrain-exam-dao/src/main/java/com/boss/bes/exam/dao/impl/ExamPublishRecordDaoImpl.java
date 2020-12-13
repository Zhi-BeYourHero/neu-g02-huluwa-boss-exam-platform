package com.boss.bes.exam.dao.impl;

import com.boss.bes.exam.dao.ExamPublishRecordDao;
import com.boss.bes.exam.mapper.ExamPublishRecordMapper;
import com.boss.bes.exam.model.dto.ExamPublishRecordDeleteDTO;
import com.boss.bes.exam.model.dto.ExamReviewerRelationDTO;
import com.boss.bes.exam.model.po.ExamPublishRecord;
import com.boss.bes.exam.model.query.ExamPublishRecordQueryWithId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 15:10
 * @since 1.0
 */
@Slf4j
@Repository
public class ExamPublishRecordDaoImpl implements ExamPublishRecordDao {
    @Resource
    private ExamPublishRecordMapper examPublishRecordMapper;
    /**
     * 通过条件查询指定考试记录
     * @param query 带有ID以及查询条件的查询考试记录对象
     * */
    @Override
    public List<ExamPublishRecord> queryByCondition(ExamPublishRecordQueryWithId query) {
        //调用mapper进行Dao进行查询
        Example example = new Example(ExamPublishRecord.class);
        Example.Criteria criteria = example.createCriteria();
        //考试标题约束
        if(query.getTitle() != null)
            criteria.andLike("title","%"+query.getTitle()+"%");
        //考试时间约束
        if(query.getExamFromTime() != null)
            criteria.andGreaterThanOrEqualTo("startTime",query.getExamFromTime());
        if(query.getExamToTime() != null)
            criteria.andLessThan("endTime",query.getExamToTime());
        //考试时间约束
        if(query.getPublishToTime() != null)
            criteria.andLessThan("createdTime",query.getPublishToTime());
        if(query.getPublishFromTime() != null)
            criteria.andGreaterThan("createdTime",query.getPublishFromTime());
        //id范围约束
        if(query.getUserIds() != null)
            criteria.andIn("publisher",query.getUserIds());
        criteria.andEqualTo("companyId",query.getCompanyId());
        return examPublishRecordMapper.selectByExample(example);
    }
    /**
     * 通过考试ID查询考试评卷管ID列表
     * @param examId 考试ID
     * @return ID列表
     * */
    @Override
    public List<Long> queryAllReviewer(Long examId) {
        return examPublishRecordMapper.getAllReviewer(examId);
    }

    /**
     * 添加考试记录
     * @param examPublishRecord 考试记录entity
     * @return 返回受影响的记录条数，用来判断是否插入成功
     * */
    @Override
    public int insertExam(ExamPublishRecord examPublishRecord) {
        return examPublishRecordMapper.insert(examPublishRecord);
    }

    /**
     * 插入考试和评卷管关系表
     *
     * @param examReviewerRelationDTOS id关系列表
     * @return 返回受影响的记录条数
     */
    @Override
    public int insertReviewerRelation(List<ExamReviewerRelationDTO> examReviewerRelationDTOS) {
        return examPublishRecordMapper.insertReviewerRelation(examReviewerRelationDTOS);
    }

    /**
     * 删除考试发布记录
     *
     * @param deleteDTO 考试记录
     * @return 返回删除记录条数
     */
    @Override
    public int deletePublishRecord(ExamPublishRecordDeleteDTO deleteDTO) {
        Example example = new Example(ExamPublishRecord.class);
        Example.Criteria criteria = example.createCriteria();
        //添加version以及发布状态比较
        criteria.andEqualTo("id",deleteDTO.getId());
        criteria.andEqualTo("version",deleteDTO.getVersion());
        criteria.andEqualTo("status",0);
        return examPublishRecordMapper.deleteByExample(example);
    }

    /**
     * 通过id查询考试发布记录
     *
     * @param id
     */
    @Override
    public ExamPublishRecord getExamPublishRecordById(Long id) {
        return examPublishRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过考试ID删除考试记录
     *
     * @param id
     */
    @Override
    public int deleteReviewerRelation(Long id) {
        return examPublishRecordMapper.deleteReviewerRelation(id);
    }

    /**
     * 发布考试
     *
     * @param examPublishRecord
     */
    @Override
    public int publishExam(ExamPublishRecord examPublishRecord) {
        return examPublishRecordMapper.publishExam(examPublishRecord);
    }

    /**
     * 更新考试
     * 通过传入的尸体类对象更新数据库记录
     * @param examPublishRecord
     */
    @Override
    public int updateExam(ExamPublishRecord examPublishRecord) {
        return examPublishRecordMapper.updateExam(examPublishRecord);
    }
    /**
     * 修改考试状态为过期阅卷过期
     *
     * @param id
     */
    @Override
    public int setReviewTimeOut(Long id) {
        return examPublishRecordMapper.setReviewTimeOut(id);
    }
    /**
     * 通过指定条件获取考试发布记录的id
     *
     * @param examPublishRecord 指定条件实体类对象
     * @return 考试发布记录的id
     */
    @Override
    public List<ExamPublishRecord> getId(ExamPublishRecord examPublishRecord) {
        Example example = new Example(ExamPublishRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if (examPublishRecord.getTitle() != null) {
            criteria.andLike("title", examPublishRecord.getTitle());
        }
        if (examPublishRecord.getExamBatchNo() != null) {
            criteria.andLike("examBatchNo", examPublishRecord.getExamBatchNo());
        }
        if (examPublishRecord.getPublisher() != null) {
            criteria.andEqualTo("publisher", examPublishRecord.getPublisher());
        }
        if (examPublishRecord.getStartTime() != null) {
            criteria.andGreaterThanOrEqualTo("startTime", examPublishRecord.getStartTime());
        }
        if (examPublishRecord.getEndTime() != null) {
            criteria.andLessThanOrEqualTo("endTime", examPublishRecord.getEndTime());
        }
        return examPublishRecordMapper.selectByExample(example);
    }

    /**
     * 通过发布考试id获取发布考试记录
     *
     * @param id 发布考试id
     * @return 发布考试记录
     */
    @Override
    public ExamPublishRecord getById(Long id) {
        return examPublishRecordMapper.getById(id);
    }

    /**
     * 通过发布考试id获取考试试卷id
     *
     * @param id 发布考试id
     * @return 考试试卷id
     */
    @Override
    public ExamPublishRecord getPaperIdById(Long id) {
        return examPublishRecordMapper.getPaperIdById(id);
    }

    /**
     * 通过试卷id获取发布考试id
     *
     * @param id 考试试卷id
     * @return 发布考试id
     */
    @Override
    public ExamPublishRecord getByPaperId(Long id) {
        return examPublishRecordMapper.getByPaperId(id);
    }

    /**
     * 通过考试ID获取阅卷人ID列表
     *
     * @param examId 考试ID
     * @return 阅卷人Id列表
     */
    @Override
    public List<Long> getReviewsByExamId(Long examId) {
        return examPublishRecordMapper.getExamReviewById(examId);
    }
}
