package boss.xtrain.core.util;

import boss.xtrain.core.constant.Constants;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.exception.BaseException;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07
 * @since
 */
public final class CommonResponseUtil {

    private CommonResponseUtil() {
    }

    public static <T> CommonResponse<T> buildCommonResponse(String code, String message, T body) {
        return CommonResponse.response(code, message, body);
    }

    public static <T> CommonResponse<T> success() {
        return buildCommonResponse(null, null, null);
    }

    public static <T> CommonResponse<T> success(String code, String message) {
        return buildCommonResponse(code, message, null);
    }

    public static <T> CommonResponse<T> success(T body) {
        return buildCommonResponse(Constants.SUCCESS, Constants.SUCCESS_MESSAGE, body);
    }

    public static <T> CommonResponse<T> success(String message, T body) {
        return buildCommonResponse(Constants.SUCCESS, message, body);
    }

    public static <T> CommonResponse<T> error(BaseException exception) {
        return buildCommonResponse(exception.getCode(), exception.getMessage(), null);
    }

    public static <T> CommonResponse<T> error(String code, String message) {
        return buildCommonResponse(code, message, null);
    }

    public static <T> CommonResponse<T> error(String code, String message, T body) {
        return buildCommonResponse(code, message, body);
    }

    public static <T> CommonResponse<T> error(String message) {
        return buildCommonResponse(Constants.FAIL, message,null);
    }


    public static <T> CommonResponse<T> error() {
        return buildCommonResponse(Constants.FAIL, Constants.FAIL_MESSAGE,null);
    }
}