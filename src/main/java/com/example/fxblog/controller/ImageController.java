package com.example.fxblog.controller;

import com.example.fxblog.other.CommonResult;
import com.example.fxblog.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 图片接口
 * @Author 王志康
 * @Date 2022/3/31 14:31
 */
@Slf4j
@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/get/image", method = RequestMethod.GET)
    public CommonResult getImageUrl() {
        return CommonResult.surress(imageService.getImage());
    }

    @RequestMapping(value = "/see/image", method = RequestMethod.GET)
    public ModelAndView seeImageView() {
        return new ModelAndView("redirect:" + imageService.getImage());
    }
}
