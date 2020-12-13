package com.boss.bes.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 开启WebSocket支持
 * @create 2020-07-20
 * @since
 */
@Configuration
public class WebSocketConfig {
    /**
     * 使用boot内置tomcat时需要注入此bean
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
