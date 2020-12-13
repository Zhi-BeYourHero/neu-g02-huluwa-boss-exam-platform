package com.boss.bes.system.service;

import com.boss.bes.system.dto.RoleDto;
import com.boss.bes.system.entity.Role;
import com.boss.bes.system.query.RoleQuery;

import java.util.List;
import java.util.Set;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的服务接口
 * @create 2020-07-07
 * @since 1.0
 */

public interface RoleService{

    /**
     * 保存一个新建的角色对象
     * @param roleDto 角色的DTO对象
     * @return 影响的行数
     */
    Integer save(RoleDto roleDto);

    /**
     * 删除一个指定的角色对象
     * @param id 角色的id
     * @return 影响的行数
     */
    Integer remove(Long id);

    /**
     * 更新一个指定的角色对象
     * @param roleDto 角色的Dto对象
     * @return 影响的行数
     */
    Integer update(RoleDto roleDto);

    /**
     * 按条件进行查询
     * @param roleQuery 封装的查询条件
     * @return 查询的返回值
     */
    List<Role> query(RoleQuery roleQuery);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);

    /**
     * 获取所有角色Id
     * @return 所有角色id
     */
    List<Role> listAll();

    /**
     * 通过id获取角色
     * @param id 角色id
     * @return 角色对象
     */
    Role getRoleById(Long id);

    /**
     * 模糊查询
     * @param roleQuery 含有模糊查询写法的角色Query对象
     * @return 查询结果
     */
    List<Role> getRoleByFuzzyName(RoleQuery roleQuery);

    /**
     * 批量删除
     * @param ids 选中的角色id
     * @return 影响的函数
     */
    Integer batchRemove(Long[] ids);
}
