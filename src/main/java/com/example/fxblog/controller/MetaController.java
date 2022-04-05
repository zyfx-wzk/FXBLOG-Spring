package com.example.fxblog.controller;

import com.example.fxblog.constant.ResultCode;
import com.example.fxblog.other.CommonResult;
import com.example.fxblog.service.MetaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/meta", method = RequestMethod.GET)
    public CommonResult login(@RequestBody Map<String, String> map) {
        String key = map.get("key");
        CommonResult result = new CommonResult<>();
        try {
            switch (map.get("type")) {
                case "list": {
                    result = CommonResult.result(metaService.getMetaList(key));
                    break;
                }
                case "value": {
                    result = CommonResult.result(metaService.getMeta(key));
                    break;
                }
                default:
            }
        }catch (Exception e){
            return CommonResult.error(ResultCode.GET_META_FAIL);
        }
        return result;
    }
}
