package boss.xtrain.cache.redis.configure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description Redis使用FastJson序列化
 * @create 2020-07-02
 * @since 1.0
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {
    @SuppressWarnings("unused")
    private ObjectMapper objectMapper;

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final Class<T> clazz;

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * redis对象序列化
     *
     * @param t redis对象
     * @return json字符串
     */
    @Override
    public byte[] serialize(T t) {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    /**
     * redis对象反序列化
     *
     * @param bytes 字符串
     * @return redis对象
     */
    @Override
    public T deserialize(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz);
    }

    /**
     * 设置ObjectMapper
     *
     * @param objectMapper ObjectMapper
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "'objectMapper' must not be null");
        this.objectMapper = objectMapper;
    }

    /**
     * 获得java类型
     *
     * @param clazz 指定的Java类型
     * @return 类型
     */
    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
