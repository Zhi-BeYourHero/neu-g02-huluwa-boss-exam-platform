package boss.xtrain.core.exception.error;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-13
 * @since
 */
public enum AuthExceptionCode {
    /**
     * 认证异常
     */
    AUTH_ERROR_USERNAME_OR_PASSWORD("110101", "认证失败，用户名或密码错误"),

    /**
     * 无效的TOKEN
     */
    INVALID_TOKEN("110102", "携带的Token是无效token，无法访问系统"),

    /**
     * 无权限
     */
    FORBIDDEN("110103", "当前用户没有权限访问资源"),

    /**
     * 没有TOKEN
     */
    TOKEN_MISSING("110104","当前访问没有TOKEN，无法继续访问"),

    /**
     * 其他错误
     */
    OTHER_EXCEPTION("110110","其他错误"),
    ;
    /**
     * 异常码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;

    AuthExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
