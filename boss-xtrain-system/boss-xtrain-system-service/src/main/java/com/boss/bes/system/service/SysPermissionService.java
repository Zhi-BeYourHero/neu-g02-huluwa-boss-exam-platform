package com.boss.bes.system.service;

import java.util.Set;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统权限服务
 * @create 2020-07-08
 * @since
 */
public interface SysPermissionService {
    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    Set<String> getRolePermission(Long userId);

    /**
     * 获取资源数据权限
     *
     * @param userId 用户Id
     * @return 资源权限信息
     */
    Set<String> getResourcePermission(Long userId);

    /**
     * 获取用户的组织id
     * @param userId
     * @return
     */
    Set<String> getRoleOrgIds(Long userId);
}
