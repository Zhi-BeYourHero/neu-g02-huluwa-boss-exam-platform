package com.boss.bes.system.service;

import com.boss.bes.system.dto.RoleResourceDto;
import com.boss.bes.system.entity.RoleResource;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色资源多表查询的服务接口
 * @create 2020-07-20 9:25
 * @since 1.0
 */

public interface RoleResourceService{

    /**
     * 添加新的角色资源对象
     * @param roleResource 要新增的角色资源对象
     * @return 影响的行数
     */
    boolean save(RoleResource roleResource);

    /**
     * 通过角色id删除
     * @param id 角色id
     * @return 影响的行数
     */
    Integer delete(Long id);

    /**
     * 获取所有的角色资源信息
     * @return 查询结果
     */
    List<RoleResource> listAll();

    /**
     * 按照角色Id进行资源Id查询
     * @param roleId 角色Id
     * @return 查询结果
     */
    List<Long> getResourceIdsByRoleId(Long roleId);

    /**
     * 批量添加
     * @param roleResourceDtos
     * @return
     */
    Integer saveList(List<RoleResourceDto> roleResourceDtos);
}
