package com.boss.bes.paper;

import boss.xtrain.security.annotation.EnableCustomConfig;
import boss.xtrain.security.annotation.EnableLwzFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.controller
 * @Description:
 * @Date: 2020/7/9 11:48
 * @since: 1.0
 */
@EnableCustomConfig
@SpringCloudApplication
@EnableLwzFeignClients
@EnableSwagger2
@CrossOrigin
public class PaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperApplication.class, args);
    }
}