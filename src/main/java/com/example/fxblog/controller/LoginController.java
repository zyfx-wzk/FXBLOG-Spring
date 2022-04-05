package com.example.fxblog.controller;

import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.other.annotation.RsaDecrypt;
import com.example.fxblog.service.UserService;
import com.example.fxblog.utils.JwtUtil;
import com.example.fxblog.utils.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录和RSA密钥获取
 * @Author 王志康
 * @Date 2022/2/24 22:11
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RsaDecrypt(required = false)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody Map<String, String> map) {
        log.info(String.valueOf(map));
        String username = map.get("username");
        String password = map.get("password");
        String remember = map.get("remember");
        if (userService.login(username, password)) {
            String token = JwtUtil.createToken(username, "true".equals(remember));
            return CommonResult.result(token);
        } else {
            return CommonResult.error(ResultCode.NOT_LOGIN);
        }
    }

    @RequestMapping(value = "/get/rsa", method = RequestMethod.GET)
    public CommonResult getRsa() {
        return CommonResult.result(RsaUtil.RSA_PUBLIC_KEY);
    }
}
