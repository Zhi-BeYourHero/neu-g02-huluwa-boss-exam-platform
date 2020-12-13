package boss.xtrain.core.enums;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-06
 * @since
 */
public enum  UserStatus {
    /**
     * 用户的状态
     */
    OK(0, "正常"), DISABLE(1, "停用"), DELETED(2, "删除");

    private final Integer code;
    private final String info;

    UserStatus(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
