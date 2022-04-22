package com.example.fxblog.service;

import java.util.List;

/**
 * @Author 王志康
 * @Date 2022/3/31 14:34
 */
public interface ImageService {
    String getImage();

    List<String> getImage(int count);

    long updateImage();
}
