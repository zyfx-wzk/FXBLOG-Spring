package com.example.fxblog.service;

import java.util.List;
import java.util.Map;

/**
 * @Author 王志康
 * @Date 2022/4/3 21:32
 */
public interface MetaService {
    Map<String,String> getMetaList(String key);

    String getMeta(String key);
}
