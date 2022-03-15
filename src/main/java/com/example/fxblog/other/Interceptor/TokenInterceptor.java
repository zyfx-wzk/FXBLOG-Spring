package com.example.fxblog.other.Interceptor;

import cn.hutool.json.JSONUtil;
import com.example.fxblog.annotation.PassToken;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Token拦截验证
 *
 * @Author 王志康
 * @Date 2022/3/7 12:50
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Token");
        //仅拦截方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        //若有Passtoken注解,则跳过检查
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        } else {
            if (token == null || JwtUtil.isExpiration(token)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer;
                writer = response.getWriter();
                writer.print(JSONUtil.parseObj(CommonResult.notLogin()));
                return false;
            }
            String userName = JwtUtil.getUserName(token);
            request.setAttribute("userName", userName);
            return true;
        }
        return true;
    }
}
