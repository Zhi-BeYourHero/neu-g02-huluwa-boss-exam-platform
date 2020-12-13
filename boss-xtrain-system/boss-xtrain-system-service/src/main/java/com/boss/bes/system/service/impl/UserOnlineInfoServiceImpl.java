package com.boss.bes.system.service.impl;

import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.BeanCopierUtil;
import com.boss.bes.system.SystemException;
import com.boss.bes.system.dao.UserDao;
import com.boss.bes.system.dao.UserOnlineInfoDao;
import com.boss.bes.system.dto.UserDto;
import com.boss.bes.system.dto.UserOnlineInfoDto;
import com.boss.bes.system.entity.UserOnlineInfo;
import com.boss.bes.system.query.UserOnlineInfoQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.boss.bes.system.service.UserOnlineInfoService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 在线用户的服务实现类
 * @create 2020-07-07
 * @since 1.0
 */

@Service
@Slf4j
public class UserOnlineInfoServiceImpl implements UserOnlineInfoService{

    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);

    @Resource
    private UserOnlineInfoDao userOnlineInfoDao;

    @Resource
    private UserDao userDao;

    private static final String MODULE_NAME = "ONLINE_USER";

    /**
     * 按条件进行查询
     *
     * @param userOnlineInfoQuery 封装的查询条件
     * @return 查询的返回值
     */
    @Override
    public List<UserOnlineInfo> query(UserOnlineInfoQuery userOnlineInfoQuery) {
        try{
            return userOnlineInfoDao.queryByCondition(userOnlineInfoQuery);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210401","ONLINE_USER_INFO_QUERY_FAILURE");
        }
    }

    /**
     * 获取所有在线用户的信息
     *
     * @return 所有在线用户信息
     */
    @Override
    public List<UserOnlineInfo> listAll() {
        try{
            return userOnlineInfoDao.listAll();
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210401","ONLINE_USER_INFO_QUERY_FAILURE");
        }
    }

    /**
     * 按照用户Id删除在线用户记录
     *
     * @param userId 用户Id
     * @return 影响的行数
     */
    @Override
    public Integer delete(Long userId) {
        try{
            return this.userOnlineInfoDao.remove(userId);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210405","ONLINE_USER_INFO_DELETE_FAILURE");
        }
    }

    /**
     * 当用户下线的时候 将其信息保存在数据库中
     * @return 影响的行数
     */
    @Override
    public Integer writeNewLogs(UserOnlineInfo userOnlineInfo){
        try{
            return userOnlineInfoDao.save(userOnlineInfo);
        }catch(Exception e){
            throw new SystemException(MODULE_NAME,"210404","ONLINE_USER_INFO_SAVE_FAILURE");
        }
    }

    /**
     * 退出
     * @param userOnlineInfoDto
     */
    @Override
    public void logout(UserOnlineInfoDto userOnlineInfoDto) {
        userOnlineInfoDto = userOnlineInfoDao.queryByPrimaryKey(userOnlineInfoDto.getId());
        userOnlineInfoDto.setOfflineTime(new Date(System.currentTimeMillis()));
        long stopTime = userOnlineInfoDto.getOfflineTime().getTime() - userOnlineInfoDto.getOnlineTime().getTime();
        userOnlineInfoDto.setStopTime((int)stopTime);
        userOnlineInfoDto.setStatus(1);
        userOnlineInfoDao.updateByPrimaryKeySelective(userOnlineInfoDto);
    }

    /**
     * 登录
     * @param userOnlineInfoDto
     * @return
     */
    @Override
    public UserOnlineInfoDto login(UserOnlineInfoDto userOnlineInfoDto) {

        UserDto userDto = userDao.queryById(userOnlineInfoDto.getUserId());
        userOnlineInfoDto.setCode(userDto.getCode());
        userOnlineInfoDto.setName(userDto.getName());

        userOnlineInfoDto.setId(snowflakeWorker.nextId());
        userOnlineInfoDto.setOnlineTime(new Date(System.currentTimeMillis()));
        userOnlineInfoDto.setStatus(0);
        //将登录信息保存到数据库
        UserOnlineInfo userOnlineInfo = new UserOnlineInfo();
        BeanCopierUtil.copy(userOnlineInfoDto, userOnlineInfo);
        //获取请求信息,设置ip
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String remoteAddr = request.getRemoteAddr();
        log.warn("在线用户ip:{}",remoteAddr);
        userOnlineInfo.setIp(remoteAddr);
        userOnlineInfoDao.save(userOnlineInfo);
        return userOnlineInfoDto;
    }
}