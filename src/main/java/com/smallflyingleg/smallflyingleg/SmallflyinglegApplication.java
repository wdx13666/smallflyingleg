package com.smallflyingleg.smallflyingleg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot 应用
 */
@SpringBootApplication
@MapperScan("com.smallflyingleg.smallflyingleg.mapper")
public class SmallflyinglegApplication {

    public static void main(String[] args) {
        //spring应用启动
        SpringApplication.run(SmallflyinglegApplication.class, args);
    }

}
