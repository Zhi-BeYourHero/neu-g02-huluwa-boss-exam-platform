package com.boss.bes.system.service;

import com.boss.bes.system.entity.RoleResource;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.entity.UserRole;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色和用户的联合服务接口
 * @create 2020-07-07
 * @since 1.0
 */

public interface UserRoleService{

    /**
     * 获取所有的判卷官
     * @return 查询结果
     */
    List<User> queryAllReviewer();

    /**
     * 添加新的角色用户对象
     * @param userRole 要新增的角色用户对象
     * @return 影响的行数
     */
    Integer save(UserRole userRole);

    /**
     * 通过用户id删除
     * @param userId 用户id
     * @return 影响的行数
     */
    Integer deleteByUserId(Long userId);

    /**
     * 通过角色id删除
     * @param roleId 角色id
     * @return 影响的行数
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 获取所有的角色用户信息
     * @return 查询结果
     */
    List<UserRole> listAll();

    /**
     * 通过用户Id获取所有的角色Id
     * @param userId 用户Id
     * @return 查询结果
     */
    List<Long> getRoleIdsByUserId(Long userId);

    /**
     * 通过角色Id获取所有的用户Id
     * @param roleId 角色Id
     * @return 查询结果
     */
    List<Long> getUserIdsByRoleId(Long roleId);

    /**
     * 批量保存
     * @param userRoles 中间表对象
     * @return 查询结果
     */
    Integer saveList(List<UserRole> userRoles);

}
