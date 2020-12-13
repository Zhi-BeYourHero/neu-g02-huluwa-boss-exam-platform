package boss.xtrain.security.handler;

import boss.xtrain.core.exception.error.AuthExceptionCode;
import boss.xtrain.core.util.CommonResponseUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-13
 * @since
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint
{
    Logger logger = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Throwable cause = authException.getCause();

        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if(cause instanceof InvalidTokenException) {
                //自定义invalid token异常
                response.getWriter().write(JSON.toJSONString(CommonResponseUtil.error(
                        AuthExceptionCode.INVALID_TOKEN.getCode(),AuthExceptionCode.INVALID_TOKEN.getMessage()
                )));
            }else{
                //没有token时返回的异常
                response.getWriter().write(JSON.toJSONString(CommonResponseUtil.error(
                        AuthExceptionCode.TOKEN_MISSING.getCode(),AuthExceptionCode.TOKEN_MISSING.getMessage()
                )));
            }
        } catch (IOException e) {
            logger.error("抛出异常{}",e.toString());
        }
    }
}