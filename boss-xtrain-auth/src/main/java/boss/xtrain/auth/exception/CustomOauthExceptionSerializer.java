package boss.xtrain.auth.exception;

import boss.xtrain.core.constant.Constants;
import boss.xtrain.core.exception.error.AuthExceptionCode;
import boss.xtrain.security.util.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 自定义Oauth2异常返回
 * @create 2020-07-09
 * @since
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException>
{
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(CustomOauthExceptionSerializer.class);

    public static final String BAD_CREDENTIALS = "Bad credentials";

    public CustomOauthExceptionSerializer()
    {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException
    {
        jsonGenerator.writeStartObject();
        if (StringUtils.equals(e.getMessage(), BAD_CREDENTIALS))
        {
            log.info("账号密码错误哦：{}",e.getMessage());
            jsonGenerator.writeStringField(Constants.CODE_TAG, AuthExceptionCode.AUTH_ERROR_USERNAME_OR_PASSWORD.getCode());
            jsonGenerator.writeStringField(Constants.MSG_TAG, AuthExceptionCode.AUTH_ERROR_USERNAME_OR_PASSWORD.getMessage());
        }
        else
        {
            log.info("其他错误噢：{}",e.getMessage());
            jsonGenerator.writeStringField(Constants.CODE_TAG, AuthExceptionCode.OTHER_EXCEPTION.getCode());
            jsonGenerator.writeStringField(Constants.MSG_TAG, AuthExceptionCode.OTHER_EXCEPTION.getMessage()+"："+e.getMessage());
        }
        jsonGenerator.writeEndObject();
    }
}