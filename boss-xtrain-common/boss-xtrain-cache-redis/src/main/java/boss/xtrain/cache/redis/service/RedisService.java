package boss.xtrain.cache.redis.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description spring redis 工具类
 * @create 2020-07-02
 * @since 1.0
 */

@Component
public class RedisService {

    @Resource
    public RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * 简单K-V操作
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public ValueOperations<String, Object> setCacheObject(String key, Object value) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * 简单K-V操作
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    public ValueOperations<String, Object> setCacheObject(String key, Object value, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        operation.set(key, value, timeout, timeUnit);
        return operation;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Object getCacheObject(String key) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key 键
     */
    public void deleteObject(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 键的集合
     */
    public void deleteObject(Collection<String> collection) {
        redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public ListOperations<String, Object> setCacheList(String key, List<Object> dataList) {
        ListOperations<String, Object> listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            for (Object o : dataList) {
                listOperation.leftPush(key, o);
            }
        }
        return listOperation;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public List<Object> getCacheList(String key) {
        List<Object> dataList = new ArrayList<>();
        ListOperations<String, Object> listOperation = redisTemplate.opsForList();
        long size = listOperation.size(key);

        for (int i = 0; i < size; i++) {
            dataList.add(listOperation.index(key, i));
        }
        return dataList;
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public BoundSetOperations<String, Object> setCacheSet(String key, Set<Object> dataSet) {
        BoundSetOperations<String, Object> setOperation = redisTemplate.boundSetOps(key);
        for (Object o : dataSet) {
            setOperation.add(o);
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key 键
     * @return  set
     */
    public Set<Object> getCacheSet(String key) {
        BoundSetOperations<String, Object> operation = redisTemplate.boundSetOps(key);
        return operation.members();
    }

    /**
     * 缓存Map
     *
     * @param key  键
     * @param dataMap 缓存的数据
     * @return 缓存数据的对象
     */
    public HashOperations<String, String, Object> setCacheMap(String key, Map<String, Object> dataMap) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key 键
     * @return  缓存的Map
     */
    public Map<Object, Object> getCacheMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
