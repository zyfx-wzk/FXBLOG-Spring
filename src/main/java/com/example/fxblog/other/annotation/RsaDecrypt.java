package com.example.fxblog.other.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否对前端数据进行RSA解密
 * @Author 王志康
 * @Date 2022/3/23 17:03
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RsaDecrypt  {
    boolean required() default true;
}
