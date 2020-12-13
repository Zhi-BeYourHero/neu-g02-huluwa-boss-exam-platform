package com.boss.bes.exam.api;

import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.vo.ExamMessageVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 考试人员接口
 * @create 2020-07-09 21:47
 * @since 1.0
 */
@RequestMapping("education/bes/v1/exam/user/")
public interface ExamUserApi {

    /**
     * 考试人员注册
     *
     * @param request 通用请求
     * @return 是否注册成功
     */
    @PostMapping("/register")
    String register(@RequestBody @Validated CommonRequest<ExamUserDTO> request);

    /**
     * 考试人员通过密码登录
     *
     * @param request 通用请求
     * @param id 发布考试id
     * @return 是否登录成功
     */
    @PostMapping("/login/pwd/{id}")
    ExamUserInfoDTO loginByPassword(@RequestBody @Validated CommonRequest<ExamUserPwdLoginDTO> request,
                           @PathVariable long id);

    /**
     * 考试人员通过手机验证码登录
     *
     * @param request 通用请求
     * @param id 发布考试id
     * @return 是否登录成功
     */
    @PostMapping("/login/code/{id}")
    ExamUserInfoDTO loginByCode(@RequestBody @Validated CommonRequest<ExamUserCodeLoginDTO> request,
                       @PathVariable long id);

    /**
     * 考试人员通过人脸识别登录
     *
     * @param request 通用请求
     * @param id 发布考试id
     * @return 是否登录成功
     */
    @PostMapping("/login/face/{id}")
    ExamUserInfoDTO loginByFace(@RequestBody @Validated CommonRequest<ExamUserFaceLoginDTO> request,
                       @PathVariable long id);

    /**
     * 考试人员获取验证码
     *
     * @param request 通用请求
     * @return 验证码
     */
    @PostMapping("/generate")
    String generateCode(@RequestBody CommonRequest<String> request);

    /**
     * 答卷提交
     *
     * @param request 通用请求
     * @return 是否提交成功
     */
    @PostMapping("/submit")
    boolean submitPaper(@RequestBody @Validated CommonRequest<ExamPaperAnswerDTO> request);

    /**
     * 考试信息校验
     *
     * @param request 通用请求
     * @return 考试信息
     */
    @PostMapping("/verify")
    ExamMessageVO verifyExamMessage(@RequestBody CommonRequest<Long> request);

    /**
     * 获取试卷信息
     *
     * @param pId 发布考试id
     * @param uId 考试人员id
     * @return 试卷信息
     */
    @PostMapping("/paper")
    ExamPaperDTO paperInformation(@RequestParam("pId") Long pId, @RequestParam("uId") Long uId);

    /**
     * 保存考试答案
     *
     * @param request 通用请求
     */
    @PostMapping("/save")
    void saveAnswerMessage(@RequestBody @Validated CommonRequest<ExamPaperAnswerDTO> request);

    /**
     * 上传图片
     *
     * @return 图片url
     */
    @PostMapping("/upload")
    String upload(@RequestParam("file") MultipartFile file, @RequestParam("name") String name);

    /**
     * 获取用户登录信息
     *
     * @param request 用请求
     * @return 用户信息
     */
    @PostMapping("info")
    ExamUserInfoDTO info(@RequestBody CommonRequest<Long> request);
}
