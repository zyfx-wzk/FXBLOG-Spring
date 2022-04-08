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
    //状态码
    private Integer status;
    //返回信息
    private String message;
    //实际数据
    private T data;

    /*以下为静态方法，作用是利用本类的构造方法将三个元素组装在一起
	生成一个本类对象并返回，根据不同的响应状态自行调用不同的方法，可以利用
	上面写好的枚举类型填充返回状态码和返回信息，也可以自行传入返回状态码和
	返回信息*/
    public static <T> CommonResult result(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult surress() {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),null);
    }

    public static <T> CommonResult error(ResultCode resultCode) {
        return new CommonResult<>(resultCode.getCode(), resultCode.getMessage(),null);
    }
}
