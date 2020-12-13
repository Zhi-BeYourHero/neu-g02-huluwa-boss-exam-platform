package com.boss.bes.exam.model.vo;

import boss.xtrain.core.annotation.excel.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试报表详细视图对象
 * @create 2020-07-11 09:09
 * @since 1.0
 */
@Data
public class ExamReportDetailVO {

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 性别（0代表女，1代表男）
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * 考试标题
     */
    @Excel(name = "考试名称")
    private String title;

    /**
     * 主观题得分
     */
    @Excel(name = "主观题得分", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal subjectiveSubjectScore;

    /**
     * 客观题得分
     */
    @Excel(name = "客观题得分", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal objectiveSubjectScore;

    /**
     * 总分
     */
    @Excel(name = "总分", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal score;

    /**
     * 排名
     */
    @Excel(name = "排名", cellType = Excel.ColumnType.NUMERIC)
    private Long ranking;

    /**
     * 考试耗时
     */
    @Excel(name = "考试耗时", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalTime timeConsuming;

    /**
     * 系统评价(能力标签)
     */
    @Excel(name = "能力标签")
    private String systemEvaluate;

}
