package com.example.fxblog.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.service.MetaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(value = "/get/meta", method = RequestMethod.GET)
    public CommonResult getMetaData(@RequestParam("key") String key,
                                    @RequestParam("type") String type) {
        CommonResult result = new CommonResult<>();
        try {
            switch (type) {
                case "list": {
                    result = CommonResult.result(metaService.getMetaList(key));
                    break;
                }
                case "value": {
                    result = CommonResult.result(metaService.getMetaValue(key));
                    break;
                }
                default:
            }
        } catch (Exception e) {
            return CommonResult.error(ResultCode.GET_META_FAIL);
        }
        return result;
    }

    @RequestMapping(value = "/add/meta", method = RequestMethod.POST)
    public CommonResult setMetaData(@RequestBody JSONObject jsonMetaData) {
        String metaKey = jsonMetaData.getStr("key");
        String metaValue = jsonMetaData.getStr("value");
        try {
            metaService.setMetaValue(metaKey, metaValue);
        } catch (Exception e) {
            return CommonResult.error(ResultCode.GET_META_FAIL);
        }
        return CommonResult.surress();
    }
}
