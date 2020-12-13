package com.boss.bes.paper.service.exception;

import boss.xtrain.core.exception.BaseException;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.exception
 * @Description:
 * @Date: 2020/7/9 9:49
 * @since: 1.0
 */
public class PaperServiceException extends BaseException {

    public PaperServiceException(String module, String code, String defaultMessage) {
        super(module, code, defaultMessage);
    }

    public PaperServiceException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public PaperServiceException(String defaultMessage) {
        super(defaultMessage);
    }
}

