package boss.xtrain.security.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 认证失败事件处理器模板
 * @create 2020-07-06
 * @since
 */
public abstract class AbstractAuthenticationFailureEvenHandler implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event)
    {
        AuthenticationException authenticationException = event.getException();
        Authentication authentication = (Authentication) event.getSource();

        handle(authenticationException, authentication);
    }

    /**
     * 处理登录失败方法
     */
    public abstract void handle(AuthenticationException authenticationException, Authentication authentication);
}
