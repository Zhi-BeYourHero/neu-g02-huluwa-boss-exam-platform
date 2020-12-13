package com.boss.bes.exam.api;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.exam.model.query.ExamReportQuery;
import com.boss.bes.exam.model.vo.ExamReportDetailVO;
import com.boss.bes.exam.model.vo.ExamReportVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试报表接口
 * @create 2020-07-11 00:12
 * @since 1.0
 */
@RequestMapping("education/bes/v1/exam")
public interface ExamReportApi {

    /**
     * 答卷查询
     *
     * @param request 通用请求
     * @return 答卷查询结果
     */
    @PostMapping("/report")
    CommonPage<ExamReportVO> examReport(@RequestBody @Validated CommonRequest<ExamReportQuery> request);

    /**
     * 导出报表
     *
     * @param response 响应
     * @param reportVO 报表详细
     */
    @PostMapping("/export")
    void export(@RequestBody CommonRequest<ExamReportVO> reportVO, HttpServletResponse response);
}
