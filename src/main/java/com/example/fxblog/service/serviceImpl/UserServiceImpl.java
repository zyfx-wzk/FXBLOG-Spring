package com.example.fxblog.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.fxblog.entity.UserEntity;
import com.example.fxblog.mapper.UserMapper;
import com.example.fxblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 王志康
 * @Date 2022/2/25 23:29
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_username", username);
        queryWrapper.eq("user_password", password);
        long num = userMapper.selectCount(queryWrapper);
        return num != 0;
    }
}
