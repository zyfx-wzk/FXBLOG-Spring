package com.example.fxblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 王志康
 * @Date 2022/4/3 21:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fx_meta")
public class MetaEntity {
    @TableField("meta_key")
    private String metaKey;

    @TableField("meta_value")
    private String metaValue;
}