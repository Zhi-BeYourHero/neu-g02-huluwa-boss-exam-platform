package com.boss.bes.system.dao;

import com.boss.bes.system.entity.Role;
import com.boss.bes.system.query.RoleQuery;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 定义非通用的Mapper方法
 * @create 2020-07-10 19:59
 * @since 1.0
 */

public interface RoleDao{

    /**
     * 保存新建的角色信息
     * @param role 角色的对象
     * @return 修改的行数
     */
    Integer save(Role role);

    /**
     * 删除角色信息
     * @param id 角色的id
     * @return 修改的行数
     */
    Integer remove(Long id);

    /**
     * 更新角色信息
     * @param role 角色的对象
     * @return 修改的行数
     */
    Integer update(Role role);

    /**
     * 使用Example查询
     * @param roleQuery 角色的Query对象
     * @return 查询结果
     */
    List<Role> queryByCondition(RoleQuery roleQuery);

    /**
     * 通过id获取角色对象
     * @param id 角色Id
     * @return 角色对象
     */
    Role getRoleById(Long id);

    /**
     * 批量删除角色
     * @param ids 选中的角色id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);

}
