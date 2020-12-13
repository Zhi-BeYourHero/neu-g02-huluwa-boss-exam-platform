package com.boss.bes.paper.service.util;

import org.springframework.context.annotation.Import;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/9 10:27
 * @since: 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Import(CommonFieldAspect.class)
public @interface SetCommonField {

    /**
     * 标注方法类型
     * @return 方法类型字符串
     */
    String methodType();
}
