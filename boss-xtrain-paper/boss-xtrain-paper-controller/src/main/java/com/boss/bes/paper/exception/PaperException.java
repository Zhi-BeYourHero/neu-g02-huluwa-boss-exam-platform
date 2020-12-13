package com.boss.bes.paper.exception;

import boss.xtrain.core.exception.BaseException;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.exception
 * @Description:
 * @Date: 2020/7/10 9:08
 * @since: 1.0
 */
public class PaperException extends BaseException {

    public PaperException(String module, String code, String defaultMessage) {
        super(module, code, defaultMessage);
    }

    public PaperException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public PaperException(String defaultMessage) {
        super(defaultMessage);
    }
}