package com.example.fxblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author WZK
 */
@SpringBootApplication
@MapperScan("com.example.fxblog.mapper")
public class FxBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxBlogApplication.class, args);
    }

}
