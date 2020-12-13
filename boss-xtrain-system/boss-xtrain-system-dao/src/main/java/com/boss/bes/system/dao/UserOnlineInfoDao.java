package com.boss.bes.system.dao;

import com.boss.bes.system.dto.UserOnlineInfoDto;
import com.boss.bes.system.entity.UserOnlineInfo;
import com.boss.bes.system.query.UserOnlineInfoQuery;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 定义非通用的Mapper方法
 * @create 2020-07-10 20:07
 * @since 1.0
 */

public interface UserOnlineInfoDao {

    // 增删改查方法虽然Controller只有查的接口 但是其他方法也要提供
    // 用户数据库的维护

    /**
     * 保存新增的在线用户信息
     * @param userOnlineInfo 在线用户信息的对象
     * @return 影响行数
     */
    Integer save(UserOnlineInfo userOnlineInfo);

    /**
     * 删除在线用户信息
     * @param userId 在线用户信息的用户Id
     * @return 影响行数
     */
    Integer remove(Long userId);

    /**
     * 查询在线用户信息
     * @param userOnlineInfoQuery 在线用户信息的Query对象
     * @return 搜索结果
     */
    List<UserOnlineInfo> queryByCondition(UserOnlineInfoQuery userOnlineInfoQuery);

    /**
     * 获取所有在线用户信息
     * @return 在线用户对象
     */
    List<UserOnlineInfo> listAll();

    /**
     * 根据主键查询
     * @param userOnlineInfoId
     * @return
     */
    UserOnlineInfoDto queryByPrimaryKey(Long userOnlineInfoId);

    /**
     * 选择性更新
     * @param userOnlineInfoDto
     */
    int updateByPrimaryKeySelective(UserOnlineInfoDto userOnlineInfoDto);

}
