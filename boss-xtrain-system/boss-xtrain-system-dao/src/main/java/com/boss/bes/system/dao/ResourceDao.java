package com.boss.bes.system.dao;



import com.boss.bes.system.dto.ResourceDto;
import com.boss.bes.system.entity.Resource;

import com.boss.bes.system.query.ResourceQuery;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @since 1.0
 */
public interface ResourceDao {
    /**
     * 保存新建的资源信息
     *
     * @param resource 资源的对象
     * @return 修改的行数
     */
    Integer save(Resource resource);
    /**
     * 删除资源信息
     *
     * @param resource 资源的对象
     * @return 修改的行数
     */
    Integer remove(Resource resource);
    /**
     * 更新资源信息
     *
     * @param resource 资源的对象
     * @return 修改的行数
     */
    Integer update(Resource resource);

    /**
     *
     * @param query 查询条件
     * @return 查询结果
     */
    List<Resource> queryByCondition(ResourceQuery query);

    /**
     * 条件查询资源列表
     * @param example
     * @return
     */
    List<ResourceDto> queryResource(Example example);
    /**
     * 批量删除资源
     * @param ids 要删除的资源id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
