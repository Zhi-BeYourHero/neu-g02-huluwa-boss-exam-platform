package boss.xtrain.cache.redis.configure;

import boss.xtrain.cache.redis.lock.Lock;
import boss.xtrain.cache.redis.lock.impl.JedisDistributeLock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 分布式锁自动配置
 * @create 2020-07-03 20:18
 * @since 1.0
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DistributedLockAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public Lock jedisDistributeLock(RedisTemplate<String,Object> redisTemplate){
        return new JedisDistributeLock(redisTemplate);
    }
}
