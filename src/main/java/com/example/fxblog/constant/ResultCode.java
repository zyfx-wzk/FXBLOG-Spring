package com.example.fxblog.constant;

import lombok.Data;

/**
 * @Author 王志康
 * @Date 2022/3/7 13:32
 */
public enum ResultCode {

    /**
     * 操作成功
     */
    SUCCESS(200,"成功"),
    /**
     * 操作失败
     */
    FAIL(400,"失败"),
    /**
     * 未登录或者登录已失效
     */
    NOT_LOGIN(401,"未登录或者登录已失效"),
    /**
     * 账号或密码错误
     */
    ERROR_LOGIN(402,"账号或密码错误");

    public final int code;
    private final String message;
    ResultCode(int code,String message) {
        this.code = code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
