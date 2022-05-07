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

    private void verifyList(List list) {
        if (list.size() == 0) {
            log.warn("元数据的请求键值出错,可能数据库出现问题或者接口被恶意请求");
            throw new RuntimeException();
        }
    }


    /**
     * 获取元数据-JSON列表
     */
    @Override
    public List<JSONObject> getMetaJsonList(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        verifyList(list);
        List<JSONObject> result = new ArrayList<>();
        for (MetaEntity meta : list) {
            JSONObject str = JSONUtil.parseObj(meta.getMetaValue());
            result.add(str);
        }
        return result;
    }

    /**
     * 获取元数据-String列表
     */
    @Override
    public List<String> getMetaStringList(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        verifyList(list);
        List<String> result = new ArrayList<>();
        for (MetaEntity meta : list) {
            result.add(meta.getMetaValue());
        }
        return result;
    }

    /**
     * 获取元数据-String
     */
    @Override
    public String getMetaStringValue(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        verifyList(list);
        return list.get(0).getMetaValue();
    }

    /**
     * 获取元数据-JSON
     */
    @Override
    public JSONObject getMetaJsonValue(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        verifyList(list);
        return JSONUtil.parseObj(list.get(0).getMetaValue());
    }

    /**
     * 删除元数据(若value为空,则直接匹配键值,删除所有数据)
     * @param key 可以为空
     * @param value 可以为空
     */
    @Override
    public void delMetaValue(String key, String value) {
        QueryWrapper<MetaEntity> queryWrapper = new QueryWrapper<>();
        if (key != null) {
            queryWrapper.eq("meta_key", key);
        }
        if (value != null) {
            queryWrapper.eq("meta_value", value);
        }
        metaMapper.delete(queryWrapper);
    }


    /**
     * 增加元数据
     *
     * @param key 键
     * @param list 值列表
     */
    @Override
    public void addMetaValue(String key, List<String> list) {
        //插入数据前,删除数据库中已有的数据,防止重复数据产生
        for (String val : list) {
            delMetaValue(key, val);
            metaMapper.insert(new MetaEntity(key, val));
        }
    }
}
