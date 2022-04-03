package com.example.fxblog.utils;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Author 王志康
 * @Date 2021/12/17 16:20
 */

@Slf4j
@Component
public class ImageUtil {
    public List<String> imageList;
    public int listLen;

    ImageUtil() throws FileNotFoundException {
        getImageList();
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void getImageList() throws FileNotFoundException {
        File file = new File(ResourceUtils.getURL("classpath:").getPath());
        String basePath = file.getParentFile().getParent();
        FileReader fileReader = new FileReader(basePath + "\\json\\ImageList.json");
        JSONArray array = JSONUtil.parseArray(fileReader.readString());
        imageList = JSONUtil.toList(array, String.class);
        listLen = imageList.size();
        log.info("图片列表加载完成,当前共" + imageList.size() + "张图片");
    }
}
