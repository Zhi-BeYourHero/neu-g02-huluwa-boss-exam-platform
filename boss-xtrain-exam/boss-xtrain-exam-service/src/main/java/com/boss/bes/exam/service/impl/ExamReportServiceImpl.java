package com.boss.bes.exam.service.impl;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.util.convert.BeanException;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.exam.dao.ExamPublishRecordDao;
import com.boss.bes.exam.dao.ExamRecordDao;
import com.boss.bes.exam.dao.ExamUserDao;
import com.boss.bes.exam.model.dto.ExamReportDTO;
import com.boss.bes.exam.model.dto.ExamReportDetailDTO;
import com.boss.bes.exam.model.po.ExamPublishRecord;
import com.boss.bes.exam.model.po.ExamRecord;
import com.boss.bes.exam.model.po.ExamUser;
import com.boss.bes.exam.model.query.ExamReportQuery;
import com.boss.bes.exam.service.ExamReportService;
import com.boss.bes.exam.service.api.SystemFeignService;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static boss.xtrain.util.convert.BeanUtil.*;
import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试报表服务实现类
 * @create 2020-07-11 09:36
 * @since 1.0
 */
@Service
public class ExamReportServiceImpl implements ExamReportService {

    @Resource
    private ExamPublishRecordDao examPublishRecordDao;

    @Resource
    private ExamRecordDao examRecordDao;

    @Resource
    private ExamUserDao examUserDao;

    @Resource
    private SystemFeignService systemFeignService;
    /**
     * 考试报表
     *
     * @param query 查询条件对象
     * @return 查询结果
     */
    @Override
    public CommonPage<ExamReportDTO> examReport(ExamReportQuery query) {
        CommonPage<ExamReportDTO> commonPage = new CommonPage<>();
        try {
            // 获取所有符合条件的发布考试记录
            ExamPublishRecord publishRecord = copyProperties(query, ExamPublishRecord.class);
            if (query.getPublisher() != null) {
                Map<Long, String> publishers = systemFeignService.getAllPublisher("");
                for (Map.Entry<Long, String> entry : publishers.entrySet()) {
                    if (entry.getValue().equals(query.getPublisher())) {
                        publishRecord.setPublisher(entry.getKey());
                        break;
                    }
                }
            }
            // 分页查询
            startPage(query.getPageIndex(), query.getPageSize());
            PageInfo<ExamPublishRecord> pageInfo = new PageInfo<>(examPublishRecordDao.getId(publishRecord));
            List<ExamPublishRecord> publishRecords = pageInfo.getList();
            // 如果没有符合条件的发布考试记录，直接返回
            if (publishRecords.isEmpty()) {
                commonPage.setRows(new ArrayList<>());
                return commonPage;
            }
            // 查询考试对应的考试记录
            List<ExamReportDTO> result = new ArrayList<>();
            for (ExamPublishRecord record : publishRecords) {
                List<Long> id = new ArrayList<>();
                id.add(record.getId());
                // 考试记录
                List<ExamRecord> examRecords = examRecordDao.getById(id);
                ExamReportDTO reportDTO = BeanUtil.copyProperties(record, ExamReportDTO.class);
                List<ExamReportDetailDTO> detailDtoS = new ArrayList<>();
                for (int i = 0; i < examRecords.size(); i++) {
                    ExamRecord examRecord = examRecords.get(i);
                    ExamReportDetailDTO detailDTO = convert(examRecord, reportDTO.getTitle(), (i + 1));
                    detailDtoS.add(detailDTO);
                }
                reportDTO.setReportDetails(detailDtoS);
                reportDTO.setActualPeopleNum(examRecords.size());
                result.add(reportDTO);
            }
            // 设置分页信息
            commonPage.setPageIndex(pageInfo.getPageNum());
            commonPage.setPageSize(pageInfo.getSize());
            commonPage.setPages(pageInfo.getPages());
            commonPage.setTotal(pageInfo.getTotal());
            commonPage.setRows(result);
        } catch (BeanException e) {
            throw new ExamException(ExamExceptionCode.EXAM_REPORT_ERROR);
        }
        return commonPage;
    }

    /**
     * 考试报表详细的数据转换
     *
     * @param record 考试记录
     * @return 考试报表详细
     */
    private ExamReportDetailDTO convert(ExamRecord record, String title, long rank) {
        ExamReportDetailDTO reportDetailDTO = copyProperties(record, ExamReportDetailDTO.class);
        ExamUser examUser = examUserDao.selectById(record.getTUId());
        copyBeanProp(reportDetailDTO, examUser);
        reportDetailDTO.setRanking(rank);
        reportDetailDTO.setTitle(title);
        // 计算考试耗时
        long duration = Duration.between(record.getActualStartTime(), record.getActualEndTime()).abs().toMinutes();
        reportDetailDTO.setTimeConsuming(duration);
        return reportDetailDTO;
    }
}
