package boss.xtrain.core.exception.error;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-log
 * @Description: 通用异常枚举类
 * @Date: 2020/7/2 8:25
 * @since: 1.0
 */
public enum CommonErrorCode {
    /**
     * 通用服务异常
     */
    BASE_CRUD_SERVICE_ERROR_CODE_CREATE("100000","保存失败"),
    BASE_CRUD_SERVICE_ERROR_CODE_RETRIEVE("100001","查询失败"),
    BASE_CRUD_SERVICE_ERROR_CODE_UPDATE("100002","更新失败"),
    BASE_CRUD_SERVICE_ERROR_CODE_DELETE("100003","删除失败"),



    LOG_NOT_EXIST_ERROR("50003","操作空数据变量"),

    /**
     * 操作失败
     */
    OPERATION_ERROR("100004", "操作失败"),
    /**
     * 参数错误
     */
    PARAM_ERROR("100005", "参数校验错误"),

    /**
     * JSON转换错误
     */
    JSON_CONVERT_ERROR("100006", "JSON转换错误"),

    /**
     * rpc调用异常
     */
    RPC_ERROR("100007", "网络出问题");

    /**
     * 异常码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;

    CommonErrorCode(String code, String message) {
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
