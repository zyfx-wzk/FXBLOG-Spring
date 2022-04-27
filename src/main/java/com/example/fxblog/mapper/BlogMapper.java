package com.example.fxblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fxblog.entity.BlogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 王志康
 * @Date 2022/4/25 19:49
 */
@Mapper
public interface BlogMapper extends BaseMapper<BlogEntity> {
}
