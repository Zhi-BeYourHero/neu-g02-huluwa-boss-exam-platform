package boss.xtrain.core.data.convention.common;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @description: 请求封装
 * @author: WenZhi Luo
 * @create: 2020-07-01
 * @since
 */
public class CommonRequest<T> implements Serializable {
    private static final long serialVersionUID = -3704768961834196684L;
    /**
     * 请求头信息
     * */
    private RequestHeader header;
    /**
     * 请求题内容，用来放置DTO
     * */
    @Valid
    private T body;

    /**
     * getter setter方法
     * */
    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public CommonRequest() {
    }
}
