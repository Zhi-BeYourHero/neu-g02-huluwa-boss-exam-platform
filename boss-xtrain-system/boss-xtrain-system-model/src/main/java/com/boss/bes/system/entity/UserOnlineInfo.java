package com.boss.bes.system.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 在线用户的实体类 tkMyBatis生成
 * @create 2020-07-07
 * @since 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user_online_info")
@JsonInclude()
public class UserOnlineInfo implements Serializable {

    /**
     * 在线用户id
     */
    @Id
    @Column(name = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "t_u_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 工号
     */
    @Column(name = "code")
    private String code;

    /**
     * 用户
     */
    @Column(name = "`name`")
    private String name;

    /**
     * IP
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 上线时间
     */
    @Column(name = "online_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlineTime;

    /**
     * 下线时间
     */
    @Column(name = "offline_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offlineTime;

    /**
     * 在线时长
     */
    @Column(name = "stop_time")
    private Integer stopTime;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    private static final long serialVersionUID = 1L;
}