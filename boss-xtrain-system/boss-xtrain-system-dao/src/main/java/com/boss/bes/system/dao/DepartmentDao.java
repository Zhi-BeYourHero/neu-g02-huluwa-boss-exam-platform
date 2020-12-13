package com.boss.bes.system.dao;



import com.boss.bes.system.entity.Department;

import com.boss.bes.system.query.DepartmentQuery;

import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @since 1.0
 */
public interface DepartmentDao {
    /**
     * 保存新建的部门信息
     *
     * @param department 部门的对象
     * @return 修改的行数
     */
    Integer save(Department department);
    /**
     * 删除部门信息
     *
     * @param department 部门的对象
     * @return 修改的行数
     */
    Integer remove(Department department);
    /**
     * 更新部门信息
     *
     * @param department 部门的对象
     * @return 修改的行数
     */
    Integer update(Department department);

    /**
     *
     * @param query 查询条件
     * @return 查询结果
     */
    List<Department> queryByCondition(DepartmentQuery query);
    /**
     * 批量删除部门
     * @param ids 要删除的部门id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
