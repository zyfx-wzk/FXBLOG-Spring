package com.example.fxblog.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyPair;

/**
 * @Author 王志康
 * @Date 2022/3/22 14:44
 */

@Component
public class RsaUtil {
    public static final String PUBLIC_KEY = "RSA_PUBLIC_KEY";
    public static final String PRIVATE_KEY = "RSA_PRIVATE_KEY";

    public static String RSA_PUBLIC_KEY;
    public static String RSA_PRIVATE_KEY;

    private static RSA rsa;

    @Autowired
    public RsaUtil(RedisUtil redisUtil) {
        if (redisUtil.hasKey(PUBLIC_KEY)) {
            RSA_PUBLIC_KEY = (String) redisUtil.get(PUBLIC_KEY);
            RSA_PRIVATE_KEY = (String) redisUtil.get(PRIVATE_KEY);
            rsa = new RSA(RSA_PRIVATE_KEY, RSA_PUBLIC_KEY);
        } else {
            rsa=new RSA();
            RSA_PUBLIC_KEY = rsa.getPublicKeyBase64();
            RSA_PRIVATE_KEY = rsa.getPrivateKeyBase64();
            redisUtil.set(PUBLIC_KEY, RSA_PUBLIC_KEY, 3600 * 2400);
            redisUtil.set(PRIVATE_KEY, RSA_PRIVATE_KEY, 3600 * 2400);
        }
    }
}
