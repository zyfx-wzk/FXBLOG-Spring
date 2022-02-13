package com.example.fxblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fxblog.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 王志康
 * @Date 2022/2/28 0:11
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
