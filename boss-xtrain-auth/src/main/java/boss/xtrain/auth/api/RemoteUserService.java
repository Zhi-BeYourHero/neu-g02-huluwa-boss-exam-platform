package boss.xtrain.auth.api;

import boss.xtrain.core.constant.ServiceNameConstants;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.auth.factory.RemoteUserFallbackFactory;
import boss.xtrain.security.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用户服务
 * @create 2020-07-06
 * @since
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "/user/info/{username}")
    CommonResponse<UserInfo> getUserInfo(@PathVariable("username") String username);

    @GetMapping(value = "/resources/deleteUser")
    String deleteUser();
}