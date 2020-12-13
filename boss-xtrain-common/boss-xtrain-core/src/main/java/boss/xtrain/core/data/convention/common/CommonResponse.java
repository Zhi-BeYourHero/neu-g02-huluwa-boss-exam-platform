package boss.xtrain.core.data.convention.common;

import boss.xtrain.core.util.AppUtil;

import java.io.Serializable;

/**
 * @description:
 * @author: WenZhi Luo
 * @create: 2020-07-01
 * @since
 */
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = -6512564584974810367L;
    /**
     * 响应头
     * */
    private ResponseHeader header;
    /**
     * 响应内容
     * */
    private T body;

    /**
     * 全参构造方法
     * */
    public CommonResponse(ResponseHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    /**
     * 无参构造方法
     * **/
    public CommonResponse() {
    }
    /**
     * getter setter方法
     * */
    public ResponseHeader getHeader() {
        return header;
    }

    public T getBody() {
        return body;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public void setBody(T body) {
        this.body = body;
    }

    /**
     * 含响应体
     * */
    public static <T> CommonResponse<T> response(String code, String message,T body){
        //构建响应对象
        CommonResponse<T> commonResponse = new CommonResponse<>();
        AppUtil.setResponseExtendInfo(commonResponse,code,message);
        commonResponse.setBody(body);
        return commonResponse;
    }


}