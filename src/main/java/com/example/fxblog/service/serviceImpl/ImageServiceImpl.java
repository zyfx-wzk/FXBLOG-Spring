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

    @Override
    public String getImage() {
        List<String> list = imageUtil.imageList;
        int n = RandomUtil.randomInt(0, imageUtil.listLen);
        return list.get(n);
    }
}
