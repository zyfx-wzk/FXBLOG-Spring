package com.example.fxblog.other.Exception;

import com.example.fxblog.constant.ResultCode;
import lombok.Data;
import lombok.Getter;

/**
 * 请求返回异常信息
 *
 * @Author 王志康
 * @Date 2022/4/14 14:16
 */
@Getter
public class ReturnException extends RuntimeException {
    private ResultCode resultCode = ResultCode.ERROR;

    public ReturnException(String message){
        super(message);
    }

    public ReturnException(ResultCode resultCode) {
        super();
        this.resultCode = resultCode;
    }

    public ReturnException(String message, ResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }
}
