package com.example.fxblog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtToekn工具类
 *
 * @Author 王志康
 * @Date 2022/2/25 23:07
 */

@Slf4j
@Component
public class JwtUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    //私钥
    private static final String SECRET = "zyfx_wzk";
    //签发者
    private static final String ISS = "FXBLOG";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION_TIME = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_TIME_REMEMBER = 604800L;

    //获取Token
    public static String createToken(String userName, boolean isRemember) {
        long expirationTime = isRemember ? EXPIRATION_TIME_REMEMBER : EXPIRATION_TIME;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                .compact();
    }

    //获取Token对象
    private static Claims getTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    //获取Token中的用户名
    public static String getUserName(String token) {
        try {
            return getTokenClaims(token).getSubject();
        } catch (Exception e) {
            log.warn("用户使用伪造的Token进行登录");
            return "";
        }
    }

    //检查是否已过期
    public static boolean isExpiration(String token) {
        try {
            return getTokenClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            log.warn("用户使用伪造的Token进行登录");
            return true;
        }

    }
}
