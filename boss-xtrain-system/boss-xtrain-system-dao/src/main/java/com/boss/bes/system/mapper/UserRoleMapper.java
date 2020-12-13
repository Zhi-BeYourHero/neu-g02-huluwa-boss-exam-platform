package com.boss.bes.system.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.system.entity.RoleResource;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 多表查询使用
 * @create 2020-07-11 15:38
 * @since 1.0
 */

@Mapper
public interface UserRoleMapper extends CommonMapper<UserRole> {
    /**
     * 查询所有的判卷官
     * @return 查询结果
     */
    List<User> selectAllReviewer();

    /**
     * 批量新增
     * @param userRoles 要新增的数组
     * @return 影响的行数
     */
    int saveList(@Param("userRoles") List<UserRole> userRoles);

    /**
     * 根据角色ID批量删除
     * @param roleIds 要删除的角色id
     * @return 影响的行数
     */
    int deleteByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据用户ID批量删除
     * @param userIds 要删除的用户id
     * @return 影响的行数
     */
    int deleteByUserIds(@Param("userIds") List<Long> userIds);


    /**
     * 根据用户ID查出所属角色
     * @param userId 用户id
     * @return 查询结果
     */
    List<Long> queryRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * 根据角色ID查询用户id
     * @param roleId 角色id
     * @return 查询结果
     */
    List<Long> queryUserIdsByRoleId(@Param("roleId") Long roleId);
}