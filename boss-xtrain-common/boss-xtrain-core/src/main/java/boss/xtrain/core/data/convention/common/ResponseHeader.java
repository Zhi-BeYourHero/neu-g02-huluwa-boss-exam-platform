package boss.xtrain.core.data.convention.common;

import java.io.Serializable;

/**
 * @description: 响应头信息
 * @Author: FHF
 * @Date: Created in 11:07 2020/7/2
 * @since 1.0
 */
public class ResponseHeader implements Serializable {
    private static final long serialVersionUID = 1057253498252326033L;
    /**
     * 应用程序版本
     */
    private String version;

    /**
     * 应答码信息
     */
    private String answerBackCode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 是否加密 1 加密 0 不加密 默认不加密
     */
    private Integer encryptFlag;

    /**
     * 全参无参构造方法
     * */
    public ResponseHeader(String version, String answerBackCode, String message, Integer encryptFlag) {
        this.version = version;
        this.answerBackCode = answerBackCode;
        this.message = message;
        this.encryptFlag = encryptFlag;
    }
    public ResponseHeader() {
    }

    /**
     * getter setter 方法
     * */
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAnswerBackCode() {
        return answerBackCode;
    }

    public void setAnswerBackCode(String answerBackCode) {
        this.answerBackCode = answerBackCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getEncryptFlag() {
        return encryptFlag;
    }

    public void setEncryptFlag(Integer encryptFlag) {
        this.encryptFlag = encryptFlag;
    }
}
