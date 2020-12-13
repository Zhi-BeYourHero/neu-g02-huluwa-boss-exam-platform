package boss.xtrain.config.swagger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 开启Swagger2通用注解
 * @create 2020-07-02 17:47
 * @since 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerAutoConfiguration.class })
public @interface EnableCustomSwagger2 {

}
