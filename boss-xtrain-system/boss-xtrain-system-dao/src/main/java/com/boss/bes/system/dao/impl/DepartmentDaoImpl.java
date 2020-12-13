package com.boss.bes.system.dao.impl;

import com.boss.bes.system.dao.DepartmentDao;
import com.boss.bes.system.entity.Department;
import com.boss.bes.system.mapper.DepartmentMapper;
import com.boss.bes.system.query.DepartmentQuery;
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
public class DepartmentDaoImpl implements DepartmentDao {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Integer save(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public Integer remove(Department department) {
        return departmentMapper.delete(department);
    }

    @Override
    public Integer update(Department department) {
        Example example = new Example(Department.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",department.getId());
        return departmentMapper.updateByExampleSelective(department,example);
    }

    @Override
    public List<Department> queryByCondition(DepartmentQuery query) {
        Example example = new Example(Department.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",query.getId());
        criteria.andEqualTo("companyId",query.getCompanyId());
        criteria.andEqualTo("name",query.getName());
        criteria.andEqualTo("mnemonicCode",query.getMnemonicCode());
        criteria.andEqualTo("code",query.getCode());
        criteria.andEqualTo("level",query.getLevel());
        criteria.andEqualTo("parentId",query.getParentId());
        criteria.andEqualTo("master",query.getMaster());
        criteria.andEqualTo("createdBy",query.getCreatedBy());
        criteria.andEqualTo("updatedBy",query.getUpdatedBy());
        criteria.andEqualTo("status",query.getStatus());
        criteria.andEqualTo("version",query.getVersion());
        return departmentMapper.selectByExample(example);
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        int count = 0;
        for(Long id: ids){
            count += this.departmentMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}
