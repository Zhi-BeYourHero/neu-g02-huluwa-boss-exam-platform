package boss.xtrain.config;

import boss.xtrain.handler.HystrixFallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 熔断降级处理配置类
 * @create 2020-07-11
 * @since
 */
@Configuration
public class GatewayFallbackConfig {

    @Autowired
    private HystrixFallbackHandler hystrixFallbackHandler;

    /**
     * 路由转发到对应的处理器进行处理
     * @return
     */
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.GET("/fallback")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler);
    }
}
