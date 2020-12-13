package boss.xtrain.util.string;

import org.apache.commons.lang3.*;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 字符串操作工具类
 * @create 2020-7-2
 * @since 1.0
 */
public class StringUtil extends StringUtils {

    /**
     * 空字符串
     */
    private static final String NULL_STR = "";


    /**
     * 判断字符串是否为“”或者null
     *
     * @param cs 字符序列
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs == NULL_STR;
    }

    /**
     * 判断字符串是否不为“”或者不为null
     *
     * @param cs 字符序列
     * @return 是否不为空
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 去除字符串首尾的空格
     *
     * @param str 字符串
     * @return 去除字符串首尾的空格后的字符串
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 判断字符串是否相等
     *
     * @param cs1 字符串1
     * @param cs2 字符串1
     * @return 是否相等
     */
    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        return StringUtils.equals(cs1, cs2);
    }

    /**
     * 字符串比较，忽略大小写
     *
     * @param str1 字符串1
     * @param str2 字符串1
     * @return 比较结果
     */
    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return StringUtils.equalsIgnoreCase(str1, str2);
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULL_STR;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return NULL_STR;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }

}
