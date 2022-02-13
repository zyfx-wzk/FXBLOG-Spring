package com.example.fxblog.controller;

import com.example.fxblog.annotation.PassToken;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.service.UserService;
import com.example.fxblog.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 王志康
 * @Date 2022/2/24 22:11
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestParam("name") String name, @RequestParam("password")  String password) {
        if (userService.login(name, password)) {
            String token = JwtUtil.createToken(name, false);
            return CommonResult.success(token);
        } else {
            return CommonResult.fail();
        }
    }
}
