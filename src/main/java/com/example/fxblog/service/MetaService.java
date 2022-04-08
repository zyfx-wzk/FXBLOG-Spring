package com.example.fxblog.service;

import cn.hutool.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author 王志康
 * @Date 2022/4/3 21:32
 */
public interface MetaService {
    List<JSONObject> getMetaList(String key);

    JSONObject getMetaValue(String key);

    void setMetaValue(String key,String value);
}
