package com.example.fxblog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis工具类
 *
 * @Author 王志康
 * @Date 2022/2/21 22:12
 */

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

}
