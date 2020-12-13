package boss.xtrain.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description oauth2自定义异常，指定CustomOauthExceptionSerializer为对应的序列化器
 * @create 2020-07-09
 * @since
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception
{
    private static final long serialVersionUID = 1L;

    public CustomOauthException(String msg)
    {
        super(msg);
    }
}