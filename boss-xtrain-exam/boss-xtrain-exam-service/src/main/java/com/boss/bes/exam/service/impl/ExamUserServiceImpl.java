package com.boss.bes.exam.service.impl;

import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.exam.dao.ExamUserDao;
import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.po.ExamUser;
import com.boss.bes.exam.service.ExamUserService;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import static boss.xtrain.util.convert.BeanUtil.*;
/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 13:32
 * @since 1.0
 */
@Service
public class ExamUserServiceImpl implements ExamUserService {

    @Resource
    private ExamUserDao examUserDao;

    /**
     * 考试人员注册
     *
     * @param examUserDTO 数据传输对象
     * @return 是否注册成功
     */
    @Override
    @Transactional
    public boolean register(ExamUserDTO examUserDTO) {
        try {
            String id = SnowFlakeUtil.getId();
            examUserDTO.setId(Long.valueOf(id));
            ExamUser user = copyProperties(examUserDTO, ExamUser.class);
            user.setCreatedBy(Long.valueOf(id));
            user.setCreatedTime(new Date());
            return examUserDao.insert(user) > 0;
        } catch (Exception e) {
            throw new ExamException(ExamExceptionCode.REGISTER_ERROR);
        }
    }

    /**
     * 考试人员通过密码登录
     *
     * @param userPwdLoginDTO 数据传输对象
     * @return 是否验证成功
     */
    @Override
    public ExamUserInfoDTO loginByPassword(ExamUserPwdLoginDTO userPwdLoginDTO) {
        try {
            ExamUser user = copyProperties(userPwdLoginDTO, ExamUser.class);
            List<ExamUser> users = examUserDao.query(user);
            ExamUserInfoDTO userInfo = new ExamUserInfoDTO();
            if (users.size() == 1) {
                copyProperties(users.get(0), userInfo);
            }
            userInfo.setFlag(users.size() == 1);
            return userInfo;
        } catch (Exception e) {
            throw new ExamException(ExamExceptionCode.PASSWORD_ERROR);
        }
    }

    /**
     * 考试人员通过验证码登录
     *
     * @param userCodeLoginDTO 数据传输对象
     * @return 是否验证成功
     */
    @Override
    public ExamUserInfoDTO loginByCode(ExamUserCodeLoginDTO userCodeLoginDTO) {
        try {
            ExamUser user = copyProperties(userCodeLoginDTO, ExamUser.class);
            List<ExamUser> users = examUserDao.query(user);
            ExamUserInfoDTO userInfo = new ExamUserInfoDTO();
            if (users.size() == 1) {
                copyProperties(users.get(0), userInfo);
            }
            userInfo.setFlag(users.size() == 1);
            return userInfo;
        } catch (Exception e) {
            throw new ExamException(ExamExceptionCode.CODE_ERROR);
        }
    }

    /**
     * 考试人员通过人脸登录
     *
     * @param userFaceLoginDTO 数据传输对象
     * @return 是否验证成功
     */
    @Override
    public ExamUserInfoDTO loginByFace(ExamUserFaceLoginDTO userFaceLoginDTO) {
        try {
            ExamUser user = copyProperties(userFaceLoginDTO, ExamUser.class);
            List<ExamUser> users = examUserDao.query(user);
            ExamUserInfoDTO userInfo = new ExamUserInfoDTO();
            if (users.size() == 1) {
                copyProperties(users.get(0), userInfo);
            }
            userInfo.setFlag(users.size() == 1);
            return userInfo;
        } catch (ExamException e) {
            throw new ExamException(ExamExceptionCode.FACE_ERROR);
        }
    }

    /**
     * 获取用户信息
     *
     * @param id id
     * @return 用户信息
     */
    @Override
    public ExamUserInfoDTO info(long id) {
        ExamUser examUser = examUserDao.selectById(id);
        return copyProperties(examUser, ExamUserInfoDTO.class);
    }
}
