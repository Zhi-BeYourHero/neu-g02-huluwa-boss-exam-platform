package boss.xtrain.util;

import boss.xtrain.util.cdn.CdnProperties;
import boss.xtrain.util.message.MessageProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description Spring Bean的配置类
 * @create 2020-07-07 15:42
 * @since 1.0
 */

@Configuration
public class BeanConfig {

    @Bean
    public MessageProperties messageProperties() {
        return new MessageProperties();
    }

    @Bean
    public CdnProperties cdnProperties() {
        return new CdnProperties();
    }


}