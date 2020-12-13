package boss.xtrain.core.util;

import boss.xtrain.core.constant.Constants;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.ResponseHeader;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07
 * @since
 */
public class AppUtil {

    private AppUtil(){}

    public static <T> void setResponseExtendInfo(CommonResponse<T> commonResponse){
        ResponseHeader header = new ResponseHeader();
        //设置是否加密
        header.setEncryptFlag(Constants.NOT_ENCRYPT_FLAG);
        //当前版本
        header.setVersion(Constants.SYSTEM_VERSION);
        //返回成功信息
        header.setAnswerBackCode(Constants.SUCCESS);

        header.setMessage(Constants.SUCCESS_MESSAGE);
        commonResponse.setHeader(header);
    }

    public static <T> void setResponseExtendInfo(CommonResponse<T> commonResponse,String code, String message){
        ResponseHeader header = new ResponseHeader();
        header.setEncryptFlag(Constants.NOT_ENCRYPT_FLAG);
        header.setVersion(Constants.SYSTEM_VERSION);
        header.setAnswerBackCode(code);
        header.setMessage(message);
        commonResponse.setHeader(header);
    }
}
