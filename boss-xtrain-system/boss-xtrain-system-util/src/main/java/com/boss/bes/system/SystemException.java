package com.boss.bes.system;

import boss.xtrain.core.exception.BaseException;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统管理模块的异常
 * @create 2020-07-09 21:32
 * @since 1.0
 */
public class SystemException extends BaseException {

    public SystemException(String module, String code, String defaultMessage) {
        super(module, code, defaultMessage);
    }

    public SystemException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public SystemException(String defaultMessage) {
        super(defaultMessage);
    }
}