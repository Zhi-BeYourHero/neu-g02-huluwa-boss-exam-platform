package com.boss.bes.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 在线用户的VO类
 * @create 2020-07-10 18:05
 * @since 1.0
 */
@Data
public class UserOnlineInfoVo implements Serializable {

    /**
     * 在线用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 工号
     */
    private String code;

    /**
     * 用户
     */
    private String name;

    /**
     * IP
     */
    private String ip;

    /**
     * 上线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlineTime;

    /**
     * 下线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offlineTime;

    /**
     * 在线时长
     */
    private Integer stopTime;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

}