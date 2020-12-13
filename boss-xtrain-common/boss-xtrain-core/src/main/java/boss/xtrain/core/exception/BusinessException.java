package boss.xtrain.core.exception;

import boss.xtrain.core.exception.error.CommonErrorCode;
/**
 * @Author: wangziyi
 * @program: boss-xtrain-log
 * @Description: 业务异常类
 * @Date: 2020/7/2 8:25
 * @since: 1.0
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常码
     */
    private final String code;

    /**
     * 异常信息
     */
    final String message;

    public BusinessException() {
        code = null;
        message = null;
    }

    public String getCode() {
        return code;
    }


    public BusinessException(Throwable throwable) {
        super(throwable);
        code = null;
        message = null;
    }

    public BusinessException(CommonErrorCode commonErrorCode) {
        this.code = commonErrorCode.getCode();
        this.message = commonErrorCode.getMessage();
    }

    public BusinessException(CommonErrorCode commonErrorCode, Throwable throwable) {
        super(throwable);
        this.code = commonErrorCode.getCode();
        this.message = commonErrorCode.getMessage();
    }


    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String code, String message, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.message = message;
    }

}