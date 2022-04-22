package com.example.fxblog.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.service.ImageService;
import com.example.fxblog.service.MetaService;
import com.example.fxblog.utils.ImageUtil;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 图片接口
 *
 * @Author 王志康
 * @Date 2022/3/31 14:31
 */
@Slf4j
@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private MetaService metaService;

    @RequestMapping(value = "/update/image", method = RequestMethod.POST)
    public CommonResult updateImageUrl(@RequestBody JSONArray imageList) {
        List<String> list = JSONUtil.toList(imageList, String.class);
        metaService.addMetaValue(ImageUtil.IMAGE_URL, list);
        return CommonResult.surress(imageService.updateImage());
    }

    @RequestMapping(value = "/get/image", method = RequestMethod.GET)
    public CommonResult getImageUrl(@RequestParam(value = "count", defaultValue = "1") int count) {
        return CommonResult.surress(count == 1 ? imageService.getImage() : imageService.getImage(count));
    }

    @RequestMapping(value = "/see/image", method = RequestMethod.GET)
    public ModelAndView seeImageView() {
        return new ModelAndView("redirect:" + imageService.getImage());
    }
}
