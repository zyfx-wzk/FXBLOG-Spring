package com.example.fxblog.other.Exception;

import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.other.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

/**
 * 统一异常拦截类
 *
 * @Author 王志康
 * @Date 2022/4/14 13:28
 */
@Slf4j
@RestControllerAdvice
public class HandlerException {
    /**
     * 处理请求返回时抛出的异常
     */
    @ResponseBody
    @ExceptionHandler(ReturnException.class)
    public CommonResult handleThrowException(ReturnException e) {
        log.error(e.getMessage(), e);
        return CommonResult.error(e.getResultCode());
    }

    /**
     * 处理请求运行时抛出的异常
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public CommonResult handleThrowException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return CommonResult.error(ResultCode.ERROR);
    }
}
