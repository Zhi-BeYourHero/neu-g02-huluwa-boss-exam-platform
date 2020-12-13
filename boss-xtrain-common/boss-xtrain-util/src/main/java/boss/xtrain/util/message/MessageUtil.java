package boss.xtrain.util.message;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 发送短信的工具类
 * @create 2020-07-03 17:25
 * @since 1.0
 */

@Slf4j
@Component
public class MessageUtil {

    private static MessageProperties messageProperties;

    private static Random random = new Random();

    private static final String CODE_STR = "0123456789";

    @Autowired
    public void setMessageProperties(MessageProperties autowiredMessageProperties) {
        messageProperties = autowiredMessageProperties;
    }

    /**
     * 向手机号码为telephone的用户发送验证码
     * @param telephone 用户的手机号
     * @return 响应字符串
     */
    public static String  sendMessage(String telephone) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(messageProperties.getRegionId(),
                messageProperties.getAccessKeyId(), messageProperties.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(messageProperties.getSysDomain());
        request.setSysVersion(messageProperties.getSysVersion());
        request.setSysAction(messageProperties.getSysAction());
        request.putQueryParameter("RegionId", messageProperties.getRegionId());
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", messageProperties.getSignName());
        request.putQueryParameter("TemplateCode", messageProperties.getTemplateCode());
        Map<String, String> map = generateCode();
        request.putQueryParameter("TemplateParam", toJSONString(map));
        client.getCommonResponse(request);
        return map.get("code");
    }

    /**
     * 生成六位验证码
     * @return 验证码
     */
    private static Map<String, String> generateCode() {
        Map<String, String> map = new HashMap<>(1);
        int codeLength = 6;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i< codeLength; i++){
            stringBuilder.append(CODE_STR.charAt(random.nextInt(10)));
        }
        String code = stringBuilder.toString();
        map.put("code", code);
        return map;
    }
}