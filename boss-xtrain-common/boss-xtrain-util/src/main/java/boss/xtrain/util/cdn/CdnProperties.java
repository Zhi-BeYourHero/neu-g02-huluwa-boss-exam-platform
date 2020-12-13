package boss.xtrain.util.cdn;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description CDN的配置类
 * @create 2020-07-07 15:03
 * @since 1.0
 */
@ConfigurationProperties("oss")
@Component
@Data
@NoArgsConstructor
public class CdnProperties {

    private String accessKeyId = "LTAI4GL93fRheB5W41NockfR";

    private String accessKeySecret = "uqwcmdcb46tfsuJPR2aPRjabXTw5kd";

    private String bucketName = "bes-g02";

    private String endpoint = "http://oss-cn-shanghai.aliyuncs.com";

}