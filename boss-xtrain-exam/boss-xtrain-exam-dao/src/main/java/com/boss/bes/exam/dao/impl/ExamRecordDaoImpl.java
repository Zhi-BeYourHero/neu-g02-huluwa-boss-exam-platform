package com.boss.bes.exam.dao.impl;

import com.boss.bes.exam.dao.ExamRecordDao;
import com.boss.bes.exam.mapper.ExamRecordMapper;
import com.boss.bes.exam.model.dto.EvaluateSubmitDTO;
import com.boss.bes.exam.model.po.ExamAnswerRecord;
import com.boss.bes.exam.model.po.ExamRecord;
import com.boss.bes.exam.model.po.ExamRecordQueryResult;
import com.boss.bes.exam.model.query.ExamRecordQuery;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-10 18:10
 * @since 1.0
 */
@Repository
public class ExamRecordDaoImpl implements ExamRecordDao {

    @Resource
    private ExamRecordMapper examRecordMapper;

    /**
     * 通过发布考试记录的id获取考试记录信息
     *
     * @param ids 发布考试记录的id
     * @return 考试记录信息
     */
    @Override
    public List<ExamRecord> getById(List<Long> ids) {
        return examRecordMapper.getById(ids);
    }
    /**
     * 通过组合查询条件获取复合数据
     *
     * @param query
     */
    @Override
    public List<ExamRecordQueryResult> getExamRecordByCondition(ExamRecordQuery query) {
        return examRecordMapper.getExamRecordByCondition(query);
    }

    /**
     * 插入考试记录
     *
     * @param examRecord 考试记录信息
     * @return 是否插入成功
     */
    @Override
    public boolean insert(ExamRecord examRecord) {
        return examRecordMapper.insert(examRecord) > 0;
    }

    /*
     * 通过考试记录ID以及题目ID查询对应的
     *
     * @param recordId 考试记录id
     * @param subjectId 题目id
     */
    @Override
    public ExamRecord getAimExamAnswerRecord(Long recordId, Long subjectId) {
        Example example = new Example(ExamAnswerRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tRId",recordId);
        criteria.andEqualTo("tPId",subjectId);
        List<ExamRecord> examRecords = examRecordMapper.selectByExample(example);
        if(examRecords.size() != 1){
            throw new ExamException(ExamExceptionCode.EXAM_EXAM_GET_ANSWER_RECORD_ERROR.getCode(),
                    ExamExceptionCode.EXAM_EXAM_GET_ANSWER_RECORD_ERROR.getMessage());
        }
        return examRecords.get(0);
    }

    /**
     * 更新考试记录的评卷信息
     *
     * @param evaluateSubmitDTO
     */
    @Override
    public int submitPaperEvaluation(EvaluateSubmitDTO evaluateSubmitDTO) {
        //生成按照ID更新条件
        Example example = new Example(ExamRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",evaluateSubmitDTO.getExamRecordId());
        //生成更新内容
        ExamRecord examRecord = new ExamRecord();
        examRecord.setSubjectiveSubjectScore(evaluateSubmitDTO.getSubjectiveScore());
        examRecord.setScore(evaluateSubmitDTO.getScore());
        examRecord.setSystemEvaluate(evaluateSubmitDTO.getMarkingEvaluate());
        //设置为已评卷
        examRecord.setStatus(1);
        examRecordMapper.updateByExampleSelective(examRecord,example);
        return 0;
    }

    /**
     * 更新能力标签
     *
     * @param id  考试记录id
     * @param tag 能力标签
     * @return 是否更新成功
     */
    @Override
    public boolean updateTag(Long id, String tag) {
        return examRecordMapper.updateTag(id, tag) > 0;
    }

    /*
     * 设置考试发布记录的考试记录均为超时
     *
     * @param examId
     */
    @Override
    public int setExamRecordReviewTimeout(Long examId) {
        //按照ID更新条件
        Example example = new Example(ExamRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tPId",examId);
        //已经评卷完成的考试记录不需要修改为过期状态
        criteria.andNotEqualTo("status",1);
        //更新内容
        ExamRecord record = new ExamRecord();
        //设置阅卷过期
        record.setStatus(2);
        return examRecordMapper.updateByExampleSelective(record,example);
    }

    /**
     * 通过考试记录ID查询考试记录
     *
     * @param recordId
     */
    @Override
    public ExamRecord getExamRecordById(Long recordId) {
        return examRecordMapper.selectByPrimaryKey(recordId);
    }

    /**
     * 通过发布考试记录id获取参加考试人数
     *
     * @param id 发布考试记录id
     * @return 参加考试人数
     */
    @Override
    public int getExamPeopleNum(long id) {
        return examRecordMapper.getExamPeopleNum(id);
    }
}
