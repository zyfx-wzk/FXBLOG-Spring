package com.example.fxblog.other.Interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.other.BodyRequsetWrapper;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.other.Exception.ReturnException;
import com.example.fxblog.other.annotation.RsaDecrypt;
import com.example.fxblog.utils.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 数据解密拦截
 *
 * @Author 王志康
 * @Date 2022/3/23 17:05
 */
@Slf4j
public class RsaInterceptor implements HandlerInterceptor {
    @Autowired
    private RsaUtil rsaUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //仅拦截POST请求
        if (!HttpMethod.POST.toString().equals(request.getMethod())) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        //若有RSADecrypt注解,则对数据进行解密
        if (method.isAnnotationPresent(RsaDecrypt.class)) {
            RsaDecrypt rsaDecrypt = method.getAnnotation(RsaDecrypt.class);
            if (rsaDecrypt.required() && request instanceof BodyRequsetWrapper) {
                BodyRequsetWrapper requsetWrapper = (BodyRequsetWrapper) request;
                //由于通用JSON传输的格式问题,需要对加密数据进行包装
                try {
                    JSONObject json = JSONUtil.parseObj((requsetWrapper).getBody());
                    String data = rsaUtil.decrypt(json.getStr("data"));
                    log.info(data);
                    requsetWrapper.setBody(data);
                } catch (Exception e) {
                    throw new ReturnException("加密数据疑似被篡改,无法进行解码",ResultCode.DECODING_ERROR);
                }
            }
        }
        return true;
    }
}
