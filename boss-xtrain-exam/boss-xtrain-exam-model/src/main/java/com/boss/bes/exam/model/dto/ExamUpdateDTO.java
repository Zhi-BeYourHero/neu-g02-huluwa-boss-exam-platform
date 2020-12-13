package com.boss.bes.exam.model.dto;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-10 14:35
 * @since 1.0
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamUpdateDTO extends BaseDTO {
    /**
     * 发布考试记录ID
     * */
    @NotNull(message = "发布考试ID不能为空")
    private Long id;

    /**
     * 发布考试记录的version
     * */
    @NotNull(message = "考试信息版本不能为空")
    private Long version;
    /**
     * 考试标题
     * */
    @NotBlank(message = "考试名称不能为空")
    private String title;

    /**
     * 试卷id
     * */
    @NotNull(message = "试卷Id不能为空")
    private Long tPId;

    /**
     * 阅卷官ID
     * */
    @NotNull(message = "阅卷官ID不能为空")
    private List<Long> reviewerId;

    /**
     * 阅卷方式（试卷归类，题型归类）
     */
    @NotNull(message = "阅卷分配方式不能未空")
    private Long markingMode;
    /**
     * 阅卷停止时间
     */
    @NotNull(message = "阅卷停止时间不能为空")
    @Future(message = "评卷结束时间必须为未来时间")
    private LocalDateTime markingStopTime;

    /**
     * 考试开始时间
     */
    @NotNull(message = "阅卷分配方式不能未空")
    @Future(message = "考试开始时间必须为未来时间")
    private LocalDateTime startTime;

    /**
     * 考试结束时间
     */
    @NotNull(message = "考试结束时间不能为空")
    @Future(message = "考试结束时间必须为未来时间")
    private LocalDateTime endTime;

    /**
     * 考试限制时间
     * */
    @NotNull(message = "考试时间不能为空")
    @Min(value = 2,message = "考试时间至少为30分钟")
    private Integer limitTime;

    /**
     * 计划人数
     */
    @NotNull(message = "计划人数不能为空")
    @Min(value = 10,message = "计划人数至少为10人")
    private Integer planPeopleNum;

    /**
     * 描述
     * */
    private String descript;
}
