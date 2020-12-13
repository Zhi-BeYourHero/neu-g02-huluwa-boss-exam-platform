package com.boss.bes.basedata;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.ResponseHeader;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 成功请求
 * @create 2020-07-09 00:58
 * @since
 */
public class SuccessResponse {
    private SuccessResponse() {
        throw new IllegalStateException("Utility class");
    }
    public static <T> CommonResponse<T> success(T body){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setHeader(new ResponseHeader());
        commonResponse.getHeader().setAnswerBackCode("1");
        commonResponse.setBody(body);
        commonResponse.getHeader().setEncryptFlag(0);
        commonResponse.getHeader().setVersion("1.0");
        commonResponse.getHeader().setMessage("成功调用");
        return commonResponse;
    }
}
