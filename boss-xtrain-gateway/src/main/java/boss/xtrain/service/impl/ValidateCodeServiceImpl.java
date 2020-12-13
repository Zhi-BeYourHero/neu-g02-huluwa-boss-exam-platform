package boss.xtrain.service.impl;

import boss.xtrain.cache.redis.service.RedisService;
import boss.xtrain.core.constant.Constants;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.domain.CapchaEntity;
import boss.xtrain.exception.CaptchaException;
import boss.xtrain.service.ValidateCodeService;
import boss.xtrain.util.id.IdUtils;
import boss.xtrain.util.sign.Base64;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import com.google.code.kaptcha.Producer;
import org.springframework.util.StringUtils;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 验证码实现处理
 * @create 2020-07-14
 * @since
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisService redisService;

    @Override
    public CommonResponse<CapchaEntity> createCapcha() throws IOException, CaptchaException {
        // 生成验证码
        String capText = producer.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String verifyCode = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = producer.createImage(capStr);

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        //将验证码存到缓存中
        redisService.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return CommonResponseUtil.error(e.getMessage());
        }
        CapchaEntity capchaEntity = new CapchaEntity();
        capchaEntity.setUuid(uuid);
        capchaEntity.setImg(Base64.encode(os.toByteArray()));
        return CommonResponseUtil.success(capchaEntity);
    }

    /**
     * 校验验证码
     */
    @Override
    public void checkCapcha(String code, String uuid) throws CaptchaException
    {
        if (StringUtils.isEmpty(code))
        {
            throw new CaptchaException("验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid))
        {
            throw new CaptchaException("验证码已失效");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = (String) redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);

        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException("验证码错误");
        }
    }
}
