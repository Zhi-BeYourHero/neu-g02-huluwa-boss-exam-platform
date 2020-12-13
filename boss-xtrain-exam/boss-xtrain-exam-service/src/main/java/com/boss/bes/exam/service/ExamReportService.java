package com.boss.bes.exam.service;

import boss.xtrain.core.data.convention.common.CommonPage;
import com.boss.bes.exam.model.dto.AnswerPaperDTO;
import com.boss.bes.exam.model.dto.ExamReportDTO;
import com.boss.bes.exam.model.query.AnswerPaperQuery;
import com.boss.bes.exam.model.query.ExamReportQuery;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试报表服务接口
 * @create 2020-07-11 09:35
 * @since 1.0
 */
public interface ExamReportService {

    /**
     * 考试报表
     *
     * @param query 查询条件对象
     * @return 查询结果
     */
    CommonPage<ExamReportDTO> examReport(ExamReportQuery query);

}
