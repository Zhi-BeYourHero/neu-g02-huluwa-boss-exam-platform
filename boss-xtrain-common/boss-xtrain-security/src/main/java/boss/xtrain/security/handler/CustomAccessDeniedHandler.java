package boss.xtrain.security.handler;

import boss.xtrain.core.exception.error.AuthExceptionCode;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.security.util.ServletUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 自定义访问无权限资源时的异常
 * @create 2020-07-06
 * @since
 */
@Component
public class CustomAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    private final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException)
    {
        log.info("权限不足，请联系管理员 {}", request.getRequestURI());

        String msg = authException.getMessage();
        ServletUtils.renderString(response, JSON.toJSONString(CommonResponseUtil.error(AuthExceptionCode.FORBIDDEN.getCode(), AuthExceptionCode.FORBIDDEN.getMessage()+"：(系统默认返回消息)"+msg)));
    }
}