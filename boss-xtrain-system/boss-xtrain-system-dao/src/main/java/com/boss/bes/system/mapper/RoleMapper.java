package com.boss.bes.system.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import boss.xtrain.security.domain.SysRole;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的Mapper类 tkMyBatis生成
 * @create 2020-07-07
 * @since 1.0
 */

@Mapper
public interface RoleMapper extends CommonMapper<Role> {
    List<SysRole> selectRolePermissionByUserId(Long userId);
}