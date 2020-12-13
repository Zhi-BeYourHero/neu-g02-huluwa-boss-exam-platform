package com.boss.bes.system.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import boss.xtrain.security.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 在线用户的Mapper类 tkMyBatis生成
 * @create 2020-07-07
 * @since 1.0
 */

@Mapper
public interface UserMapper extends CommonMapper<User> {

    SysUser selectSysUserByUserName(String username);

    List<User> queryWithRole(@Param("userQuery") UserQuery userQuery);
}