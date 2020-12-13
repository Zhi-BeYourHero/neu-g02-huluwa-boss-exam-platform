package com.boss.bes.system.service.impl;

import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.SystemException;
import com.boss.bes.system.dao.DepartmentDao;
import com.boss.bes.system.dto.DepartmentDto;

import com.boss.bes.system.entity.Department;

import com.boss.bes.system.query.DepartmentQuery;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.system.service.DepartmentService;

import java.util.Date;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private static final String MODULE_NAME = "DEPARTMENT";
    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);
    @Resource
    private DepartmentDao departmentDao;

    @Override
    public Integer save(DepartmentDto departmentDto) throws SystemException{
        try{
            //将dto转换成entity
            Department department = new Department(departmentDto);
            Long id = this.snowflakeWorker.nextId();
            department.setId(id);
            department.setCreatedTime(new Date(System.currentTimeMillis()));
            department.setVersion(1L);
            return departmentDao.save(department);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210201","DEPARTMENT_INSERT_FAILURE");
        }
    }

    @Override
    public Integer remove(DepartmentDto departmentDto) throws SystemException{
        try{
            //将dto转换成entity
            Department department = new Department(departmentDto);
            return departmentDao.remove(department);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210202","DEPARTMENT_DELETE_FAILURE");
        }
    }

    @Override
    public Integer update(DepartmentDto departmentDto) throws SystemException{
        try{
            //将dto转换成entity
            Department department = new Department(departmentDto);
            department.setUpdatedTime(new Date(System.currentTimeMillis()));
            Long newVersion = department.getVersion() + 1;
            department.setVersion(newVersion);
            return departmentDao.update(department);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210204","DEPARTMENT_UPDATE_FAILURE");
        }
    }

    @Override
    public List<Department> query(DepartmentQuery departmentQuery) throws SystemException{
        try{
            return departmentDao.queryByCondition(departmentQuery);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210203","DEPARTMENT_QUERY_FAILURE");
        }
    }

    @Override
    public List<Department> listAll() throws SystemException{
        try{
            DepartmentQuery departmentQuery = new DepartmentQuery();
            return departmentDao.queryByCondition(departmentQuery);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210203","DEPARTMENT_QUERY_FAILURE");
        }
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        return departmentDao.batchRemove(ids);
    }
}
