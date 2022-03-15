package com.example.fxblog.controller;

import com.example.fxblog.annotation.PassToken;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.service.UserService;
import com.example.fxblog.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author 王志康
 * @Date 2022/2/24 22:11
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    private Map<String, String> map;

    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody Map<String, String> map) {
        String username=map.get("username");
        String password=map.get("password");
        String remember=map.get("remember");
        if (userService.login(username, password)) {
            String token = JwtUtil.createToken(username, "true".equals(remember));
            return CommonResult.success(token);
        } else {
            return CommonResult.fail();
        }
    }
}
