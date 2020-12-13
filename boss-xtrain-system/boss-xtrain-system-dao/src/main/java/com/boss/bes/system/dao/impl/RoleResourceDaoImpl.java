package com.boss.bes.system.dao.impl;

import com.boss.bes.system.dao.RoleResourceDao;
import com.boss.bes.system.entity.RoleResource;
import com.boss.bes.system.mapper.RoleResourceMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-18
 * @since 1.0
 */
@Repository
public class RoleResourceDaoImpl implements RoleResourceDao {

    @Resource
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List<Long> queryRoleIdsByResourceId(Long resourceId) {
        return roleResourceMapper.queryRoleIdsByResourceId(resourceId);
    }

    @Override
    public List<Long> queryResourceIdsByRoleId(Long roleId) {
        return roleResourceMapper.queryResourceIdsByRoleId(roleId);
    }

    @Override
    public List<RoleResource> queryRoleResource(RoleResource roleResource) {
        return roleResourceMapper.select(roleResource);
    }

    @Override
    public boolean save(RoleResource roleResource) {
        return roleResourceMapper.insert(roleResource) == 1;
    }

    @Override
    public int saveList(List<RoleResource> roleResources) {
        return roleResourceMapper.saveList(roleResources);
    }

    @Override
    public int deleteByRoleIds(List<Long> roleIds) {
        return roleResourceMapper.deleteByRoleIds(roleIds);
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        WeekendSqls<RoleResource> sqls = WeekendSqls.custom();
        sqls = sqls.andEqualTo(RoleResource::getId, roleId)
                .andNotEqualTo(RoleResource::getResourceId, 1246443261099380736L)
                .andNotEqualTo(RoleResource::getResourceId, 1246658443771777024L)
                .andNotEqualTo(RoleResource::getResourceId, 1246442767337525248L)
                .andNotEqualTo(RoleResource::getResourceId, 1246443137224806400L)
                .andNotEqualTo(RoleResource::getResourceId, 1246715615201333248L);;

        Example example = Example.builder(RoleResource.class)
                .where(sqls)
                .build();
        return roleResourceMapper.deleteByExample(example);
    }

    @Override
    public int deleteByResourceId(Long resourceId) {
        WeekendSqls<RoleResource> sqls = WeekendSqls.custom();
        sqls = sqls.andEqualTo(RoleResource::getResourceId, resourceId);

        Example example = Example.builder(RoleResource.class)
                .where(sqls)
                .build();
        return roleResourceMapper.deleteByExample(example);
    }

    @Override
    public int deleteByResourceIds(List<Long> resourceIdList) {
        return roleResourceMapper.deleteByResourceIds(resourceIdList);
    }

    @Override
    public int countByRoleId(Long roleId) {
        WeekendSqls<RoleResource> sqls = WeekendSqls.custom();
        sqls = sqls.andEqualTo(RoleResource::getId, roleId);

        Example example = Example.builder(RoleResource.class)
                .where(sqls)
                .build();
        return roleResourceMapper.selectCountByExample(example);
    }

    @Override
    public int deleteByEntity(RoleResource roleResource) {
        return roleResourceMapper.delete(roleResource);
    }

    @Override
    public int selectCountByExample(Example roleResourceExample) {
        return roleResourceMapper.selectCountByExample(roleResourceExample);
    }

    /**
     * 获取所有信息
     *
     * @return 查询结果
     */
    @Override
    public List<RoleResource> listAll() {
        return roleResourceMapper.selectAll();
    }
}