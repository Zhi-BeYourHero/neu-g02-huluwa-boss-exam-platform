package com.boss.bes.exam.service;


import com.boss.bes.exam.model.dto.*;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 13:32
 * @since 1.0
 */
public interface ExamUserService{

    /**
     * 考试人员注册
     *
     * @param examUserDTO 数据传输对象
     * @return 是否注册成功
     */
    boolean register(ExamUserDTO examUserDTO);

    /**
     * 考试人员通过密码登录
     *
     * @param userPwdLoginDTO 数据传输对象
     * @return 是否验证成功
     */
    ExamUserInfoDTO loginByPassword(ExamUserPwdLoginDTO userPwdLoginDTO);

    /**
     * 考试人员通过验证码登录
     *
     * @param userCodeLoginDTO 数据传输对象
     * @return 是否验证成功
     */
    ExamUserInfoDTO loginByCode(ExamUserCodeLoginDTO userCodeLoginDTO);

    /**
     * 考试人员通过人脸登录
     *
     * @param userFaceLoginDTO 数据传输对象
     * @return 是否验证成功
     */
    ExamUserInfoDTO loginByFace(ExamUserFaceLoginDTO userFaceLoginDTO);

    /**
     * 获取用户信息
     *
     * @param id id
     * @return 用户信息
     */
    ExamUserInfoDTO info(long id);

}
