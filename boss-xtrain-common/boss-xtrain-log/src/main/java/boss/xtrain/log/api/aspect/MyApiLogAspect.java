package boss.xtrain.log.api.aspect;


import boss.xtrain.core.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import boss.xtrain.log.api.annotation.ApiLog;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Objects;

/**
 * @Author wangziyi
 * @program boss-xtrain-log
 * @Description 控制层日志切面, 使用ApiLog注解打到控制器上进行使用，或者使用GlobalApiLog注解打在启动类上
 * @Date 2020/7/2 8:25
 * @since 1.0
 */
@Aspect
@Component
public class MyApiLogAspect {

    private static final Logger log = LoggerFactory.getLogger(MyApiLogAspect.class);

    @Pointcut("@within(boss.xtrain.log.api.annotation.ApiLog)")
    public void apiPointcut() {
        log.info("进入日志切面");
    }

    @Around("apiPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取请求信息
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String reqMsg = this.getRequestMsg(request);
        //请求参数
        Object[] args = joinPoint.getArgs();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取joinPoint所有的methods,获取具有注解的方法的注解内容
        Class<?> clazz = joinPoint.getTarget().getClass();
        //注解中内容
        String logMsg = this.getAnnotationMsg(clazz);
        //method调用并产生结果
        Object result = null;
        try{
            result = joinPoint.proceed();
        }
        finally {
            //计算时间
            long execTime = System.currentTimeMillis() - startTime;
            //保存日志
            log.info("regMsg: {}", reqMsg);
            log.info("logMsg: {}", logMsg);
            log.info("method: {}.{}", clazz.getName(), methodName);
            log.info("args: {}", args);
            log.info("execTime: {} ms", execTime);
            log.info("result: {}", result);
        }
        return result;
    }

    /**
     * 获取请求方法的详细信息
     *
     * @param res HttpServletRequest 请求
     * @return String Request信息
     */
    private String getRequestMsg(HttpServletRequest res) {
        //获取请求
        if (res == null) {
            return "requestMsg=[]";
        }
        //获取请求url
        String url = res.getRequestURL().toString();
        //获取请求ip
        String ip = res.getRemoteAddr();
        //请求者
        String reqUser = res.getRemoteUser();
        //请求时间
        String reqTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return String.format("[URL-%s, IP-%s, reqUser-%s, reqTim-%s]", url, ip, reqUser, reqTime);
    }

    /**
     * 获取注解中日志信息
     *
     * @param clazz 注解的类
     * @return 注解中日志信息
     */
    private String getAnnotationMsg(Class<?> clazz) {
        if (clazz == null) {
            return "";
        }
        String logMsg = "";
        ApiLog apiLog = clazz.getAnnotation(ApiLog.class);
        logMsg = apiLog.msg();
        return logMsg;
    }
}