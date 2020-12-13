package com.boss.bes.exam.controller;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.exam.api.ExamReportApi;
import com.boss.bes.exam.model.dto.ExamReportDTO;
import com.boss.bes.exam.model.dto.ExamReportDetailDTO;
import com.boss.bes.exam.model.query.ExamReportQuery;
import com.boss.bes.exam.model.vo.ExamReportDetailVO;
import com.boss.bes.exam.model.vo.ExamReportVO;
import com.boss.bes.exam.service.ExamReportService;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import com.boss.bes.exam.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-06 21:00
 * @since 1.0
 */
@Api(value = "考试报表模块")
@ApiLog(msg = "考试报表模块日志")
@CrossOrigin
@RestController
public class ExamReportController implements ExamReportApi {

    @Resource
    private ExamReportService examReportService;

    /**
     * 答卷查询
     *
     * @param request 通用请求
     * @return 答卷查询结果
     */
    @Override
    @ApiOperation("考试报表接口")
    @PreAuthorize("@ss.hasAnyPermi('reportQuery')")
    public CommonPage<ExamReportVO> examReport(CommonRequest<ExamReportQuery> request) {
        CommonPage<ExamReportDTO> dtos = examReportService.examReport(request.getBody());
        CommonPage<ExamReportVO> vos = new CommonPage<>();
        List<ExamReportVO> examReportVos = new ArrayList<>();
        for (ExamReportDTO dto : dtos.getRows()) {
            ExamReportVO reportVO = BeanUtil.copyProperties(dto, ExamReportVO.class);
            List<ExamReportDetailDTO> details = dto.getReportDetails();
            List<ExamReportDetailVO> detailVos = new ArrayList<>();
            for (ExamReportDetailDTO detail : details) {
                ExamReportDetailVO detailVO = BeanUtil.copyProperties(detail, ExamReportDetailVO.class);
                boolean sex = detail.getSex();
                if (sex) {
                    detailVO.setSex("男");
                } else {
                    detailVO.setSex("女");
                }
                detailVO.setTimeConsuming(LocalTime.ofSecondOfDay(detail.getTimeConsuming()));
                detailVos.add(detailVO);
            }
            reportVO.setReportDetails(detailVos);
            examReportVos.add(reportVO);
        }
        vos.setPageIndex(dtos.getPageIndex());
        vos.setPageSize(dtos.getPageSize());
        vos.setPages(dtos.getPages());
        vos.setTotal(dtos.getTotal());
        vos.setRows(examReportVos);
        return vos;
    }

    /**
     * 导出报表
     *
     * @param response 响应
     * @param reportVO 报表详细
     */
    @Override
    @ApiOperation("导出报表接口")
    @PreAuthorize("@ss.hasAnyPermi('reportQuery')")
    public void export(CommonRequest<ExamReportVO> reportVO, HttpServletResponse response) {
        try {
            ExcelUtil<ExamReportDetailVO> util = new ExcelUtil<>(ExamReportDetailVO.class);
            util.exportExcel(response, reportVO.getBody().getReportDetails(), "考试报表");
        } catch (IOException e) {
            throw new ExamException(ExamExceptionCode.EXPORT_ERROR);
        }
    }
}
