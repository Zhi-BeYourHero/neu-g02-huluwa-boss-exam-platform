package boss.xtrain.core.constant;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 通用常量信息
 * @create 2020-07-05
 * @since
 */
public class Constants {

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 管理员角色标识
     */
    public static final String ADMIN = "admin";

    /**
     * 系统版本号
     */
    public static final String SYSTEM_VERSION = "v0.0.1_beta";

    /**
     * 响应加密
     */
    public static final int ENCRYPT_FLAG = 1;

    /**
     * 响应不加密
     */
    public static final int NOT_ENCRYPT_FLAG = 0;

    /**
     * 成功标记
     */
    public static final String SUCCESS = "200";

    /**
     * 失败标记
     */
    public static final String FAIL = "500";

    /**
     * 成功调用信息标记
     */
    public static final String SUCCESS_MESSAGE = "成功调用";

     /**
     * 调用失败信息标记
     */
    public static final String FAIL_MESSAGE = "调用失败";

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "message";


    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";


    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;


    private Constants(){}
}
