package com.boss.bes.system.service.impl;

import boss.xtrain.security.util.SecurityUtils;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.SystemException;
import com.boss.bes.system.dao.UserDao;
import com.boss.bes.system.dto.UserDto;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.query.UserQuery;
import com.boss.bes.system.service.UserService;
import com.boss.bes.system.mapper.UserMapper;
import boss.xtrain.security.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 在线用户的服务实现类
 * @create 2020-07-10
 * @since 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDao userDao;

    private static final String MODULE_NAME = "USER";

    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);

    /**
     * 保存一个新建的用户对象
     *
     * @param userDto 用户的DTO对象
     * @return 影响的行数
     */
    @Override
    public Integer save(UserDto userDto) {
        try{
            User user = this.doDtoTransf2Entity(userDto);
            Long id = this.snowflakeWorker.nextId();
            String password = userDto.getPassword();
            if(password != null){
                String encryptedPassword = SecurityUtils.encryptPassword(password);
                user.setPassword(encryptedPassword);
            }
            user.setId(id);
            user.setCreatedTime(new Date(System.currentTimeMillis()));
            user.setVersion(1L);
            user.setUpdatedBy(null);
            user.setUpdatedTime(null);
            return userDao.save(user);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210901","USER_INSERT_FAILURE");
        }
    }

    /**
     * 删除一个指定的用户对象
     *
     * @param id 用户的id
     * @return 影响的行数
     */
    @Override
    public Integer remove(Long id) {
        try{
            return userDao.remove(id);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210902","USER_DELETE_FAILURE");
        }
    }

    /**
     * 更新一个指定的用户对象
     *
     * @param userDto 用户的Dto对象
     * @return 影响的行数
     */
    @Override
    public Integer update(UserDto userDto) {
        try{
            User user = this.doDtoTransf2Entity(userDto);
            String password = userDto.getPassword();
            String encryptedPassword = SecurityUtils.encryptPassword(password);
            user.setPassword(encryptedPassword);
            user.setUpdatedTime(new Date());
            Long newVersion = user.getVersion() + 1;
            user.setVersion(newVersion);
            return userDao.update(user);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210903","USER_UPDATE_FAILURE");
        }
    }

    /**
     * 按条件进行查询
     *
     * @param userQuery 封装的查询条件
     * @return 查询的返回值
     */
    @Override
    public List<User> query(UserQuery userQuery) {
        try{
            return userDao.queryByCondition(userQuery);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210904","USER_QUERY_FAILURE");
        }
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }

    @Override
    public SysUser selectUserByUserName(String username) {
        return userMapper.selectSysUserByUserName(username);
    }

    /**
     * 按用户名进行模糊查询
     * @param username 用户名 模糊查询
     * @return 查询结果
     */
    @Override
    public List<User> selectUserByFuzzyUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andLike("name", "%"+username+"%");
        return userMapper.selectByExample(example);
    }

    /**
     * 通过用户的id获取用户名
     * @param id 用户id
     * @return 用户名
     */
    @Override
    public String getUsernameById(Long id) {
        return userDao.getUsernameById(id);
    }

    /**
     * 通过用户id获取用户对象
     *
     * @param id 用户id
     * @return 用户对象
     */
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    /**
     * 批量删除用户
     *
     * @param ids 选中的用户Id
     * @return 影响的行数
     */
    @Override
    public Integer batchRemove(Long[] ids) {
        return this.userDao.batchRemove(ids);
    }

    /**
     * 批量添加用户
     *
     * @param userDtos 所有用户DTO对象
     * @return 影响的行数
     */
    @Override
    public Integer batchAdd(List<UserDto> userDtos) {
        List<User> users = new ArrayList<>();
        for(UserDto userDto: userDtos){
            users.add(this.doDtoTransf2Entity(userDto));
        }
        return this.userDao.batchAdd(users);
    }

    /**
     * 带有角色Id的多表查询
     *
     * @param userQuery 用户Query对象
     * @return 查询结果
     */
    @Override
    public List<User> queryWithRoleId(UserQuery userQuery) {
        return this.userDao.queryWithRoleId(userQuery);
    }

    /**
     * 子类决定如何做增删改过程中 dto到entity对象转化
     * @param dto DTO对象
     * @return 实体类对象
     */
    protected User doDtoTransf2Entity(UserDto dto) {
        return BeanUtil.copyProperties(dto,User.class);
    }

}
