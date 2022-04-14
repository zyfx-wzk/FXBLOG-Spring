package com.example.fxblog.other;

import cn.hutool.json.JSONUtil;
import com.example.fxblog.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回类
 *
 * @Author 王志康
 * @Date 2022/3/7 13:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 实际数据
     */
    private T data;

    /**
     * 请求成功,返回正常请求数据
     */
    public static <T> CommonResult surress(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 请求成功,但无需返回数据
     */
    public static <T> CommonResult surress() {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 请求失败,返回默认失败信息
     */
    public static <T> CommonResult error() {
        return new CommonResult<>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMessage(), null);
    }

    /**
     * 请求失败,返回自定义失败信息
     */
    public static <T> CommonResult error(ResultCode resultCode) {
        return new CommonResult<>(resultCode.getCode(), resultCode.getMessage(), null);
    }
}
