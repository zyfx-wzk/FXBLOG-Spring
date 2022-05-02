package com.example.fxblog.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.example.fxblog.other.FastjsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis序列化配置
 * @Author 王志康
 * @Date 2022/2/17 22:37
 */

@Configuration
public class RedisConfig {
    /**
     * 当我们的数据存储到Redis的时候，我们的键（key）和值（value）都是通过Spring提供的Serializer序列化到数据库的。
     * RedisTemplate默认使用的是JdkSerializationRedisSerializer，StringRedisTemplate默认使用的是StringRedisSerializer。
     * Spring Data JPA为我们提供了下面的Serializer：
     * GenericToStringSerializer、Jackson2JsonRedisSerializer、
     * JacksonJsonRedisSerializer、JdkSerializationRedisSerializer、
     * OxmSerializer、StringRedisSerializer。
     * 在此我们将自己配置RedisTemplate并定义Serializer。
     */
    //Bean注解在服务启动时将此函数返回的对象注册到IOC容器中
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        FastjsonRedisSerializer<Object> fastjsonRedisSerializer = new FastjsonRedisSerializer<>(Object.class);
        //指定作用域白名单
        ParserConfig.getGlobalInstance().addAccept("com.example");

        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(fastjsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastjsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }
}
