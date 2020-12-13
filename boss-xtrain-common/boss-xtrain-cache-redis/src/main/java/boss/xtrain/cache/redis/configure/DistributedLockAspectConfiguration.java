package boss.xtrain.cache.redis.configure;

import boss.xtrain.cache.redis.lock.Lock;
import boss.xtrain.cache.redis.lock.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 分布式锁切面配置
 * @create 2020-07-03 20:08
 * @since 1.0
 */
@Aspect
@Configuration
@ConditionalOnClass(Lock.class)
@AutoConfigureAfter(DistributedLockAutoConfiguration.class)
public class DistributedLockAspectConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DistributedLockAspectConfiguration.class);

    @Resource
    private Lock lock;

    @Pointcut("@annotation(boss.xtrain.cache.redis.lock.RedisLock)")
    private void lockPoint() {

    }

    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.value();
        if (StringUtils.isEmpty(key)) {
            Object[] args = pjp.getArgs();
            key = Arrays.toString(args);
        }
        int retryTimes = redisLock.action().equals(RedisLock.LockFailAction.CONTINUE) ? redisLock.retryTimes() : 0;
        boolean isLock = lock.lock(key, redisLock.keepMills(), retryTimes, redisLock.sleepMills());
        if (!isLock) {
            log.debug("获取锁失败：{}", key);
            return null;
        }
        log.debug("获取锁成功：{}", key);
        try {
            return pjp.proceed();
        } catch (Exception e) {
            log.error("执行锁方法异常", e);
        } finally {
            boolean releaseResult = lock.releaseLock(key);
            log.debug("释放锁：{}{}", key, (releaseResult ? "成功" : "失败"));
        }
        return null;
    }
}
