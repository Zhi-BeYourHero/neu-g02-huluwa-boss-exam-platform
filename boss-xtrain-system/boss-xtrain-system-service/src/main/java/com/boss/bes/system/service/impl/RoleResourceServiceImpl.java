package com.boss.bes.system.service.impl;

import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dao.RoleResourceDao;
import com.boss.bes.system.dto.RoleResourceDto;
import com.boss.bes.system.entity.RoleResource;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.boss.bes.system.service.RoleResourceService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色资源多表查询的服务接口实现类
 * @create 2020-07-20 9:25
 * @since 1.0
 */

@Service
public class RoleResourceServiceImpl implements RoleResourceService{

    @Resource
    private RoleResourceDao roleResourceDao;

    /**
     * 添加新的角色资源对象
     *
     * @param roleResource 要新增的角色资源对象
     * @return 影响的行数
     */
    @Override
    public boolean save(RoleResource roleResource) {
        return roleResourceDao.save(roleResource);
    }

    /**
     * 通过角色id删除
     *
     * @param id 角色id
     * @return 影响的行数
     */
    @Override
    public Integer delete(Long id) {
        return roleResourceDao.deleteByRoleId(id);
    }

    /**
     * 获取所有的角色资源信息
     *
     * @return 查询结果
     */
    @Override
    public List<RoleResource> listAll() {
        return roleResourceDao.listAll();
    }

    /**
     * 按照角色Id进行查询
     *
     * @param roleId 角色Id
     * @return 查询结果
     */
    @Override
    public List<Long> getResourceIdsByRoleId(Long roleId) {
        List<Long> resourcesIds = roleResourceDao.queryResourceIdsByRoleId(roleId);
        // 删去父节点的资源id
        resourcesIds.remove(1246443261099380736L);
        resourcesIds.remove(1246658443771777024L);
        resourcesIds.remove(1246442767337525248L);
        resourcesIds.remove(1246443137224806400L);
        resourcesIds.remove(1246715615201333248L);
        return resourcesIds;
    }

    /**
     * 批量添加
     *
     * @param roleResourceDtos
     * @return
     */
    @Override
    public Integer saveList(List<RoleResourceDto> roleResourceDtos) {
        List<RoleResource> roleResources = new ArrayList<>();
        for(RoleResourceDto roleResourceDto: roleResourceDtos){
            roleResources.add(BeanUtil.copyProperties(roleResourceDto, RoleResource.class));
        }
        return this.roleResourceDao.saveList(roleResources);
    }
}
