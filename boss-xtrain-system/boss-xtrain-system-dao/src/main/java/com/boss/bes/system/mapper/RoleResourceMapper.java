package com.boss.bes.system.mapper;

import com.boss.bes.system.entity.RoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleResourceMapper extends tk.mybatis.mapper.common.Mapper<RoleResource> {
    /**
     * 批量新增
     * @param roleResources
     * @return
     */
    int saveList(@Param("roleResources") List<RoleResource> roleResources);

    /**
     * 根据角色ID批量删除
     * @param roleIds
     * @return
     */
    int deleteByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据资源ID批量删除
     * @param resourceIds
     * @return
     */
    int deleteByResourceIds(@Param("resourceIds") List<Long> resourceIds);


    /**
     * 根据资源ID查出所属角色
     * @param resourceId
     * @return
     */
    List<Long> queryRoleIdsByResourceId(@Param("resourceId") Long resourceId);

    /**
     * 根据角色ID查询分配的资源
     * @param roleId
     * @return
     */
    List<Long> queryResourceIdsByRoleId(@Param("roleId") Long roleId);
}