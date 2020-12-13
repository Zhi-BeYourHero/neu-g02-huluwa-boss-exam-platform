package boss.xtrain.security.config;

import boss.xtrain.security.util.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 导入 SecurityImportBeanDefinitionRegistrar 自动加载类
 * @create 2020-07-06
 * @since
 */
public class SecurityImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar
{
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry)
    {
        //资源服务配置类
        Class<ResourceServerConfig> aClass = ResourceServerConfig.class;

        String beanName = StringUtils.uncapitalize(aClass.getSimpleName());
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ResourceServerConfig.class);
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }
}
