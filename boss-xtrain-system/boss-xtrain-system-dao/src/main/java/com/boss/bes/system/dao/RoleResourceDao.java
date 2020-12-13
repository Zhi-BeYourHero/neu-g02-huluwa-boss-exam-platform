package com.boss.bes.system.dao;

import com.boss.bes.system.entity.RoleResource;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-18
 * @since 1.0
 */
public interface RoleResourceDao {
    /**
     * 根据资源ID查询所分配的角色
     * @param resourceId 资源Id
     * @return 资源id对应的所有角色id
     */
    List<Long> queryRoleIdsByResourceId(Long resourceId);

    /**
     * 根据角色ID查询所分配的资源
     * @param roleId 角色id
     * @return 角色id对应的所有资源id
     */
    List<Long> queryResourceIdsByRoleId(Long roleId);

    /**
     * 条件查询角色所属资源
     * @param roleResource 角色资源中间表对象
     * @return 查询结果
     */
    List<RoleResource> queryRoleResource(RoleResource roleResource);

    /**
     * 新增资源角色中间表对象
     * @param roleResource 角色资源中间表对象
     * @return 是否保存成功
     */
    boolean save(RoleResource roleResource);

    /**
     * 批量新增
     * @param roleResources 角色资源中间表对象列表
     * @return 修改的行数
     */
    int saveList(List<RoleResource> roleResources);

    /**
     * 根据角色ID批量删除
     * @param roleIds 所有要删除的角色id
     * @return 受影响的行数
     */
    int deleteByRoleIds(List<Long> roleIds);

    /**
     * 根据角色ID删除
     * @param roleId 要删除对象的角色Id
     * @return 受影响的行数
     */
    int deleteByRoleId(Long roleId);

    /**
     * 根据资源id删除
     * @param resourceId 要删除的资源id
     * @return 受影响的行数
     */
    int deleteByResourceId(Long resourceId);

    /**
     * 根据资源id批量删除
     * @param resourceIdList 所有要删除的资源id
     * @return 受影响的行数
     */
    int deleteByResourceIds(List<Long> resourceIdList);

    /**
     * 查询是否有此roleId
     * @param roleId 要查询的角色id
     * @return 查询结果
     */
    int countByRoleId(Long roleId);

    /**
     * 根据entity删除
     * @param roleResource 中间表对象
     * @return 影响的行数
     */
    int deleteByEntity(RoleResource roleResource);

    /**
     * 按照Example进行查询数量
     * @param roleResourceExample Example对象
     * @return 查询结果
     */
    int selectCountByExample(Example roleResourceExample);

    /**
     * 获取所有信息
     * @return 查询结果
     */
    List<RoleResource> listAll();
}
