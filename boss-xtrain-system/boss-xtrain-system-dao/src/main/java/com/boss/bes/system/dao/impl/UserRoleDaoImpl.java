package com.boss.bes.system.dao.impl;

import com.boss.bes.system.dao.UserRoleDao;
import com.boss.bes.system.entity.RoleResource;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.entity.UserRole;
import com.boss.bes.system.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色和用户多表查询的Dao实现类
 * @create 2020-07-11 15:40
 * @since 1.0
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 获取所有的判卷官
     *
     * @return 查询结果
     */
    @Override
    public List<User> queryAllReviewer() {
        return userRoleMapper.selectAllReviewer();
    }

    /**
     * 根据用户ID查询所分配的角色
     *
     * @param userId 用户Id
     * @return 用户id对应的所有角色id
     */
    @Override
    public List<Long> queryRoleIdsByUserId(Long userId) {
        return userRoleMapper.queryRoleIdsByUserId(userId);
    }

    /**
     * 根据角色ID查询所对应的用户
     *
     * @param roleId 角色id
     * @return 角色id对应的所有用户id
     */
    @Override
    public List<Long> queryUserIdsByRoleId(Long roleId) {
        return userRoleMapper.queryUserIdsByRoleId(roleId);
    }

    /**
     * 条件查询角色所属角色
     *
     * @param userRole 角色用户的中间表对象
     * @return 所有
     */
    @Override
    public List<UserRole> queryRoleResource(UserRole userRole) {
        return userRoleMapper.select(userRole);
    }

    /**
     * 保存新增的中间表对象
     *
     * @param userRole 用户和角色的实体类
     * @return 受影响的行数
     */
    @Override
    public Integer save(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }

    /**
     * 批量新增
     *
     * @param userRoles 所有的中间表对象
     * @return 受影响的行数
     */
    @Override
    public Integer saveList(List<UserRole> userRoles) {
        return userRoleMapper.saveList(userRoles);
    }

    /**
     * 根据角色ID批量删除
     *
     * @param roleIds 所有的角色id
     * @return 受影响的行数
     */
    @Override
    public Integer deleteByRoleIds(List<Long> roleIds) {
        return userRoleMapper.deleteByRoleIds(roleIds);
    }

    /**
     * 根据角色ID删除
     *
     * @param roleId 要删除的角色id
     * @return 受影响的行数
     */
    @Override
    public Integer deleteByRoleId(Long roleId) {
        WeekendSqls<UserRole> sqls = WeekendSqls.custom();
        sqls = sqls.andEqualTo(UserRole::getRoleId, roleId);

        Example example = Example.builder(UserRole.class)
                .where(sqls)
                .build();
        return userRoleMapper.deleteByExample(example);
    }

    /**
     * 根据用户id删除
     *
     * @param userId 要删除的用户Id
     * @return 受影响的行数
     */
    @Override
    public Integer deleteByUserId(Long userId) {
        WeekendSqls<UserRole> sqls = WeekendSqls.custom();
        sqls = sqls.andEqualTo(UserRole::getId, userId);

        Example example = Example.builder(UserRole.class)
                .where(sqls)
                .build();
        return userRoleMapper.deleteByExample(example);
    }

    /**
     * 根据用户id批量删除
     *
     * @param userIds 要删除的所有用户Id
     * @return 影响的行数
     */
    @Override
    public Integer deleteByUserIds(List<Long> userIds) {
        return userRoleMapper.deleteByUserIds(userIds);
    }

    /**
     * 查询是否有此roleId
     *
     * @param roleId 要查询的角色id
     * @return 查询结果
     */
    @Override
    public Integer countByRoleId(Long roleId) {
        WeekendSqls<UserRole> sqls = WeekendSqls.custom();
        sqls = sqls.andEqualTo(UserRole::getRoleId, roleId);

        Example example = Example.builder(RoleResource.class)
                .where(sqls)
                .build();
        return userRoleMapper.selectCountByExample(example);
    }

    /**
     * 根据entity删除
     *
     * @param userRole 要删除的中间表对象
     * @return 受影响的行数
     */
    @Override
    public Integer deleteByEntity(UserRole userRole) {
        return userRoleMapper.delete(userRole);
    }

    /**
     * 按照Example进行查询
     *
     * @param userRoleExample 要查询的Example对象
     * @return 受影响的行数
     */
    @Override
    public Integer selectCountByExample(Example userRoleExample) {
        return userRoleMapper.selectCountByExample(userRoleExample);
    }

    /**
     * 获取所有信息
     *
     * @return 查询结果
     */
    @Override
    public List<UserRole> listAll() {
        return userRoleMapper.selectAll();
    }
}