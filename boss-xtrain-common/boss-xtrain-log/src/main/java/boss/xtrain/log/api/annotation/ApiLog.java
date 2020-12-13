package boss.xtrain.log.api.annotation;


import boss.xtrain.log.api.aspect.MyApiLogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author wangziyi
 * @program boss-xtrain-log
 * @Description 自定义日志注解
 * @Date 2020/7/2 8:25
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyApiLogAspect.class)
public @interface ApiLog {
    /**
     * 描述
     * @return {String}
     */
    String msg() default "";
}

