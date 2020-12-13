package com.boss.bes.system.constant;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-20
 * @since
 */
public class ErrorCodeConstant {

    private ErrorCodeConstant() {
    }

    /**
     * 2213xx 在线用户管理模块
     */
    public static final String USER_ONLINE_FORCE_TO_EXIT = "221301";
    public static final String USER_ONLINE_FORCE_TO_EXIT_MSG = "您已被强制下线！";
    public static final String USER_ONLINE_CROWDING_OUT = "221302";
    public static final String USER_ONLINE_CROWDING_OUT_MSG = "您的账号在另一台设备上登录，请重新登录！";

}
