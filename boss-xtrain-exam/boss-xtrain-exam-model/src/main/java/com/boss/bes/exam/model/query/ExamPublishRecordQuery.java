package com.boss.bes.exam.model.query;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import boss.xtrain.core.data.convention.base.dto.BaseQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 08:58
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamPublishRecordQuery extends BaseQueryDTO {
    /**
     * 考试所属公司Id
     * */
    @NotNull(message = "公司Id不能为空")
    private Long companyId;
    /**
     * 考试名称
     * */
    private String title;
    /**
     * 发布用户名称
     * */
    private String publishUser;
    /**
     * 发布发布开始时间
     * */
    private LocalDateTime publishFromTime;
    /**
     * 发布结束时间
     * */
    private LocalDateTime publishToTime;
    /**
     * 考试开始时间
     * */
    private LocalDateTime examFromTime;
    /**
     * 考试结束时间
     * */
    private LocalDateTime examToTime;

}
