package com.example.fxblog.utils;

import cn.hutool.json.JSONUtil;
import com.example.fxblog.other.CommonResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @Author 王志康
 * @Date 2022/3/31 15:21
 */
@Component
public class ErrorUtil {
    //拦截器错误封装
    public void writerError(HttpServletResponse response, CommonResult error) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer;
        writer = response.getWriter();
        writer.print(JSONUtil.parseObj(error));
    }
}
