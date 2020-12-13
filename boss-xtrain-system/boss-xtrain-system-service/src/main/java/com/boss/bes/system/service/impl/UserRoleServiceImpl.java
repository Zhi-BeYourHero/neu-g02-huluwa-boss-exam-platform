package com.boss.bes.system.service.impl;

import com.boss.bes.system.dao.UserRoleDao;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.entity.UserRole;
import com.boss.bes.system.service.UserRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleDao userRoleDao;

    /**
     * 获取所有的判卷官
     *
     * @return 查询结果
     */
    @Override
    public List<User> queryAllReviewer() {
        return userRoleDao.queryAllReviewer();
    }

    /**
     * 添加新的角色用户对象
     *
     * @param userRole 要新增的角色用户对象
     * @return 影响的行数
     */
    @Override
    public Integer save(UserRole userRole) {
        return userRoleDao.save(userRole);
    }

    /**
     * 通过用户id删除
     *
     * @param userId 用户id
     * @return 影响的行数
     */
    @Override
    public Integer deleteByUserId(Long userId) {
        return userRoleDao.deleteByUserId(userId);
    }

    /**
     * 通过角色id删除
     *
     * @param roleId 角色id
     * @return 影响的行数
     */
    @Override
    public Integer deleteByRoleId(Long roleId) {
        return userRoleDao.deleteByRoleId(roleId);
    }

    /**
     * 通过用户Id获取所有的角色Id
     * @param userId 用户Id
     * @return 查询结果
     */
    @Override
    public List<Long> getRoleIdsByUserId(Long userId){
        return userRoleDao.queryRoleIdsByUserId(userId);
    }

    /**
     * 通过角色Id获取所有的用户Id
     *
     * @param roleId 角色Id
     * @return 查询结果
     */
    @Override
    public List<Long> getUserIdsByRoleId(Long roleId) {
        return userRoleDao.queryUserIdsByRoleId(roleId);
    }

    @Override
    public Integer saveList(List<UserRole> userRoles) {
        return userRoleDao.saveList(userRoles);
    }


    /**
     * 获取所有的角色用户信息
     *
     * @return 查询结果
     */
    @Override
    public List<UserRole> listAll() {
        return userRoleDao.listAll();
    }
}
