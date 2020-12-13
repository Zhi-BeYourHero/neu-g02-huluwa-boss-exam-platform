package boss.xtrain;

import boss.xtrain.security.annotation.EnableLwzFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description Auth主启动类
 * @create 2020-07-06
 * @since
 */
@SpringCloudApplication
@EnableLwzFeignClients
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}