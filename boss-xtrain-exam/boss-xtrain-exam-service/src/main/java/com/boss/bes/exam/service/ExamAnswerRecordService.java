package com.boss.bes.exam.service;

import com.boss.bes.exam.model.dto.EvaluateDTO;

import java.util.List;

public interface ExamAnswerRecordService{

    public int evaluateSubject(List<EvaluateDTO> evaluateDTOList);

}
