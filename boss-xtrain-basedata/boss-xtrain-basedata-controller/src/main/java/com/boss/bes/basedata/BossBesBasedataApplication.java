package com.boss.bes.basedata;

import boss.xtrain.config.swagger.EnableCustomSwagger2;
import boss.xtrain.security.annotation.EnableCustomConfig;
import boss.xtrain.security.annotation.EnableLwzFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


@EnableCustomSwagger2
@EnableCustomConfig
@SpringCloudApplication
@EnableLwzFeignClients
public class BossBesBasedataApplication {
    public static void main(String[] args) {
        SpringApplication.run(BossBesBasedataApplication.class, args);
    }
}
