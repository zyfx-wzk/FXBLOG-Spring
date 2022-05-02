package com.example.fxblog.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.entity.BlogEntity;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章处理
 *
 * @Author 王志康
 * @Date 2022/4/25 22:32
 */
@Slf4j
@RestController
public class BlogColltroller {
    @Autowired
    private BlogService blogService;

    /**
     * 获取博客内容
     */
    @RequestMapping(value = "/get/markdown", method = RequestMethod.GET)
    public CommonResult getBlogText(@RequestParam("uuid") Integer uuid) {
        return CommonResult.surress(blogService.getBlogText(uuid));
    }

    /**
     * 获取博客列表
     */
    @RequestMapping(value = "/get/blog", method = RequestMethod.GET)
    public CommonResult getBlogList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return CommonResult.surress(blogService.getBlogList(page, size));
    }

    /**
     * 添加博客
     */
    @RequestMapping(value = "/insert/blog", method = RequestMethod.POST)
    private CommonResult addMarkdown(@RequestBody JSONObject blog) {
        BlogEntity blogEntity = new BlogEntity(blog.getStr("text"), blog.getStr("title"));
        try {
            blogService.addBlog(blogEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.error(ResultCode.BLOG_ERROR);
        }
        return CommonResult.surress();
    }
}
