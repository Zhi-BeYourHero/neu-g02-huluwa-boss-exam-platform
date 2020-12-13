package com.boss.bes.exam.controller;

import boss.xtrain.cache.redis.service.RedisService;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.cdn.CdnUtil;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.file.FileUtil;
import boss.xtrain.util.message.MessageUtil;
import com.aliyuncs.exceptions.ClientException;
import com.boss.bes.exam.api.ExamUserApi;
import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.vo.ExamMessageVO;
import com.boss.bes.exam.service.AnswerPaperService;
import com.boss.bes.exam.service.ExamPublishRecordService;
import com.boss.bes.exam.service.ExamUserService;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import com.boss.bes.exam.util.VerifaceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 14:12
 * @since 1.0
 */
@Api(value = "手机答卷模块")
@ApiLog(msg = "手机答卷模块日志")
@CrossOrigin
@RestController
public class ExamUserController implements ExamUserApi {

    @Resource
    private ExamUserService examUserService;

    @Resource
    private ExamPublishRecordService examPublishRecordService;

    @Resource
    private AnswerPaperService answerPaperService;

    @Resource
    private RedisService redisService;

    /**
     * 考试人员注册
     *
     * @param request 通用请求
     * @return 是否注册成功
     */
    @Override
    @ApiOperation("考试人员注册")
    public String register(CommonRequest<ExamUserDTO> request) {
        boolean register = examUserService.register(request.getBody());
        return register ? "注册成功" : "注册失败";
    }

    /**
     * 考试人员通过密码登录
     *
     * @param request 通用请求
     * @param id      发布考试id
     * @return 是否登录成功
     */
    @Override
    @ApiOperation("考试人员通过密码登录")
    public ExamUserInfoDTO loginByPassword(CommonRequest<ExamUserPwdLoginDTO> request, long id) {
        ExamUserInfoDTO userInfo = examUserService.loginByPassword(request.getBody());
        userInfo.setPId(id);
        return userInfo;
    }

    /**
     * 考试人员通过手机验证码登录
     *
     * @param request 通用请求
     * @param id      发布考试id
     * @return 是否登录成功
     */
    @Override
    @ApiOperation("考试人员通过验证码登录")
    public ExamUserInfoDTO loginByCode(CommonRequest<ExamUserCodeLoginDTO> request, long id) {
        ExamUserCodeLoginDTO body = request.getBody();
        ExamUserInfoDTO userInfo = examUserService.loginByCode(body);
        userInfo.setPId(id);
        Object code = redisService.getCacheObject("code::" + body.getMobile());
        if (Boolean.TRUE.equals(userInfo.getFlag()) && code.equals(body.getCode())) {
            return userInfo;
        }
        userInfo.setFlag(false);
        return userInfo;
    }

    /**
     * 考试人员通过人脸识别登录
     *
     * @param request 通用请求
     * @param id      发布考试id
     * @return 是否登录成功
     */
    @Override
    @ApiOperation("考试人员通过人脸识别登录")
    public ExamUserInfoDTO loginByFace(CommonRequest<ExamUserFaceLoginDTO> request, long id) {
        ExamUserFaceLoginDTO body = request.getBody();
        String faceUrl = body.getPhotoUrl();
        String faceName = body.getPhotoName();
        List<String> urls = CdnUtil.getBatchUrl();
        ExamUserInfoDTO userInfo = new ExamUserInfoDTO();
        for (String url : urls) {
            if (!url.equals(faceUrl) && VerifaceUtil.veriface(faceUrl, url)) {
                body.setPhotoUrl(url.split("\\?")[0]);
                userInfo = examUserService.loginByFace(body);
                break;
            }
        }
        CdnUtil.delete(faceName);
        userInfo.setPId(id);
        return userInfo;
    }

