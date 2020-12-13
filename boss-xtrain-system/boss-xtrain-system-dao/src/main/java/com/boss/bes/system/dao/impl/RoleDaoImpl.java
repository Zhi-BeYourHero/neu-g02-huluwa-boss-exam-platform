package com.boss.bes.system.dao.impl;

import com.boss.bes.system.dao.RoleDao;
import com.boss.bes.system.entity.Role;
import com.boss.bes.system.mapper.RoleMapper;
import com.boss.bes.system.mapper.RoleResourceMapper;
import com.boss.bes.system.query.RoleQuery;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的Dao类
 * @create 2020-07-10 20:01
 * @since 1.0
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleResourceMapper roleResourceMapper;

    /**
     * 保存新建的角色信息
     *
     * @param role 角色的对象
     * @return 修改的行数
     */
    @Override
    public Integer save(Role role) {
        return roleMapper.insert(role);
    }

    /**
     * 删除角色信息
     *
     * @param id 角色的id
     * @return 修改的行数
     */
    @Override
    public Integer remove(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新角色信息
     *
     * @param role 角色的对象
     * @return 修改的行数
     */
    @Override
    public Integer update(Role role) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",role.getId());
        return roleMapper.updateByExample(role,example);
    }

    @Override
    public List<Role> queryByCondition(RoleQuery roleQuery){
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",roleQuery.getId());
        criteria.andEqualTo("companyId",roleQuery.getCompanyId());
        criteria.andEqualTo("organizationId",roleQuery.getOrganizationId());
        criteria.andEqualTo("name",roleQuery.getName());
        criteria.andEqualTo("code",roleQuery.getCode());
        criteria.andEqualTo("remark",roleQuery.getRemark());
        criteria.andEqualTo("createdBy",roleQuery.getCreatedBy());
        criteria.andEqualTo("updatedBy",roleQuery.getUpdatedBy());
        criteria.andEqualTo("version",roleQuery.getVersion());
        criteria.andEqualTo("status",roleQuery.getStatus());
        return roleMapper.selectByExample(example);
    }

    /**
     * 通过id获取角色对象
     *
     * @param id 角色Id
     * @return 角色对象
     */
    @Override
    public Role getRoleById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除角色
     *
     * @param ids 选中的角色id
     * @return 影响的行数
     */
    @Override
    public Integer batchRemove(Long[] ids) {
        int count = 0;
        for(Long id: ids){
            count += this.roleMapper.deleteByPrimaryKey(id);
            this.roleResourceMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}