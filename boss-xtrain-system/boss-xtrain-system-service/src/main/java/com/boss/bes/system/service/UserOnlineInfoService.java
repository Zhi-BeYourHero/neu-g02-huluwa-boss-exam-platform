package com.boss.bes.system.service;

import com.boss.bes.system.dto.UserOnlineInfoDto;
import com.boss.bes.system.entity.UserOnlineInfo;
import com.boss.bes.system.query.UserOnlineInfoQuery;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 在线用户的服务接口
 * @create 2020-07-07
 * @since 1.0
 */

public interface UserOnlineInfoService{

    /**
     * 按条件进行查询
     * @param userOnlineInfoQuery 封装的查询条件
     * @return 查询的返回值
     */
    List<UserOnlineInfo> query(UserOnlineInfoQuery userOnlineInfoQuery);

    /**
     * 获取所有在线用户的信息
     * @return 所有在线用户信息
     */
    List<UserOnlineInfo> listAll();

    /**
     * 按照用户Id删除在线用户记录
     * @param userId 用户Id
     * @return 影响的行数
     */
    Integer delete(Long userId);

    /**
     * 用户下线的时候将记录写进数据库
     * @param userOnlineInfo 在线用户对象
     * @return 影响的行数
     */
    Integer writeNewLogs(UserOnlineInfo userOnlineInfo);

    /**
     * 退出操作
     * @param userOnlineInfoDto
     */
    void logout(UserOnlineInfoDto userOnlineInfoDto);

    /**
     * 用户登录时做的操作
     * @param userOnlineInfoDto
     */
    UserOnlineInfoDto login(UserOnlineInfoDto userOnlineInfoDto);
}
