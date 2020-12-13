package com.boss.bes.system.dao.impl;

import com.boss.bes.system.dao.CompanyDao;
import com.boss.bes.system.entity.Company;
import com.boss.bes.system.mapper.CompanyMapper;
import com.boss.bes.system.query.CompanyQuery;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-14
 * @since 1.0
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {
    @Resource
    private CompanyMapper  companyMapper;

    @Override
    public String selectNameByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Integer save(Company company) {
        return companyMapper.insert(company);
    }

    @Override
    public Integer remove(Company company) {
        return companyMapper.delete(company);
    }

    @Override
    public Integer update(Company company) {
        Example example = new Example(Company.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",company.getId());
        return companyMapper.updateByExampleSelective(company,example);
    }

    @Override
    public List<Company> queryByCondition(CompanyQuery query) {
        Example example = new Example(Company.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",query.getId());
        criteria.andEqualTo("organizationId",query.getOrganizationId());
        criteria.andEqualTo("name",query.getName());
        criteria.andEqualTo("code",query.getCode());
        criteria.andEqualTo("mnemonicCode",query.getMnemonicCode());
        criteria.andEqualTo("master",query.getMaster());
        criteria.andEqualTo("tax",query.getTax());
        criteria.andEqualTo("fax",query.getFax());
        criteria.andEqualTo("tel",query.getTel());
        criteria.andEqualTo("address",query.getAddress());
        criteria.andEqualTo("email",query.getEmail());
        criteria.andEqualTo("website",query.getWebsite());
        criteria.andEqualTo("createdBy",query.getCreatedBy());
        criteria.andEqualTo("updatedBy",query.getUpdatedBy());
        criteria.andEqualTo("status",query.getStatus());
        criteria.andEqualTo("version",query.getVersion());
        return companyMapper.selectByExample(example);
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        int count = 0;
        for(Long id: ids){
            count += this.companyMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}
