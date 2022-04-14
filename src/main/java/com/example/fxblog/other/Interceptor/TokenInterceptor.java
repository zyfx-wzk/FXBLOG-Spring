package com.example.fxblog.other.Interceptor;

import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.other.Exception.ReturnException;
import com.example.fxblog.other.annotation.NeedToken;
import com.example.fxblog.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Token");
        //仅拦截方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        //若有Passtoken注解,则跳过检查
        if (method.isAnnotationPresent(NeedToken.class)) {
            NeedToken needToken = method.getAnnotation(NeedToken.class);
            if (needToken.required()) {
                if (token == null || JwtUtil.isExpiration(token)) {
                    throw new ReturnException("用户使用伪造的token进行登录",ResultCode.LOGIN_NOT);
                }
                String userName = JwtUtil.getUserName(token);
                request.setAttribute("userName", userName);
            }
        }
        return true;
    }
}
