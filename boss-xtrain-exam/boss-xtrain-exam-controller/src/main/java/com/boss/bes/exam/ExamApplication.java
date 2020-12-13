package com.boss.bes.exam;

import boss.xtrain.config.swagger.EnableCustomSwagger2;
import boss.xtrain.log.exception.resolver.EnableGlobalExceptionHandler;
import boss.xtrain.security.annotation.EnableCustomConfig;
import boss.xtrain.security.annotation.EnableLwzFeignClients;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 14:14
 * @since 1.0
 */
@EnableRabbit
@EnableCustomSwagger2
@CrossOrigin
@EnableGlobalExceptionHandler
@EnableCustomConfig
@SpringCloudApplication
@EnableLwzFeignClients
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }
}
