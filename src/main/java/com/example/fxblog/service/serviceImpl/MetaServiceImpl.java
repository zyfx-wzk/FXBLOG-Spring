package com.example.fxblog.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.fxblog.entity.MetaEntity;
import com.example.fxblog.mapper.MetaMapper;
import com.example.fxblog.service.MetaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 王志康
 * @Date 2022/4/3 21:35
 */
@Slf4j
@Service
public class MetaServiceImpl implements MetaService {
    @Autowired
    private MetaMapper metaMapper;

    private QueryWrapper<MetaEntity> getValue(String key) {
        QueryWrapper<MetaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("meta_key", key);
        return queryWrapper;
    }

    @Override
    public Map<String, String> getMetaList(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        Map<String, String> result = new HashMap<>(list.size());
        for (MetaEntity meta : list) {
            String[] str = meta.getMetaValue().split("@@@@@");
            log.info(Arrays.toString(str));
            result.put(str[0], str[1]);
        }
        return result;
    }

    @Override
    public String getMeta(String key) {
        MetaEntity meta = metaMapper.selectOne(getValue(key));
        return meta.getMetaValue();
    }
}
