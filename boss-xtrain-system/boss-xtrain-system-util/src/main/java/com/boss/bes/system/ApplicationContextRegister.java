package com.boss.bes.system;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 工具类
 * @create 2020-07-20
 * @since
 */
@Component
@Lazy(false)
public class ApplicationContextRegister  implements ApplicationContextAware {

    private static ApplicationContext context;
    /**
     * 设置spring上下文
     * @param applicationContext spring上下文
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        setContext(applicationContext);
    }

    private static void setContext(ApplicationContext applicationContext){
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}