    /**
     * 考试人员获取验证码
     *
     * @param request 通用请求
     * @return 验证码
     */
    @Override
    @ApiOperation("考试人员获取验证码")
    @Cacheable(cacheNames = "code", key = "#request.body")
    public String generateCode(CommonRequest<String> request) {
        String code;
        try {
            code = MessageUtil.sendMessage(request.getBody());
        } catch (ClientException e) {
            throw new ExamException(ExamExceptionCode.GENERATE_CODE_ERROR);
        }
        return code;
    }

    /**
     * 答卷提交
     *
     * @param request 通用请求
     * @return 是否提交成功
     */
    @Override
    @ApiOperation("考试人员提交试卷")
    public boolean submitPaper(CommonRequest<ExamPaperAnswerDTO> request) {
        ExamPaperAnswerDTO body = request.getBody();
        // 缓存的键
        String key = request.getBody().getRid() + "-" + request.getBody().getId();
        redisService.deleteObject(key);
        return answerPaperService.submitPaper(body);
    }

    /**
     * 考试信息校验
     *
     * @param request 通用请求
     * @return 考试信息
     */
    @Override
    @ApiOperation("考试信息校验")
    public ExamMessageVO verifyExamMessage(CommonRequest<Long> request) {
        ExamPublishRecordResultDTO resultDTO = examPublishRecordService.getById(request.getBody());
        return BeanUtil.copyProperties(resultDTO, ExamMessageVO.class);
    }

    /**
     * 获取试卷信息
     *
     * @param pId 发布考试id
     * @param uId 考试人员id
     * @return 试卷信息
     */
    @Override
    @ApiOperation("获取试卷信息")
    public ExamPaperDTO paperInformation(Long pId, Long uId) {
        ExamInfoDTO infoDTO = examPublishRecordService.getPaperIdById(pId);
        final String paperKey = "paper-" + pId + " " + infoDTO.getTPId();
        // 试卷信息
        ExamPaperDTO paperDTO = (ExamPaperDTO)redisService.getCacheObject(paperKey);
        if (paperDTO == null) {
            paperDTO = BeanUtil.copyProperties(infoDTO, ExamPaperDTO.class);
            paperDTO.setSubjects(answerPaperService.paperInfo(infoDTO.getTPId()));
            redisService.setCacheObject(paperKey, paperDTO);
        }
        final String answerKey = pId + "-" + uId;
        // 是否已经答卷
        ExamPaperAnswerDTO paperAnswerDTO = (ExamPaperAnswerDTO)redisService.getCacheObject(answerKey);
        if (paperAnswerDTO != null) {
            for (ExamSubjectDTO subject : paperAnswerDTO.getSubjects()) {
                if (subject.getMyAnswer() != null) {
                    paperDTO.getSubjects().forEach(dto -> {
                        if (dto.getId().equals(subject.getId())) {
                            dto.setMyAnswer(subject.getMyAnswer());
                        }
                    });
                }
            }
        }
        return paperDTO;
    }

    /**
     * 临时保存考试答案
     *
     * @param request 通用请求
     */
    @Override
    @ApiOperation("保存考试答案")
    public void saveAnswerMessage(CommonRequest<ExamPaperAnswerDTO> request) {
        // 缓存的键
        String key = request.getBody().getRid() + "-" + request.getBody().getId();
        // 考试人员的答案信息
        redisService.setCacheObject(key, request.getBody());
    }

    /**
     * 上传图片
     *
     * @return 图片url
     */
    @Override
    public String upload(MultipartFile file, String name) {
        String filename = file.getOriginalFilename();
        String filepath = "/tmp";
        File img = new File(filepath + filename);
        try {
            file.transferTo(img);
            CdnUtil.upload(img, name);
            FileUtil.deleteFile(filepath + filename);
        } catch (Exception e) {
            throw new ExamException(ExamExceptionCode.IMG_UPLOAD_ERROR);
        }
        return CdnUtil.getUrl(name);
    }

    /**
     * 获取用户登录信息
     *
     * @param request 用请求
     * @return 用户信息
     */
    @Override
    public ExamUserInfoDTO info(CommonRequest<Long> request) {
        return examUserService.info(request.getBody());
    }
}
