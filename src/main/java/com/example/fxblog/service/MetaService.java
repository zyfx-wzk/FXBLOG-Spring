package com.example.fxblog.service;

import cn.hutool.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author 王志康
 * @Date 2022/4/3 21:32
 */
public interface MetaService {
    List<JSONObject> getMetaJsonList(String key);

    List<String> getMetaStringList(String key);

    JSONObject getMetaJsonValue(String key);

    String getMetaStringValue(String key);

    void addMetaValue(String key, List<String> list);

    void delMetaValue(String key, String value);
}
