package boss.xtrain.log.exception;

import boss.xtrain.log.exception.resolver.GlobalExceptionHandler;
import boss.xtrain.log.exception.resolver.GlobalResponseControllerAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangziyi
 * @program boss-xtrain-log
 * @Description 注入异常处理
 * @Date 2020/7/2 8:25
 * @since 1.0
 */
@Configuration
public class GlobalExceptionConfiguration {

    /**
     * 配置全局异常处理器
     * @return 全局异常处理器
     */
    @Bean
    public GlobalExceptionHandler globalDefaultExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * 配置全局响应数据处理器
     *
     * @return 全局响应数据处理器
     */
    @Bean
    public GlobalResponseControllerAdvice globalResponseControllerAdvice() {
        return new GlobalResponseControllerAdvice();
    }
}

