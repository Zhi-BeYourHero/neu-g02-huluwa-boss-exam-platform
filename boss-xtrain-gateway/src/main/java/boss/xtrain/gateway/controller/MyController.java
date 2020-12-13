package boss.xtrain.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.exception.GatewayErrorType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-06
 * @since
 */
@RestController
@RefreshScope
public class MyController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo(){
        return configInfo;
    }
    
    @RequestMapping("/fallback")
    public CommonResponse getFallback(){
        return CommonResponseUtil.error(GatewayErrorType.GATEWAY_DEMOTION_ERROR.getCode(),GatewayErrorType.GATEWAY_DEMOTION_ERROR.getMessage());
    }

}