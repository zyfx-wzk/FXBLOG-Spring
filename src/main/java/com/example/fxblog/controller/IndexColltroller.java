package com.example.fxblog.controller;

import com.example.fxblog.other.CommonResult;
import com.example.fxblog.utils.MusicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 王志康
 * @Date 2022/5/5 18:13
 */
@Controller
public class IndexColltroller {
    @Autowired
    private MusicUtil musicUtil;

    @RequestMapping(value = "/,/index")
    public String index() {
        return "index.html";
    }

    @ResponseBody
    @RequestMapping(value = "/get/music", method = RequestMethod.GET)
    public CommonResult getMusciList() {
        return CommonResult.surress(musicUtil.getMusicList());
    }

    @ResponseBody
    @RequestMapping(value = "/update/music", method = RequestMethod.GET)
    public CommonResult updateMusciList() {
        musicUtil.loadingMusicList();
        return CommonResult.surress();
    }
}
