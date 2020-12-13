package com.boss.bes.system.dao.impl;

import com.boss.bes.system.BeanCopierUtil;
import com.boss.bes.system.dao.UserOnlineInfoDao;
import com.boss.bes.system.dto.UserOnlineInfoDto;
import com.boss.bes.system.entity.UserOnlineInfo;
import com.boss.bes.system.mapper.UserOnlineInfoMapper;
import com.boss.bes.system.query.UserOnlineInfoQuery;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 在线用户的Dao实现类
 * @create 2020-07-10 20:12
 * @since 1.0
 */
@Repository
public class UserOnlineInfoImpl implements UserOnlineInfoDao {

    @Resource
    private UserOnlineInfoMapper userOnlineInfoMapper;

    /**
     * 保存新增的在线用户信息
     *
     * @param userOnlineInfo 在线用户信息的对象
     * @return 影响行数
     */
    @Override
    public Integer save(UserOnlineInfo userOnlineInfo) {
        return userOnlineInfoMapper.insert(userOnlineInfo);
    }

    /**
     * 删除在线用户信息
     *
     * @param id 在线用户信息的用户id
     * @return 影响行数
     */
    @Override
    public Integer remove(Long id) {
        Example example = new Example(UserOnlineInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",id);
        return userOnlineInfoMapper.deleteByExample(example);
    }

    @Override
    public List<UserOnlineInfo> queryByCondition(UserOnlineInfoQuery userOnlineInfoQuery){
        Example example = new Example(UserOnlineInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",userOnlineInfoQuery.getId());
        criteria.andEqualTo("userId",userOnlineInfoQuery.getUserId());
        criteria.andEqualTo("code",userOnlineInfoQuery.getCode());
        criteria.andEqualTo("name",userOnlineInfoQuery.getName());
        criteria.andEqualTo("ip",userOnlineInfoQuery.getIp());
        criteria.andGreaterThanOrEqualTo("onlineTime",userOnlineInfoQuery.getOnlineTime());
        criteria.andLessThanOrEqualTo("offlineTime",userOnlineInfoQuery.getOfflineTime());
        criteria.andEqualTo("stopTime",userOnlineInfoQuery.getStopTime());
        criteria.andEqualTo("status",userOnlineInfoQuery.getStatus());
        return userOnlineInfoMapper.selectByExample(example);
    }

    /**
     * 获取所有在线用户信息
     *
     * @return 在线用户对象
     */
    @Override
    public List<UserOnlineInfo> listAll() {
        return userOnlineInfoMapper.selectAll();
    }

    @Override
    public UserOnlineInfoDto queryByPrimaryKey(Long userOnlineInfoId) {
        UserOnlineInfo userOnlineInfo = userOnlineInfoMapper.selectByPrimaryKey(userOnlineInfoId);
        UserOnlineInfoDto userOnlineInfoDto = new UserOnlineInfoDto();
        BeanCopierUtil.copy(userOnlineInfo, userOnlineInfoDto);
        return userOnlineInfoDto;
    }

    @Override
    public int updateByPrimaryKeySelective(UserOnlineInfoDto userOnlineInfoDto) {
        UserOnlineInfo userOnlineInfo = new UserOnlineInfo();
        BeanCopierUtil.copy(userOnlineInfoDto, userOnlineInfo);
        return userOnlineInfoMapper.updateByPrimaryKeySelective(userOnlineInfo);
    }
}