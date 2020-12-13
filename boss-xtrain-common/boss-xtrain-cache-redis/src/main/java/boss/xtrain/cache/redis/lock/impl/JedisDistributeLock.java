package boss.xtrain.cache.redis.lock.impl;

import boss.xtrain.cache.redis.lock.Lock;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description redis分布式锁
 * @create 2020-07-02
 * @since 1.0
 */
@Component
public class JedisDistributeLock implements Lock {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ThreadLocal<String> lockFlag = new ThreadLocal<>();

    public static final String UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then  " +
            "   return redis.call(\"del\",KEYS[1]) else  " +
            "   return 0 end ";

    public JedisDistributeLock(RedisTemplate<String, Object> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    /**
     * 加锁
     */
    @Override
    public boolean lock(String key) {
        return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryTimes) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryTimes, long sleepMillis) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, sleepMillis);
    }

    @Override
    public boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes) {
        return lock(key, expire, retryTimes, SLEEP_MILLIS);
    }

    /**
     * 设置Redis
     *
     * @param key
     * @param expire
     * @return
     */
    private boolean setRedis(final String key, final long expire) {
        try {
            String result = redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    JedisCommands commands = (JedisCommands) redisConnection.getNativeConnection();
                    String uuid = UUID.randomUUID().toString();
                    lockFlag.set(uuid);
                    return commands.set(key, uuid, "NX", "PX", expire);
                }
            });
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 加锁具体实现
     * 如果获取锁失败，按照之前传入的重试次数进行重试
     *
     * @param key
     * @param expire
     * @param retryTimes
     * @param sleepMillis
     * @return
     */
    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        boolean result = setRedis(key, expire);
        while (!(result) && retryTimes-- > 0) {
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }

    /**
     * 释放锁
     * 可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另一个线程持有锁，不能直接删除
     *
     * @param key
     * @return
     */
    @Override
    public boolean releaseLock(String key) {
        try {
            final List<String> keys = new ArrayList<>();
            keys.add(key);
            final List<String> args = new ArrayList<>();
            args.add(lockFlag.get());
            /**
             * 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动失效
             * 的时候误删其他线程的锁。
             *
             * spring自带的执行脚本方法中，集群模式直接抛出不支持脚本执行的异常
             * 所以只能拿到原redis的connection来执行脚本
             */
            Long result = redisTemplate.execute(new RedisCallback<Long>() {
                /**
                 * 释放锁
                 * 集群模式和单机模式没有共同接口，需要分开执行
                 * @param redisConnection
                 * @return
                 * @throws DataAccessException
                 */
                @Override
                public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    Object nativeConnection = redisConnection.getNativeConnection();
                    if (nativeConnection instanceof JedisCluster) {
                        return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    } else if (nativeConnection instanceof Jedis) {
                        return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }
                    return 0L;
                }
            });
            return result != null && result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockFlag.remove();
        }
        return false;
    }
}

