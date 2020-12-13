package com.boss.bes.exam.service;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.exam.model.dto.AnswerPaperDTO;
import com.boss.bes.exam.model.dto.ExamPaperAnswerDTO;
import com.boss.bes.exam.model.dto.ExamPaperDTO;
import com.boss.bes.exam.model.dto.ExamPaperSubjectDTO;
import com.boss.bes.exam.model.query.AnswerPaperQuery;
import com.boss.bes.exam.service.api.model.vo.PaperContentExamVO;

import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 答卷查询服务
 * @create 2020-07-10 17:54
 * @since 1.0
 */
public interface AnswerPaperService {

    /**
     * 答卷查询
     *
     * @param query 查询对象
     * @return 查询结果
     */
    CommonPage<AnswerPaperDTO> answerQuery(AnswerPaperQuery query);

    /**
     * 提交答卷
     *
     * @param paperAnswerDTO 试卷信息
     * @return 是否提交成功
     */
    boolean submitPaper(ExamPaperAnswerDTO paperAnswerDTO);

    /**
     * 获取试卷信息
     *
     * @return 试卷信息
     */
    List<ExamPaperSubjectDTO> paperInfo(Long paperId);
}
