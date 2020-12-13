package boss.xtrain.auth.factory;

import boss.xtrain.auth.api.RemoteUserService;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.security.model.UserInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用户服务降级处理
 * @create 2020-07-06
 * @since
 */
@Component
public class RemoteUserFallbackFactory  implements FallbackFactory<RemoteUserService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public CommonResponse<UserInfo> getUserInfo(String username) {
                return null;
            }

            @Override
            public String deleteUser() {
                return null;
            }
        };
    }
}
