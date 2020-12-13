package com.boss.bes.system.service;


import com.boss.bes.system.dto.ResourceDto;

import com.boss.bes.system.dto.TreeDto;
import com.boss.bes.system.entity.Resource;

import com.boss.bes.system.query.ResourceQuery;

import java.util.List;
import java.util.Set;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 资源的服务接口
 * @create 2020-07-10
 * @since 1.0
 */
public interface ResourceService{
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectResourcePermsByUserId(Long userId);
    /**
     * 增加一个资源
     * @param resourceDto
     * @return
     */
    Integer save(ResourceDto resourceDto);

    /**
     * 删除一个资源
     * @param resourceDto
     * @return
     */
    Integer remove(ResourceDto resourceDto);

    /**
     * 更新一个资源
     * @param resourceDto
     * @return
     */
    Integer update(ResourceDto resourceDto);

    /**
     * 按条件查询资源
     * @param resourceQuery
     * @return 查询结果
     */
    List<Resource> query(ResourceQuery resourceQuery);
    /**
     * 返回所有的资源
     * @return
     */
    List<Resource> listAll();

    /**
     * 获取资源侧边栏
     * @return
     */
    List<TreeDto> queryRoutes();
    /**
     * 批量删除资源
     * @param ids 选中的id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
