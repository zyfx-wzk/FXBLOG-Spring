package com.example.fxblog.controller;

import com.example.fxblog.other.CommonResult;
import com.example.fxblog.other.annotation.NeedToken;
import com.example.fxblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @Author 王志康
 * @Date 2022/2/13 20:22
 */

@Slf4j
@RestController
public class TestController {
    @Autowired
    private UserService userInfoService;

    @NeedToken
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public CommonResult test() {
        return CommonResult.result("Test");
    }
}
