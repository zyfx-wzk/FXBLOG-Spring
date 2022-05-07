package com.example.fxblog.config;

import com.example.fxblog.other.Interceptor.CorsInterceptor;
import com.example.fxblog.other.Interceptor.RsaInterceptor;
import com.example.fxblog.other.Interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求拦截器
 *
 * @Author 王志康
 * @Date 2022/3/7 12:28
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //跨域拦截
        registry.addInterceptor(getCorsInterceptor()).addPathPatterns("/**");
        //登录拦截
        registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/**");
        //数据加密拦截
        registry.addInterceptor(getRsaInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public RsaInterceptor getRsaInterceptor() {
        return new RsaInterceptor();
    }

    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    public CorsInterceptor getCorsInterceptor() {
        return new CorsInterceptor();
    }
}
