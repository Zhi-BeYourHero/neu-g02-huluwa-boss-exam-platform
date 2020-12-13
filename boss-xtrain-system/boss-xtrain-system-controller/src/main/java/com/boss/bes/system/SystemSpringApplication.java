package com.boss.bes.system;

import org.springframework.boot.SpringApplication;
import boss.xtrain.security.annotation.EnableCustomConfig;
import boss.xtrain.security.annotation.EnableLwzFeignClients;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 控制器的启动类
 * @create 2020-07-07 21:37
 * @since 1.0
 */
@EnableCustomConfig
@SpringCloudApplication
@EnableLwzFeignClients
public class SystemSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemSpringApplication.class, args);
    }
}