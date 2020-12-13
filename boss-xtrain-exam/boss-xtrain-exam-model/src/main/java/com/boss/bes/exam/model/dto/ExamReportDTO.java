package com.boss.bes.exam.model.dto;

import boss.xtrain.core.data.convention.common.CommonPage;
import com.boss.bes.exam.model.vo.ExamReportDetailVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试报表数据传输对象
 * @create 2020-07-11 00:11
 * @since 1.0
 */
@Data
public class ExamReportDTO {
    /**
     * 考试标题
     */
    private String title;

    /**
     * 考试场次
     */
    private String examBatchNo;

    /**
     * 考试结束时间
     */
    private LocalDateTime endTime;

    /**
     * 计划考试人数
     */
    private Integer planPeopleNum;

    /**
     * 实际考试人数
     */
    private Integer actualPeopleNum;

    /**
     * 考试报表详细
     */
    private List<ExamReportDetailDTO> reportDetails;
}
