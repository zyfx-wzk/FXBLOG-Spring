package com.example.fxblog.utils;

import com.example.fxblog.service.MetaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 图片接口工具类
 *
 * @Author 王志康
 * @Date 2021/12/17 16:20
 */

@Slf4j
@Component
public class ImageUtil {
    public static final String IMAGE_URL_LIST = "Image_List";
    public static final String IMAGE_URL = "image_url";

    private final MetaService metaService;
    private final RedisUtil redisUtil;

    private long listLength = 0;

    ImageUtil(RedisUtil redisUtil, MetaService metaService) {
        this.redisUtil = redisUtil;
        this.metaService = metaService;
        setImageList();
    }

    /**
     * 加载图片缓存
     *
     * @return 当前缓存数量
     */
    public long setImageList() {
        delImageList();
        List<String> list = metaService.getMetaStringList(IMAGE_URL);
        redisUtil.setAdd(IMAGE_URL_LIST, 0, list.toArray());
        listLength = redisUtil.setSize(IMAGE_URL_LIST);
        log.info("图片列表加载完成,当前Redis缓存中共" + listLength + "张图片");
        return listLength;
    }

    /**
     * 删除图片缓存
     */
    public void delImageList() {
        redisUtil.remove(IMAGE_URL_LIST);
    }

    /**
     * 获取随机图片
     */
    public String getImage() {
        return (String) redisUtil.setValue(IMAGE_URL_LIST);
    }

    /**
     * 获取随机图片列表
     */
    public List<String> getImageList(int count) {
        List<String> result = new ArrayList<>();
        if (count <= listLength) {
            Set<Object> set = redisUtil.setValueSet(IMAGE_URL_LIST, count);
            for (Object o : set) {
                result.add((String) o);
            }
        } else {
            List<Object> list = redisUtil.setValueList(IMAGE_URL_LIST, count);
            for (Object o : list) {
                result.add((String) o);
            }
        }
        return result;
    }
}
