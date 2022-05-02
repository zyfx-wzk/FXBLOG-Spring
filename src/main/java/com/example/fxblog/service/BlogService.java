package com.example.fxblog.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.fxblog.entity.BlogEntity;

import java.util.List;

/**
 * @Author 王志康
 * @Date 2022/4/25 22:33
 */
public interface BlogService {
    void addBlog(BlogEntity blogEntity);

    JSONObject getBlogText(Integer uuid);

    List<JSONObject> getBlogList(Integer page, Integer size);

}
