package com.boss.bes.exam.service.impl;

import com.boss.bes.exam.dao.ExamAnswerRecordDao;
import com.boss.bes.exam.model.dto.EvaluateDTO;
import com.boss.bes.exam.service.ExamAnswerRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ExamAnswerRecordServiceImpl implements ExamAnswerRecordService{
    @Resource
    private ExamAnswerRecordDao examAnswerRecordDao;

    @Override
    public int evaluateSubject(List<EvaluateDTO> evaluateDTOList) {
        return 0;
    }

}
