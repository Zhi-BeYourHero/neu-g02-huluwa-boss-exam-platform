package com.boss.bes.exam.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11 09:03
 * @since 1.0
 */
@Data
public class RabbitMessageDTO<T> implements Serializable {
    private static final long serialVersionUID = 1783629634256405952L;
    /**
     * message类型
     * 暂定: 1. 阅卷到期类型
     * */
    private int messageType;
    /**
     * 延迟时长以毫秒为单位
     * */
    private Integer delayTime;

    /**
     * 携带的数据
     * */
    private T data;
}
