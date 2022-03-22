package com.example.fxblog.service.serviceImpl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.fxblog.entity.UserEntity;
import com.example.fxblog.mapper.UserMapper;
import com.example.fxblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 王志康
 * @Date 2022/2/25 23:29
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        //根据用户名获取用户对象
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_username", username);
        UserEntity user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return false;
        }
        //判断密码是否一致
        log.info(DigestUtil.md5Hex(password + user.getUserSalt()));
        return user.getUserPassword().equals(DigestUtil.md5Hex(password + user.getUserSalt()));
    }

}
