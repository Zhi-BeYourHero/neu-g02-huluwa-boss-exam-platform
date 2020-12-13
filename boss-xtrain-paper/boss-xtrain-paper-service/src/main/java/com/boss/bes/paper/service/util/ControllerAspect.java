package com.boss.bes.paper.service.util;

import boss.xtrain.core.data.convention.common.CommonRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/9 10:21
 * @since: 1.0
 */
@Slf4j
@Component
@Aspect
public class ControllerAspect {

    public static ThreadLocal<String> threadLocal =new ThreadLocal<>();
//    @Pointcut("execution(* *..controller..*(..))")
//    public void anyControllerApi() {}
//
//    @Before("anyControllerApi()")
    public void before(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0)
        {
            return;
        }
        CommonRequest commonRequest = (CommonRequest) args[0];
        if (commonRequest == null){
            log.info("获取CommonRequest异常！");
            throw new Exception("获取CommonRequest异常！");
        }
        String token = commonRequest.getHeader().getToken();
        ControllerAspect.threadLocal.set(token);
    }
}
