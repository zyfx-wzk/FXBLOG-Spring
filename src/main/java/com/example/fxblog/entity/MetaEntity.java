package com.example.fxblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 元数据实体类
 * @Author 王志康
 * @Date 2022/4/3 21:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fx_meta")
public class MetaEntity {
    @TableId(type = IdType.AUTO)
    private Integer metaUuid;

    @TableField("meta_key")
    private String metaKey;

    @TableField("meta_value")
    private String metaValue;

    public MetaEntity(String metaKey, String metaValue) {
        this.metaKey = metaKey;
        this.metaValue = metaValue;
    }
}
