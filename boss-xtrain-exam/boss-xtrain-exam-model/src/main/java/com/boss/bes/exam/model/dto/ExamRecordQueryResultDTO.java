package com.boss.bes.exam.model.dto;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 13:44
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamRecordQueryResultDTO extends BaseDTO {
    /**
     * 考试记录id
     * */
    private Long id;

    /**
     * 交卷时间
     * */
    private LocalDateTime actualEndTime;
    /**
     * 客观题得分
     * */
    private BigDecimal objectiveSubjectScore;
    /**
     * 主观题得分
     * */
    private BigDecimal subjectiveSubjectScore;
    /**
     * 系统评价(能力标签)
     */
    private String systemEvaluate;
    /**
     * 状态
     * */
    private Integer status;
    /**
     * 试卷ID
     * */
    private Long tPId;
    /**
     * 额外增加用来显示试卷名称作用域
     * */
    private String paperName;
    /**
     * 考试批次号
     * */
    private String examBatchNo;
    /**
     * 发布日期
     * */
    private LocalDateTime publishTime;

    /**阅卷截至时间*/
    private LocalDateTime markStopTime;
    /**
     * 答卷人名称
     * */
    private String name;
    /**
     * 答卷人电话号码
     * */
    private String mobile;
}
