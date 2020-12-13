package com.boss.bes.system.dao.impl;

import com.boss.bes.system.dao.OrganizationDao;
import com.boss.bes.system.entity.Organization;
import com.boss.bes.system.mapper.OrganizationMapper;
import com.boss.bes.system.query.OrganizationQuery;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-12
 * @since 1.0
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public String selectNameByPrimaryKey(Long id) {
        return organizationMapper.selectNameByPrimaryKey(id).getName();
    }

    @Override
    public Integer save(Organization organization) {
        return organizationMapper.insert(organization);
    }


    @Override
    public Integer remove(Organization organization) {
        return organizationMapper.delete(organization);
    }


    @Override
    public Integer update(Organization organization) {
        Example example = new Example(Organization.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",organization.getId());
        return organizationMapper.updateByExampleSelective(organization,example);
    }

    @Override
    public List<Organization> queryByCondition(OrganizationQuery query) {
        Example example = new Example(Organization.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",query.getId());
        criteria.andEqualTo("name",query.getName());
        criteria.andEqualTo("code",query.getCode());
        criteria.andEqualTo("master",query.getMaster());
        criteria.andEqualTo("name",query.getName());
        criteria.andEqualTo("tel",query.getTel());
        criteria.andEqualTo("address",query.getAddress());
        criteria.andEqualTo("createdBy",query.getCreatedBy());
        criteria.andEqualTo("updatedBy",query.getUpdatedBy());
        criteria.andEqualTo("status",query.getStatus());
        criteria.andEqualTo("version",query.getVersion());
        return organizationMapper.selectByExample(example);
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        int count = 0;
        for(Long id: ids){
            count += this.organizationMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}
