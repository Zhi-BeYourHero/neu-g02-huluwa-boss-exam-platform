package com.boss.bes.system.service;


import com.boss.bes.system.dto.DepartmentDto;
import com.boss.bes.system.entity.Department;


import com.boss.bes.system.query.DepartmentQuery;

import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 部门的服务接口
 * @create 2020-07-10
 * @since 1.0
 */
public interface DepartmentService{

    /**
     * 增加一个部门
     * @param departmentDto
     * @return
     */
    Integer save(DepartmentDto departmentDto);

    /**
     * 删除一个部门
     * @param departmentDto
     * @return
     */
    Integer remove(DepartmentDto departmentDto);

    /**
     * 更新一个部门
     * @param departmentDto
     * @return
     */
    Integer update(DepartmentDto departmentDto);

    /**
     * 按条件查询部门
     * @param departmentQuery
     * @return 查询结果
     */
    List<Department> query(DepartmentQuery departmentQuery);
    /**
     * 返回所有的部门
     * @return
     */
    List<Department> listAll();
    /**
     * 批量删除部门
     * @param ids 选中的id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
