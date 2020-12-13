package boss.xtrain.util.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description Message的配置类
 * @create 2020-07-07 15:13
 * @since 1.0
 */

@ConfigurationProperties("message")
@Component
@Data
@NoArgsConstructor
public class MessageProperties {

    private String regionId = "cn-hangzhou";

    private String accessKeyId = "LTAI4GL93fRheB5W41NockfR";

    private String accessKeySecret = "uqwcmdcb46tfsuJPR2aPRjabXTw5kd";

    private String sysDomain = "dysmsapi.aliyuncs.com";

    private String sysVersion = "2017-05-25";

    private String sysAction = "SendSms";

    private String signName = "博思在线考试系统G02";

    private String templateCode = "SMS_195195546";
}