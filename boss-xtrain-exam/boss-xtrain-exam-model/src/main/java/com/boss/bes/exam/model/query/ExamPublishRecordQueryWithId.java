package com.boss.bes.exam.model.query;

import boss.xtrain.core.data.convention.base.dto.BaseQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 15:16
 * @since 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamPublishRecordQueryWithId extends BaseQueryDTO {
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
    /**
     * 模糊查询的用户ID列表
     * */
    private List<Long> userIds;
}
