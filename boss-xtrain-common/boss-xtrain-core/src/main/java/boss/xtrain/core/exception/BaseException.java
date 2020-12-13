package boss.xtrain.core.exception;

import boss.xtrain.core.constant.Constants;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 基础异常
 * @create 2020-07-06
 * @since
 */
public class BaseException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module, String code,  String defaultMessage)
    {
        this.module = module;
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String defaultMessage)
    {
        this(module, Constants.SUCCESS, defaultMessage);
    }

    public BaseException(String defaultMessage)
    {
        this(null, Constants.SUCCESS, defaultMessage);
    }

    public String getModule()
    {
        return module;
    }

    public String getCode()
    {
        return code;
    }

    public String getDefaultMessage()
    {
        return defaultMessage;
    }
}
