package com.boss.bes.system.service;

import com.boss.bes.system.dto.UserDto;
import com.boss.bes.system.entity.User;
import boss.xtrain.security.domain.SysUser;
import com.boss.bes.system.query.UserQuery;

import java.util.List;
/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用户的服务接口
 * @create 2020-07-07
 * @since 1.0
 */
public interface UserService{

    /**
     * 保存一个新建的用户对象
     * @param userDto 用户的DTO对象
     * @return 影响的行数
     */
    Integer save(UserDto userDto);

    /**
     * 删除一个指定的用户对象
     * @param id 用户的id
     * @return 影响的行数
     */
    Integer remove(Long id);

    /**
     * 更新一个指定的用户对象
     * @param userDto 用户的Dto对象
     * @return 影响的行数
     */
    Integer update(UserDto userDto);

    /**
     * 按条件进行查询
     * @param userQuery 封装的查询条件
     * @return 查询的返回值
     */
    List<User> query(UserQuery userQuery);

    List<User> getUsers();

    SysUser selectUserByUserName(String username);

    /**
     * 按用户名进行模糊查询
     * @param name 用户名 模糊查询
     * @return 查询结果
     */
    List<User> selectUserByFuzzyUsername(String name);

    /**
     * 通过用户的id获取用户名
     * @param id 用户id
     * @return 用户名
     */
    String getUsernameById(Long id);

    /**
     * 通过用户id获取用户对象
     * @param id 用户id
     * @return 用户对象
     */
    User getUserById(Long id);

    /**
     * 批量删除用户
     * @param ids 选中的用户Id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);

    /**
     * 批量添加用户
     * @param userDtos 所有用户DTO对象
     * @return 影响的行数
     */
    Integer batchAdd(List<UserDto> userDtos);

    /**
     * 带有角色Id的多表查询
     * @param userQuery 用户Query对象
     * @return 查询结果
     */
    List<User> queryWithRoleId(UserQuery userQuery);
}
