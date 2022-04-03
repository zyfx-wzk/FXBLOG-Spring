package com.example.fxblog.other.Interceptor;

import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.other.annotation.NeedToken;
import com.example.fxblog.utils.ErrorUtil;
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
    @Autowired
    private ErrorUtil errorUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
                    errorUtil.writerError(response, CommonResult.error(ResultCode.NOT_LOGIN));
                    log.warn("未使用或使用篡改的token进行操作");
                    return false;
                }
                String userName = JwtUtil.getUserName(token);
                request.setAttribute("userName", userName);
            }
        }
        return true;
    }
}
