package com.example.fxblog.service.serviceImpl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.fxblog.entity.MetaEntity;
import com.example.fxblog.mapper.MetaMapper;
import com.example.fxblog.service.MetaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<JSONObject> getMetaList(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        List<JSONObject> result = new ArrayList<>();
        for (MetaEntity meta : list) {
            JSONObject str = JSONUtil.parseObj(meta.getMetaValue());
            log.info(str.toString());
            result.add(str);
        }
        return result;
    }

    @Override
    public JSONObject getMetaValue(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        return JSONUtil.parseObj(list.get(0).getMetaValue());
    }

    @Override
    public void setMetaValue(String key, String value) {
        metaMapper.insert(new MetaEntity(key, value));
    }
}
