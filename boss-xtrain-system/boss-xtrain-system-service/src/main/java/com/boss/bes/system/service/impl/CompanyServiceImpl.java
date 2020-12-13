package com.boss.bes.system.service.impl;

import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.SystemException;
import com.boss.bes.system.dao.CompanyDao;
import com.boss.bes.system.dto.CompanyDto;
import com.boss.bes.system.entity.Company;


import com.boss.bes.system.query.CompanyQuery;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.system.service.CompanyService;


import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    private static final String MODULE_NAME = "COMPANY";
    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);
    @Resource
    private CompanyDao companyDao;

    @Override
    public Integer save(CompanyDto companyDto) throws SystemException{
        try{
            //将dto转换成entity
            Company company = new Company(companyDto);
            Long id = this.snowflakeWorker.nextId();
            company.setId(id);
            company.setCreatedTime(new Date(System.currentTimeMillis()));
            company.setVersion(1L);
            return companyDao.save(company);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210101","COMPANY_INSERT_FAILURE");
        }
    }

    @Override
    public Integer remove(CompanyDto companyDto) throws SystemException{
        try{
            //将dto转换成entity
            Company company = new Company(companyDto);
            return companyDao.remove(company);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210102","COMPANY_DELETE_FAILURE");
        }
    }

    @Override
    public Integer update(CompanyDto companyDto) throws SystemException{
        try{
            //将dto转换成entity
            Company company = new Company(companyDto);
            company.setUpdatedTime(new Date(System.currentTimeMillis()));
            Long newVersion = company.getVersion() + 1;
            company.setVersion(newVersion);
            return companyDao.update(company);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210104","COMPANY_UPDATE_FAILURE");
        }
    }

    @Override
    public List<Company> query(CompanyQuery companyQuery) throws SystemException{
        try{
            return companyDao.queryByCondition(companyQuery);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210103","COMPANY_QUERY_FAILURE");
        }
    }

    @Override
    public String selectNameByPrimaryKey(Long id) throws SystemException{
        try{
            return companyDao.selectNameByPrimaryKey(id);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210103","COMPANY_QUERY_FAILURE");
        }
    }

    @Override
    public List<Company> selectByOrganizationId(Long id) {
        CompanyQuery companyQuery = new CompanyQuery();
        companyQuery.setOrganizationId(id);
        return companyDao.queryByCondition(companyQuery);
    }

    @Override
    public List<Company> listAll() throws SystemException{
        try {
            CompanyQuery companyQuery = new CompanyQuery();
            return companyDao.queryByCondition(companyQuery);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210103","COMPANY_QUERY_FAILURE");
        }
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        return companyDao.batchRemove(ids);
    }
}
