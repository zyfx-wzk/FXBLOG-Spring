package com.example.fxblog.service.serviceImpl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fxblog.entity.BlogEntity;
import com.example.fxblog.mapper.BlogMapper;
import com.example.fxblog.service.BlogService;
import com.example.fxblog.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 王志康
 * @Date 2022/4/25 22:33
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Override
    public void addBlog(BlogEntity blogEntity) {
        blogMapper.insert(blogEntity);
    }

    @Override
    public JSONObject getBlogText(Integer uuid) {
        BlogEntity blogEntity = blogMapper.selectById(uuid);
        blogEntity.setBlogCount(blogEntity.getBlogCount() + 1);
        blogMapper.updateById(blogEntity);
        return JSONUtil.createObj()
                .putOnce("title", blogEntity.getBlogTitle()).putOnce("text", blogEntity.getBlogText())
                .putOnce("time", blogEntity.getBlogTime()).putOnce("count", blogEntity.getBlogCount())
                .putOnce("image", blogEntity.getBlogImage());
    }

    @Override
    public List<JSONObject> getBlogList(Integer page, Integer size) {
        //分页插件
        IPage<BlogEntity> iPage = new Page<>();
        iPage.setCurrent(page).setSize(size);
        QueryWrapper<BlogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("blog_time");
        List<BlogEntity> list = blogMapper.selectPage(iPage, queryWrapper).getRecords();
        List<JSONObject> result = new ArrayList<>();
        log.info(String.valueOf(list.size()));
        for (BlogEntity blog : list) {
            //拼接文章简介
            String image = blog.getBlogImage() == null ? imageUtil.getImage() : blog.getBlogImage();
            String[] str = blog.getBlogText().split(".\r\n");
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < str.length && i < 3; i++) {
                text.append(str[i]).append("\r\n");
            }
            result.add(JSONUtil.createObj()
                    .putOnce("uuid", blog.getBlogUuid()).putOnce("title", blog.getBlogTitle())
                    .putOnce("text", text.toString())
                    .putOnce("time", blog.getBlogTime()).putOnce("type", blog.getBlogType())
                    .putOnce("count", blog.getBlogCount()).putOnce("image", image));
        }
        return result;
    }
}
