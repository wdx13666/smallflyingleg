package com.smallflyingleg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot 应用
 */

/**
 * 自动配置
 *  1、RabbitAutoConfiguration
 *  2、有自动配置了连接工厂ConnectionFactory；
 *  3、RabbitProperties 封装了 RabbitMQ的配置
 *  4、 RabbitTemplate ：给RabbitMQ发送和接受消息；
 *  5、 AmqpAdmin ： RabbitMQ系统管理功能组件;
 *  	AmqpAdmin：创建和删除 Queue，Exchange，Binding
 *  6、@EnableRabbit +  @RabbitListener 监听消息队列的内容
 *
 */
@EnableRabbit  //开启基于注解的RabbitMQ模式
@EnableAsync //开启基于注解的异步功能
@EnableScheduling //开启基于注解的定时任务
@SpringBootApplication
@MapperScan("com.smallflyingleg.mapper")
public class SmallflyinglegApplication {

    public static void main(String[] args) {
        //spring应用启动
        SpringApplication.run(SmallflyinglegApplication.class, args);
    }

}
