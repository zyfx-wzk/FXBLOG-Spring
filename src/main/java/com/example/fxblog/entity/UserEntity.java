package com.example.fxblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 王志康
 * @Date 2022/2/25 23:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fx_user")
public class UserEntity {
    @TableId
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_password")
    private String userPassword;
}
