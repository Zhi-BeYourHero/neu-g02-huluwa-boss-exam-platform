package com.boss.bes.exam.model.dto;

import lombok.Data;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 16:53
 * @since
 */
@Data
public class PaperSubjectAnswerDTO {
    /**
     * 答案内容
     * */
    private String answer;

    /**
     * 图片路径
     * */
    private String imageUrl;

    /**
     * 是否为正确答案
     * */
    private Integer rightAnswer;
}
