package com.boss.bes.exam.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 参数校验的工具类
 * @create 2020-07-09 22:42
 * @since 1.0
 */
public final class ValidatorUtil {

    /**
     * 验证手机号码的正则表达式
     */
    private static final String PATTERN = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    private static final Pattern MOBILE_PATTERN = Pattern.compile(PATTERN);

    private ValidatorUtil(){}

    /**
     * 手机号验证
     * @param src 手机号
     * @return 验证结果
     */
    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(src);
        return m.matches();
    }
}
