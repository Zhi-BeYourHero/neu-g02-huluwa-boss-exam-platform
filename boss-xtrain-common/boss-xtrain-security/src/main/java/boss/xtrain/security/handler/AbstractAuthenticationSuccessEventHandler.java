package boss.xtrain.security.handler;

import boss.xtrain.security.util.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 认证成功事件处理器模块
 * @create 2020-07-06
 * @since
 */
public abstract class AbstractAuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event)
    {
        Authentication authentication = (Authentication) event.getSource();
        if (StringUtils.isNotEmpty(authentication.getAuthorities()))
        {
            handle(authentication);
        }
    }

    /**
     * 处理登录成功方法
     */
    public abstract void handle(Authentication authentication);
}
