package com.boss.bes.exam.model.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-15 14:25
 * @since 1.0
 */
@Data
public class PaperQuery {

    /**
     * 考试记录ID
     * */
    @NotNull
    private Long recordId;

    /**
     * 考试记录试卷Id
     * */
    @NotNull
    private Long paperId;
}
