package boss.xtrain.cache.redis.lock;

/**
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 分布式锁接口
 * @author linzhiyun
 * @create 2020-07-02
 * @since
 */
public interface Lock {
    /**
     * 超时时间、重试次数、睡眠时间
     */
    long TIMEOUT_MILLIS = 30000;
    int RETRY_TIMES = Integer.MAX_VALUE;
    long SLEEP_MILLIS = 500;
    /**
     * 加锁
     */
    boolean lock(String key);
    boolean lock(String key, int retryTimes);
    boolean lock(String key, int retryTimes, long sleepMillis);
    boolean lock(String key, long expire);
    boolean lock(String key, long expire, int retryTimes);
    boolean lock(String key, long expire, int retryTimes, long sleepMillis);
    /**
     * 释放锁
     */
    boolean releaseLock(String key);
}

