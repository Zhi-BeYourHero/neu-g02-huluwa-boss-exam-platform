package com.boss.bes.system.service.impl;

import boss.xtrain.core.constant.Constants;
import boss.xtrain.security.domain.SysRole;
import boss.xtrain.security.domain.SysUser;
import com.boss.bes.system.mapper.RoleMapper;
import com.boss.bes.system.service.ResourceService;
import com.boss.bes.system.service.RoleService;
import com.boss.bes.system.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08
 * @since
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Resource
    private RoleMapper roleMapper;


    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(Long userId) {
        return roleService.selectRolePermissionByUserId(userId);
    }

    /**
     * 获取资源数据权限
     *
     * @param userId 用户Id
     * @return 资源权限信息
     */
    @Override
    public Set<String> getResourcePermission(Long userId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId))
        {
            perms.add("/");
        }else{
            perms.addAll(resourceService.selectResourcePermsByUserId(userId));
        }
        return perms;
    }

    @Override
    public Set<String> getRoleOrgIds(Long userId) {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> orgIds = new HashSet<>();
        for (SysRole perm : perms) {
            orgIds.add(String.valueOf(perm.getOrgId()));
        }
        return orgIds;
    }
}