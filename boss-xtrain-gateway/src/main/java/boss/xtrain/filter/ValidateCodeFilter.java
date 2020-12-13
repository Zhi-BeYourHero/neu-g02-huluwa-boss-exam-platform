package boss.xtrain.filter;

import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.service.ValidateCodeService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 验证码过滤器
 * @create 2020-07-14
 * @since
 */
@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object>
{
    private static final String AUTH_URL = "/oauth/token";

    @Autowired
    private ValidateCodeService validateCodeService;

    private static final String BASIC_ = "Basic ";

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    @Override
    public GatewayFilter apply(Object config)
    {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 非登录请求，不处理
            if (!StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL))
            {
                return chain.filter(exchange);
            }

            // 消息头存在内容，且不存在验证码参数，不处理
            String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotEmpty(header) && StringUtils.startsWith(header, BASIC_)
                    && !request.getQueryParams().containsKey(CODE) && !request.getQueryParams().containsKey(UUID))
            {
                return chain.filter(exchange);
            }
            try
            {
                //进行校验
                validateCodeService.checkCapcha(request.getQueryParams().getFirst(CODE),
                        request.getQueryParams().getFirst(UUID));
            }
            catch (Exception e)
            {
                ServerHttpResponse response = exchange.getResponse();
                return exchange.getResponse().writeWith(
                        Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(CommonResponseUtil.error(e.getMessage())))));
            }

            return chain.filter(exchange);
        };
    }
}
