package com.boss.bes.exam.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.facebody.model.v20191230.CompareFaceRequest;
import com.aliyuncs.facebody.model.v20191230.CompareFaceResponse;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 人脸识别工具类
 * @create 2020-07-09 15:36
 * @since 1.0
 */
@Slf4j
public final class VerifaceUtil {
    /**
     * 区域ID
     */
    public static final String REGION_ID = "cn-shanghai";

    /**
     * 访问键ID
     */
    public static final String ACCESS_KEY_ID = "LTAI4G2og1Kar73u9KG56zey";

    /**
     * 访问秘钥
     */
    public static final String ACCESS_SECRET = "JrBqy6VrwLza2CykKcehtLyOBBYN0B";

    /**
     * 识别度
     */
    public static final float CONFIDENCE = 85;
    private VerifaceUtil(){}

    /**
     * 人脸识别接口
     *  @param imageUrlA 人脸图片A
     * @param imageUrlB 人脸图片B
     * @return 是否识别成功
     */
    public static boolean veriface(String imageUrlA, String imageUrlB) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CompareFaceRequest request = new CompareFaceRequest();
        request.setSysRegionId(REGION_ID);
        request.setImageURLA(imageUrlA);
        request.setImageURLB(imageUrlB);
        try {
            CompareFaceResponse response = client.getAcsResponse(request);
            log.info("识别结果：{}", response.getData());
            return response.getData().getConfidence() > CONFIDENCE;
        } catch (ClientException e) {
            throw new ExamException(ExamExceptionCode.VERIFACE_ERROR);
        } finally {
            client.shutdown();
        }
    }
}
