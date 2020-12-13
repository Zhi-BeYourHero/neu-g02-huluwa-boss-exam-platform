package boss.xtrain.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import javax.servlet.http.HttpServletResponse;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description OAuth2 自定义异常处理
 * @create 2020-07-06
 * @since
 */
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception>
{
    Logger logger = LoggerFactory.getLogger(CustomWebResponseExceptionTranslator.class);

    /**
     * 处理所有类型异常
     * @param e
     * @return
     */
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e)
    {
        logger.info("走自定义异常：{}",e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(new CustomOauthException(e.getMessage()));
    }
}