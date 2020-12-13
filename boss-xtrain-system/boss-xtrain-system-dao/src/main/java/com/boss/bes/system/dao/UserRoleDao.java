package com.boss.bes.system.dao;

import com.boss.bes.system.entity.User;
import com.boss.bes.system.entity.UserRole;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 定义非通用的Mapper方法
 * @create 2020-07-09
 * @since 1.0
 */
public interface UserRoleDao {

    /**
     * 获取所有的判卷官
     * @return 查询结果
     */
    List<User> queryAllReviewer();

    /**
     * 根据用户ID查询所分配的角色
     * @param userId 用户Id
     * @return 用户id对应的所有角色id
     */
    List<Long> queryRoleIdsByUserId(Long userId);

    /**
     * 根据角色ID查询所对应的用户
     * @param roleId 角色id
     * @return 角色id对应的所有用户id
     */
    List<Long> queryUserIdsByRoleId(Long roleId);

    /**
     * 条件查询角色所属角色
     * @param userRole 角色用户的中间表对象
     * @return 所有
     */
    List<UserRole> queryRoleResource(UserRole userRole);

    /**
     * 保存新增的中间表对象
     * @param userRole 用户和角色的实体类
     * @return 受影响的行数
     */
    Integer save(UserRole userRole);

    /**
     * 批量新增
     * @param userRoles 所有的中间表对象
     * @return 受影响的行数
     */
    Integer saveList(List<UserRole> userRoles);

    /**
     * 根据角色ID批量删除
     * @param roleIds 所有的角色id
     * @return 受影响的行数
     */
    Integer deleteByRoleIds(List<Long> roleIds);

    /**
     * 根据角色ID删除
     * @param roleId 要删除的角色id
     * @return 受影响的行数
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 根据用户id删除
     * @param userId 要删除的用户Id
     * @return 受影响的行数
     */
    Integer deleteByUserId(Long userId);

    /**
     * 根据用户id批量删除
     * @param userIds 要删除的所有用户Id
     * @return 影响的行数
     */
    Integer deleteByUserIds(List<Long> userIds);



    /**
     * 查询是否有此roleId
     * @param roleId 要查询的角色id
     * @return 查询结果
     */
    Integer countByRoleId(Long roleId);

    /**
     * 根据entity删除
     * @param userRole 要删除的中间表对象
     * @return 受影响的行数
     */
    Integer deleteByEntity(UserRole userRole);

    /**
     * 按照Example进行查询
     * @param userRoleExample 要查询的Example对象
     * @return 受影响的行数
     */
    Integer selectCountByExample(Example userRoleExample);

    /**
     * 获取所有信息
     * @return 查询结果
     */
    List<UserRole> listAll();
}
