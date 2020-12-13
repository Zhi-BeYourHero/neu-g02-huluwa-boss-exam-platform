package com.boss.bes.system.dao.impl;

import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dao.UserDao;
import com.boss.bes.system.dto.UserDto;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.mapper.UserMapper;
import com.boss.bes.system.mapper.UserRoleMapper;
import com.boss.bes.system.query.UserQuery;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description UserDao的实现类
 * @create 2020-07-10 19:57
 * @since 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 保存新建的用户信息
     *
     * @param user 用户的对象
     * @return 修改的行数
     */
    @Override
    public Integer save(User user) {
        return userMapper.insert(user);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户的id
     * @return 修改的行数
     */
    @Override
    public Integer remove(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户的对象
     * @return 修改的行数
     */
    @Override
    public Integer update(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",user.getId());
        return userMapper.updateByExample(user,example);
    }

    @Override
    public List<User> queryByCondition(UserQuery query) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId",query.getCompanyId());
        criteria.andEqualTo("code",query.getCode());
        criteria.andEqualTo("name",query.getName());
        criteria.andEqualTo("tel",query.getTel());
        criteria.andEqualTo("createdBy",query.getCreatedBy());
        criteria.andEqualTo("updatedBy",query.getUpdatedBy());
        criteria.andEqualTo("status",query.getStatus());
        criteria.andEqualTo("version",query.getVersion());
        return userMapper.selectByExample(example);
    }

    @Override
    public String getUsernameById(Long id) {
        return userMapper.selectByPrimaryKey(id).getName();
    }

    @Override
    public User getUserById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过用户id获取
     *
     * @param ids 用户被选中的id
     * @return 影响的行数
     */
    @Override
    public Integer batchRemove(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            count += this.userMapper.deleteByPrimaryKey(id);
            this.userRoleMapper.deleteByPrimaryKey(id);
        }
        return count;
    }

    /**
     * 批量添加用户
     *
     * @param users 所有用户对象
     * @return 影响的行数
     */
    @Override
    public Integer batchAdd(List<User> users) {
        int count = 0;
        for(User user: users){
            count += this.userMapper.insert(user);
        }
        return count;
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户Id
     * @return 查询结果
     */
    @Override
    public UserDto queryById(Long userId) {
        User user = this.userMapper.selectByPrimaryKey(userId);
        return BeanUtil.copyProperties(user, UserDto.class);
    }

    /**
     * 带有角色的多表查询
     *
     * @param userQuery 查询对象
     * @return 查询结果
     */
    @Override
    public List<User> queryWithRoleId(UserQuery userQuery) {
        return this.userMapper.queryWithRole(userQuery);
    }
}