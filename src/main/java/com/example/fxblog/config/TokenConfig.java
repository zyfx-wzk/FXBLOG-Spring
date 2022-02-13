package com.example.fxblog.config;

import com.example.fxblog.other.TokenAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求拦截器,筛选未登录用户
 * @Author 王志康
 * @Date 2022/3/7 12:28
 */
@Configuration
public class TokenConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //默认拦截所有路径
        registry.addInterceptor(tokenAuthentication())
                .addPathPatterns("/**");
    }

    @Bean
    public TokenAuthentication tokenAuthentication(){
        return new TokenAuthentication();
    }
}
