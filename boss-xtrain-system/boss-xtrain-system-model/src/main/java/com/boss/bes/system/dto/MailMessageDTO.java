package com.boss.bes.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用来给指定用户发送邮件的DTO
 * @create 2020-07-20 14:38
 * @since 1.0
 */
@Data
public class MailMessageDTO {

    private List<Long> userIds;

    private String title;

    private String content;
}
