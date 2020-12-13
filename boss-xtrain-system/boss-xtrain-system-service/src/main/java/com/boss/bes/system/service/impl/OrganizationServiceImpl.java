package com.boss.bes.system.service.impl;



import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.SystemException;
import com.boss.bes.system.dao.OrganizationDao;
import com.boss.bes.system.dto.OrganizationDto;
import com.boss.bes.system.entity.Organization;
import com.boss.bes.system.mapper.OrganizationMapper;
import com.boss.bes.system.query.OrganizationQuery;
import com.boss.bes.system.service.OrganizationService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 组织机构的服务实现类
 * @create 2020-07-10
 * @since 1.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private static final String MODULE_NAME = "ORGANIZATION";
    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);

    @Resource
    private OrganizationDao organizationDao;
    private OrganizationMapper organizationMapper;

    @Override
    public Integer save(OrganizationDto organizationDto) throws SystemException{
        try{
            //将dto转换成entity
            Organization organization = new Organization(organizationDto);
            Long id = this.snowflakeWorker.nextId();
            organization.setId(id);
            organization.setCreatedTime(new Date(System.currentTimeMillis()));
            organization.setVersion(1L);
            return organizationDao.save(organization);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210501","ORGANIZATION_INSERT_FAILURE");
        }
    }

    @Override
    public Integer remove(OrganizationDto organizationDto) throws SystemException{
        try{
            //将dto转换成entity
            Organization organization = new Organization(organizationDto);
            return organizationDao.remove(organization);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210502","ORGANIZATION_DELETE_FAILURE");
        }
    }

    @Override
    public Integer update(OrganizationDto organizationDto) throws SystemException{
        try{
            //将dto转换成entity
            Organization organization = new Organization(organizationDto);
            organization.setUpdatedTime(new Date(System.currentTimeMillis()));
            Long newVersion = organization.getVersion() + 1;
            organization.setVersion(newVersion);
            return organizationDao.update(organization);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210504","ORGANIZATION_UPDATE_FAILURE");
        }
    }

    @Override
    public List<Organization> query(OrganizationQuery organizationQuery) throws SystemException{
        try{
            return organizationDao.queryByCondition(organizationQuery);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210503","ORGANIZATION_QUERY_FAILURE");
        }
    }

    @Override
    public String selectNameByPrimaryKey(Long id)throws SystemException{
        try{
            return organizationDao.selectNameByPrimaryKey(id);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210503","ORGANIZATION_QUERY_FAILURE");
        }
    }

    @Override
    public List<Organization> listAll() throws SystemException{
        try{
            OrganizationQuery organizationQuery = new OrganizationQuery();
            return organizationDao.queryByCondition(organizationQuery);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210503","ORGANIZATION_QUERY_FAILURE");
        }
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        return organizationDao.batchRemove(ids);
    }
}
