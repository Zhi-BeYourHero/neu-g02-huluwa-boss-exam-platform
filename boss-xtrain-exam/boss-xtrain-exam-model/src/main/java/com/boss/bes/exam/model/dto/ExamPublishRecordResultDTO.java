package com.boss.bes.exam.model.dto;
import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 09:06
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamPublishRecordResultDTO extends BaseDTO {
    /**
     * 考试ID
     * */
    private Long id;
    /**
     * 考试名称
     * */
    private String title;
    /**
     * 考试批次号
     * */
    private String examBatchNo;
    /**
     * 考试发布人姓名
     * */
    private String publisherName;
    /**
     * 考试开始时间
     */
    private LocalDateTime startTime;

    /**
     * 考试结束时间
     */
    private LocalDateTime endTime;

    /**
     * 计划人数
     */
    private Integer planPeopleNum;
    /**
     * 考试时长
     */
    private Integer limitTime;
    /**
     * 考试说明
     */
    private String descript;
    /**
     * 阅卷停止时间
     */
    private LocalDateTime markingStopTime;
    /**
     * 二维码链接
     */
    private String qrcodeUrl;
    /**
     * 试卷ID
     */
    private Long tPId;
    /**
     * 试卷试卷名称
     */
    private String paperName;
    /**
     * 阅卷人map
     * */
    private Map<Long,String> reviewers;
    /**
     * 阅卷方式（平均分配、随机分配）
     */
    private Long markingMode;
}
