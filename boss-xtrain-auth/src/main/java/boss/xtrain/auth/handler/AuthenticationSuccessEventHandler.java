package boss.xtrain.auth.handler;

import boss.xtrain.security.domain.LoginUser;
import boss.xtrain.security.handler.AbstractAuthenticationSuccessEventHandler;
import boss.xtrain.security.util.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 认证成功处理
 * @create 2020-07-06
 * @since
 */
@Component
public class AuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

    Logger log = LoggerFactory.getLogger(AuthenticationSuccessEventHandler.class);

    @Override
    public void handle(Authentication authentication)
    {
        HttpServletRequest request = ServletUtils.getRequest();

        String url = request.getRequestURI();
        // 使用自定义的LoginUser,继承自User，故会自动填充且使用，其实就多了个UserId
        if (authentication.getPrincipal() instanceof LoginUser)
        {
            LoginUser user = (LoginUser) authentication.getPrincipal();

            String username = user.getUsername();

            log.info("用户：{} 授权成功，url：{}", username, url);
        }
    }
}
