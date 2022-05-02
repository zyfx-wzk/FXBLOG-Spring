package com.example.fxblog.service.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import com.example.fxblog.service.ImageService;
import com.example.fxblog.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王志康
 * @Date 2021/12/13 18:15
 */

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageUtil imageUtil;

    /**
     * 获取随机图片
     * @return 随机图片url
     */
    @Override
    public String getImage() {
        return imageUtil.getImageUrl();
    }

    /**
     * 获取随机图片列表
     * @return 随机图片url列表
     */
    @Override
    public List<String> getImage(int count) {
        return imageUtil.getImageUrlList(count);
    }

    /**
     * 手动更新图片缓存
     */
    @Override
    public long updateImage() {
        return imageUtil.setImageUrlList();
    }
}
