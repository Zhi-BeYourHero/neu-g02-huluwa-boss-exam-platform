package boss.xtrain.service;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.domain.CapchaEntity;
import boss.xtrain.exception.CaptchaException;

import java.io.IOException;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 验证码处理
 * @create 2020-07-14
 * @since
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    CommonResponse<CapchaEntity> createCapcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    void checkCapcha(String key, String value) throws CaptchaException;
}
