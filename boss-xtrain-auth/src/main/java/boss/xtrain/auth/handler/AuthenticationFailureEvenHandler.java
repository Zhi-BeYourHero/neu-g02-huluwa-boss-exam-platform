package boss.xtrain.auth.handler;

import boss.xtrain.security.handler.AbstractAuthenticationFailureEvenHandler;
import boss.xtrain.security.util.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 认证失败处理
 * @create 2020-07-06
 * @since
 */
@Component
public class AuthenticationFailureEvenHandler extends AbstractAuthenticationFailureEvenHandler {

    Logger log = LoggerFactory.getLogger(AuthenticationFailureEvenHandler.class);

    @Override
    public void handle(AuthenticationException authenticationException, Authentication authentication)
    {
        HttpServletRequest request = ServletUtils.getRequest();

        String url = request.getRequestURI();
        //因为认证失败了，所以这个Principal只是一个username，
        String username = (String) authentication.getPrincipal();
        //认证失败，打印信息
        log.info("用户：{} 授权失败，url：{}", username, url);
    }
}
