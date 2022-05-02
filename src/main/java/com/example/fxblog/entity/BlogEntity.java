package com.example.fxblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 博客文章实体类
 *
 * @Author 王志康
 * @Date 2022/4/25 19:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fx_blog")
public class BlogEntity {
    @TableId(type = IdType.AUTO)
    private Integer blogUuid;

    @TableField("blog_text")
    private String blogText;

    @TableField("blog_type")
    private String blogType;

    @TableField("blog_title")
    private String blogTitle;

    @TableField("blog_image")
    private String blogImage;

    @TableField("blog_time")
    private Date blogTime;

    @TableField("blog_count")
    private Integer blogCount;

    public BlogEntity(String blogText, String blogTitle) {
        this.blogText = blogText;
        this.blogTitle = blogTitle;
        this.blogTime = new Date();
    }
}
