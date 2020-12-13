package com.boss.bes.exam.dao.impl;

import com.boss.bes.exam.dao.ExamAnswerRecordDao;
import com.boss.bes.exam.mapper.ExamAnswerRecordMapper;
import com.boss.bes.exam.model.dto.EvaluateDTO;
import com.boss.bes.exam.model.po.ExamAnswerRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试详细
 * @create 2020-07-11 15:20
 * @since 1.0
 */
@Repository
public class ExamAnswerRecordDaoImpl implements ExamAnswerRecordDao {
    @Resource
    ExamAnswerRecordMapper examAnswerRecordMapper;
    /**
     * 通过考试记录ID查询考试记录详细
     *
     * @param examRecordId 考试详细记录所属考试的ID
     * @return 返回某次考试记录的id
     */
    @Override
    public List<ExamAnswerRecord> getExamAnswerRecordById(Long examRecordId) {
        Example example = new Example(ExamAnswerRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tRId",examRecordId);
        return examAnswerRecordMapper.selectByExample(example);
    }

    /**
     * 添加考试记录
     *
     * @param examAnswerRecord 考试信息
     * @return 是否添加成功
     */
    @Override
    public boolean insert(ExamAnswerRecord examAnswerRecord) {
        return examAnswerRecordMapper.insert(examAnswerRecord) > 0;
    }
    /*
     * 更新试卷记录的评价信息
     *
     * @param evaluateDTOS
     */
    @Override
    public int updateSubjectEvaluate(List<EvaluateDTO> evaluateDTOS) {
        int result = 0;
        for (EvaluateDTO evaluateDTO:evaluateDTOS){
            result += examAnswerRecordMapper.updateSubjectEvaluation(evaluateDTO);
        }
        return result;
    }
}
