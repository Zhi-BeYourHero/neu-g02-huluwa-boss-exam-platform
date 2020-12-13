package com.boss.bes.system.dao;

import com.boss.bes.system.dto.UserDto;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.query.UserQuery;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 定义非通用的Mapper方法
 * @create 2020-07-10 19:55
 * @since 1.0
 */

public interface UserDao{

    /**
     * 保存新建的用户信息
     * @param user 用户的对象
     * @return 修改的行数
     */
    Integer save(User user);

    /**
     * 删除用户信息
     * @param id 用户的id
     * @return 修改的行数
     */
    Integer remove(Long id);

    /**
     * 更新用户信息
     * @param user 用户的对象
     * @return 修改的行数
     */
    Integer update(User user);

    /**
     * 使用Example查询
     * @param userQuery 用户的Query对象
     * @return 查询结果
     */
    List<User> queryByCondition(UserQuery userQuery);

    /**
     * 通过用户的id获取用户名
     * @param id 用户id
     * @return 用户名
     */
    String getUsernameById(Long id);

    /**
     *
     * 通过用户的id获取用户名
     * @param id 用户id
     * @return 用户对象
     */
    User getUserById(Long id);

    /**
     * 通过用户id获取
     * @param ids 用户被选中的id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);

    /**

     * 批量添加用户
     * @param users 所有用户对象
     * @return 影响的行数
     */
    Integer batchAdd(List<User> users);

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户Id
     * @return 查询结果
     */
    UserDto queryById(Long userId);

    /**
     * 带有角色的多表查询
     * @param userQuery 查询对象
     * @return 查询结果
     */
    List<User> queryWithRoleId(UserQuery userQuery);
}
