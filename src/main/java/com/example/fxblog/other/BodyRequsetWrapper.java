package com.example.fxblog.other;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * request的替代类,使body可以修改和多次读取
 *
 * @Author 王志康
 * @Date 2022/3/23 17:55
 */
@Slf4j
public class BodyRequsetWrapper extends HttpServletRequestWrapper {
    private String body;

    /**
     * 提前读取传输流,并保存body数据
     *
     * @param request 网络请求
     */
    public BodyRequsetWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream inputStream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            stringBuilder.append(str);
        }
        body = stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream bytes = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return bytes.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
