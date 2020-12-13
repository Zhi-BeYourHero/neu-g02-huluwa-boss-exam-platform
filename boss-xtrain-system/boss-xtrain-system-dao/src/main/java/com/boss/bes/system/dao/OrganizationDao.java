package com.boss.bes.system.dao;


import com.boss.bes.system.entity.Organization;
import com.boss.bes.system.query.OrganizationQuery;


import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @description 组织机构的Dao接口
 * @since 1.0
 */
public interface OrganizationDao {
    /**
     * 通过id查询组织机构名字
     * @param id 组织机构id
     * @return 组织机构名字
     */
    String selectNameByPrimaryKey(Long id);
    /**
     * 保存新建的组织机构信息
     *
     * @param organization 组织机构的对象
     * @return 修改的行数
     */
    Integer save(Organization organization);
    /**
     * 删除组织机构信息
     *
     * @param organization 组织机构的对象
     * @return 修改的行数
     */
    Integer remove(Organization organization);
    /**
     * 更新组织机构信息
     *
     * @param organization 组织机构的对象
     * @return 修改的行数
     */
    Integer update(Organization organization);

    /**
     *
     * @param query 查询条件
     * @return 查询结果
     */
    List<Organization> queryByCondition(OrganizationQuery query);
    /**
     * 批量删除组织机构
     * @param ids 要删除的组织机构id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
