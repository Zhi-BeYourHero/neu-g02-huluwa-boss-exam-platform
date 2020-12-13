package boss.xtrain.security.annotation.stuffer;

import java.lang.annotation.*;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 自动填充注解
 * @create 2020-07-05
 * @since
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AutoStuff {

}