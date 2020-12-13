package com.boss.bes.system.service.impl;

import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.SystemException;
import com.boss.bes.system.dao.RoleDao;
import com.boss.bes.system.dto.RoleDto;
import com.boss.bes.system.entity.Role;
import com.boss.bes.system.mapper.RoleMapper;
import boss.xtrain.security.domain.SysRole;
import boss.xtrain.security.util.StringUtils;
import com.boss.bes.system.query.RoleQuery;
import com.boss.bes.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的服务实现类
 * @create 2020-07-10 22:04
 * @since 1.0
 */

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleDao roleDao;

    private static final String MODULE_NAME = "ROLE";

    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);


    /**
     * 保存一个新建的用户对象
     *
     * @param roleDto 角色的DTO对象
     * @return 影响的行数
     */
    @Override
    public Integer save(RoleDto roleDto) {
        try{
            Role role = this.doDtoTransf2Entity(roleDto);
            Long id = this.snowflakeWorker.nextId();
            role.setId(id);
            role.setCreatedTime(new Date(System.currentTimeMillis()));
            role.setVersion(1L);
            role.setUpdatedBy(null);
            role.setUpdatedTime(null);
            return roleDao.save(role);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210701","ROLE_INSERT_FAILURE");
        }
    }

    /**
     * 删除一个指定的角色对象
     *
     * @param id 角色的id
     * @return 影响的行数
     */
    @Override
    public Integer remove(Long id) {
        try{
            return roleDao.remove(id);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210702","ROLE_DELETE_FAILURE");
        }
    }

    /**
     * 更新一个指定的角色对象
     *
     * @param roleDto 角色的Dto对象
     * @return 影响的行数
     */
    @Override
    public Integer update(RoleDto roleDto) {
        try{
            Role role = this.doDtoTransf2Entity(roleDto);
            role.setUpdatedTime(new Date(System.currentTimeMillis()));
            Long newVersion = role.getVersion() + 1;
            role.setVersion(newVersion);
            return roleDao.update(role);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210703","ROLE_UPDATE_FAILURE");
        }
    }

    /**
     * 按条件进行查询
     *
     * @param roleQuery 封装的查询条件
     * @return 查询的返回值
     */
    @Override
    public List<Role> query(RoleQuery roleQuery) {
        try{
            return roleDao.queryByCondition(roleQuery);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210704","ROLE_QUERY_FAILURE");
        }
    }


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.add(perm.getId().toString());
            }
        }
        return permsSet;
    }

    /**
     * 获取所有角色Id
     * @return 所有角色id
     */
    @Override
    public List<Role> listAll(){
        return this.roleMapper.selectAll();
    }

    /**
     * 通过id获取角色
     *
     * @param id 角色id
     * @return 角色对象
     */
    @Override
    public Role getRoleById(Long id) {
        return this.roleDao.getRoleById(id);
    }

    /**
     * 模糊查询
     * @param roleQuery 含有模糊查询写法的角色Query对象
     * @return 查询结果
     */
    @Override
    public List<Role> getRoleByFuzzyName(RoleQuery roleQuery) {
        Example example = new Example(Role.class);
        String name = roleQuery.getName();
        Integer status = roleQuery.getStatus();
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name", "%"+name+"%");
        criteria.andEqualTo("status",status);
        return roleMapper.selectByExample(example);
    }

    /**
     * 批量删除
     *
     * @param ids 选中的角色id
     * @return 影响的函数
     */
    @Override
    public Integer batchRemove(Long[] ids) {
        return this.roleDao.batchRemove(ids);
    }

    /**
     * 子类决定如何做增删改过程中 dto到entity对象转化
     * @param dto DTO对象
     * @return 实体类对象
     */
    protected Role doDtoTransf2Entity(RoleDto dto) {
        return BeanUtil.copyProperties(dto, Role.class);
    }
}
