package com.boss.bes.exam.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-17 15:50
 * @since
 */
@Data
public class SubjectAnswerDTO {
    /**
     * 答案
     */
    private String answer;

    /**
     * 图片路径
     */
    private String imageUrl;
}
