package com.example.fxblog.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 使用fastjson重写的RedisSerializer
 * @Author 王志康
 * @Date 2022/2/17 22:18
 */
public class FastjsonRedisSerializer<T> implements RedisSerializer<T> {
    //默认序列化编码为UTF-8
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final Class<T> clazz;

    public FastjsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    //使用Fastjson重写序列化方法
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(StandardCharsets.UTF_8);
    }

    //使用Fastjson重写反序列化方法
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        return JSON.parseObject(new String(bytes, DEFAULT_CHARSET), clazz);
    }
}
