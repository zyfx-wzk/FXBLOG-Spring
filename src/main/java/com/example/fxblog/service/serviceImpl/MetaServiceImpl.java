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
    public List<JSONObject> getMetaList(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        verifyList(list);
        List<JSONObject> result = new ArrayList<>();
        for (MetaEntity meta : list) {
            JSONObject str = JSONUtil.parseObj(meta.getMetaValue());
            log.info(str.toString());
            result.add(str);
        }
        return result;
    }

    /**
     * 获取元数据-对象
     */
    @Override
    public JSONObject getMetaValue(String key) {
        List<MetaEntity> list = metaMapper.selectList(getValue(key));
        verifyList(list);
        return JSONUtil.parseObj(list.get(0).getMetaValue());
    }

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


    @Override
    public void setMetaValue(String key, String value) {
        //插入数据前,删除数据库中已有的数据,防止重复数据产生
        delMetaValue(key, value);
        metaMapper.insert(new MetaEntity(key, value));
    }
}
