package com.example.fxblog.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.service.MetaService;
import com.example.fxblog.utils.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 额外的小数据,以map方式存储
 *
 * @Author 王志康
 * @Date 2022/4/3 21:12
 */
@Slf4j
@RestController
public class MetaController {
    @Autowired
    private MetaService metaService;

    /**
     * 获取RSA公钥
     */
    @RequestMapping(value = "/get/rsa", method = RequestMethod.GET)
    public CommonResult getRsa() {
        return CommonResult.surress(RsaUtil.RSA_PUBLIC_KEY);
    }

    /**
     * 添加元数据
     */
    @RequestMapping(value = "/insert/meta", method = RequestMethod.POST)
    public CommonResult setMetaData(@RequestBody JSONObject jsonMetaData) {
        String metaKey = jsonMetaData.getStr("key");
        String metaValue = jsonMetaData.getStr("list");
        List<String> list = JSONUtil.toList(JSONUtil.parseArray(metaValue), String.class);
        try {
            metaService.addMetaValue(metaKey,list);
        } catch (Exception e) {
            return CommonResult.error(ResultCode.META_ERROR);
        }
        return CommonResult.surress();
    }

    /**
     * 删除元数据
     */
    @RequestMapping(value = "/delete/meta", method = RequestMethod.POST)
    public CommonResult delMetaData(@RequestBody JSONObject jsonMetaData) {
        String metaKey = jsonMetaData.getStr("key");
        String metaValue = jsonMetaData.getStr("value");
        List<String> list = JSONUtil.toList(JSONUtil.parseArray(metaValue), String.class);
        try {
            metaService.delMetaValue(metaKey, metaValue);
        } catch (Exception e) {
            return CommonResult.error(ResultCode.META_ERROR);
        }
        return CommonResult.surress();
    }

    /**
     * 获取元数据-列表
     */
    @RequestMapping(value = "/get/meta", method = RequestMethod.GET)
    public CommonResult getMetaData(@RequestParam("key") String key,
                                    @RequestParam("type") String type) {
        try {
            switch (type) {
                case "list": {
                    return CommonResult.surress(metaService.getMetaJsonList(key));
                }
                case "value": {
                    return CommonResult.surress(metaService.getMetaValue(key));
                }
                default:
            }
        } catch (Exception e) {
            return CommonResult.error(ResultCode.META_ERROR);
        }
        throw new RuntimeException("接口请求参数错误");
    }
}
