package com.boss.bes.exam.util;

import boss.xtrain.core.exception.BaseException;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试模块异常类
 * @create 2020-07-09 10:18
 * @since 1.0
 */
public class ExamException extends BaseException {

    public static final String MODULE_NAME = "BES-Exam";

    public ExamException(ExamExceptionCode exceptionCode) {
        super(MODULE_NAME, exceptionCode.getCode(), exceptionCode.getMessage());
    }

    public ExamException(String code, String message) {
        super(MODULE_NAME, code, message);
    }
}
