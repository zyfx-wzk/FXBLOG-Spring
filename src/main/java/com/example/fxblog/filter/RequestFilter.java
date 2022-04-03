package com.example.fxblog.filter;

import com.example.fxblog.other.BodyRequsetWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author 王志康
 * @Date 2022/3/23 18:09
 */
@WebFilter(filterName = "RequestFilter",urlPatterns = "/*")
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //使用自己定义的BodyReaderRequsetWrapper进行替换,以实现对body的修改和重复读取
        BodyRequsetWrapper bodyReaderRequsetWrapper = new BodyRequsetWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(bodyReaderRequsetWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
