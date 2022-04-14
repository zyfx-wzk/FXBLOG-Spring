package com.example.fxblog.constant;

/**
 * 通用状态码与返回消息
 *
 * @Author 王志康
 * @Date 2022/3/7 13:32
 */
public enum ResultCode {

    /**
     * 操作成功
     */
    SUCCESS(200,"请求成功"),
    /**
     * 操作失败
     */
    ERROR(400, "请求失败"),
    /**
     * 数据解码失败
     */
    DECODING_ERROR(301,"数据解码失败"),
    /**
     * 无法获取页面元数据
     */
    META_ERROR(302,"无法获取页面元数据"),
    /**
     * 未登录或登录已失效
     */
    LOGIN_NOT(401,"未登录或登录已失效"),
    /**
     * 账号或密码错误
     */
    LOGIN_ERROR(402,"账号或密码错误"),
    /**
     * 服务端错误
     */
    SYSTEM_ERROR(500,"系统错误");

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
