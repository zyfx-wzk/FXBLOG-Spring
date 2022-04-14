package com.example.fxblog.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyPair;

/**
 * RSA加解密工具类
 * @Author 王志康
 * @Date 2022/3/22 14:44
 */

@Component
public class RsaUtil {
    public static final String PUBLIC_KEY = "RSA_PUBLIC_KEY";
    public static final String PRIVATE_KEY = "RSA_PRIVATE_KEY";

    /**
     * 公钥
     */
    public static String RSA_PUBLIC_KEY;

    /**
     * 私钥
     */
    public static String RSA_PRIVATE_KEY;

    /**
     * rsa加解密对象
     */
    private static RSA rsa;

    /**
     * 从Redis数据库中获取密钥,若不存在则新建RSA对象,并将密钥存入redis数据库中
     *
     * @param redisUtil redis工具类,同一层级无法使用@Autowired自动注入
     */
    @Autowired
    public RsaUtil(RedisUtil redisUtil) {
        if (redisUtil.hasKey(PUBLIC_KEY)) {
            RSA_PUBLIC_KEY = (String) redisUtil.get(PUBLIC_KEY);
            RSA_PRIVATE_KEY = (String) redisUtil.get(PRIVATE_KEY);
            rsa = new RSA(RSA_PRIVATE_KEY, RSA_PUBLIC_KEY);
        } else {
            rsa = new RSA();
            RSA_PUBLIC_KEY = rsa.getPublicKeyBase64();
            RSA_PRIVATE_KEY = rsa.getPrivateKeyBase64();
            redisUtil.set(PUBLIC_KEY, RSA_PUBLIC_KEY, 3600 * 2400);
            redisUtil.set(PRIVATE_KEY, RSA_PRIVATE_KEY, 3600 * 2400);
        }
    }

    /**
     * 使用私钥解码加密数据
     *
     * @param data 加密数据
     * @return 解码数据
     */
    public String decrypt(String data) {
        return rsa.decryptStr(data, KeyType.PrivateKey);
    }
}
