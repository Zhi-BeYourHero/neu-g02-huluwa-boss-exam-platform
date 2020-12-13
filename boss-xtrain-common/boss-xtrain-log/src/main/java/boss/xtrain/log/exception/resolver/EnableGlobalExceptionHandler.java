package boss.xtrain.log.exception.resolver;

import boss.xtrain.log.exception.GlobalExceptionConfiguration;
import org.springframework.context.annotation.Import;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author wangziyi
 * @program boss-xtrain-log
 * @Description 统一异常处理注解
 * @Date 2020/7/2 8:25
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({GlobalExceptionConfiguration.class})
public @interface EnableGlobalExceptionHandler {

}
