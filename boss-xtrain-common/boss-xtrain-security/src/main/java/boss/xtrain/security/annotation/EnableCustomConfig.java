package boss.xtrain.security.annotation;

import boss.xtrain.security.config.ApplicationConfig;
import boss.xtrain.security.config.SecurityImportBeanDefinitionRegistrar;
import boss.xtrain.security.feign.OAuth2FeignConfig;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

import java.lang.annotation.*;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 自定义自动加载类，主要是资源服务器使用
 * @create 2020-07-06
 * @since
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.boss.bes.**.mapper")
// 开启线程异步执行
@EnableAsync
// 自动加载类
@Import({ SecurityImportBeanDefinitionRegistrar.class, OAuth2FeignConfig.class, ApplicationConfig.class })
public @interface EnableCustomConfig {
}
