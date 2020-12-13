package com.boss.bes.paper.utils.exception;

import boss.xtrain.core.exception.BaseException;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.utils.exception
 * @Description:
 * @Date: 2020/7/11 21:05
 * @since: 1.0
 */
public class PaperException extends BaseException {

    public static final String MODULE_NAME = "Paper-Exception";

    public PaperException(PaperExceptionCode exceptionCode) {
        super(MODULE_NAME, exceptionCode.getCode(), exceptionCode.getMessage());
    }

    public PaperException(String code, String message) {
        super(MODULE_NAME, code, message);
    }

}
