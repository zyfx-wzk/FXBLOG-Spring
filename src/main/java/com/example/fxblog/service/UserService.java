package com.example.fxblog.service;

import com.example.fxblog.entity.UserEntity;

import java.util.List;

/**
 * @Author 王志康
 * @Date 2022/2/25 23:27
 */
public interface UserService {
    //用户登录
    boolean login(String name,String password);
}
