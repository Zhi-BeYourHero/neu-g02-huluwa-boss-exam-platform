package boss.xtrain.exception;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-11
 * @since
 */
public enum GatewayErrorType {

    /**
     * Sentinel限流异常
     */
    SENTINEL_CURRENT_LIMITING_ERROR("100101", "当前bes考试系统请求超过最大数，请稍后再试"),

    /**
     * 黑名单URI
     */
    BLACK_LIST_URL_ERROR("100102", "当前系统资源仅供服务内部调用，不对外开放"),

    /**
     * 熔断降级
     */
    GATEWAY_DEMOTION_ERROR("100103", "服务不可用，已被降级熔断"),

    ;
    /**
     * 异常码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;

    GatewayErrorType(String code, String message) {
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
