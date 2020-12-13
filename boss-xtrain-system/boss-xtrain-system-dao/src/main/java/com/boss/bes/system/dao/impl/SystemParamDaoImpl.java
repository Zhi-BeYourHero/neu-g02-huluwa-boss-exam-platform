package com.boss.bes.system.dao.impl;

import com.boss.bes.system.dao.SystemParamDao;
import com.boss.bes.system.entity.SystemParam;
import com.boss.bes.system.mapper.SystemParamMapper;
import com.boss.bes.system.query.SystemParamQuery;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统参数dao的实现类
 * @create 2020-07-09 10:56
 * @since 1.0
 */
@Repository
public class SystemParamDaoImpl implements SystemParamDao {

    @Resource
    private SystemParamMapper systemParamMapper;


    /**
     * 保存新建的系统参数
     *
     * @param systemParam 系统参数的对象
     * @return 修改的行数
     */
    @Override
    public Integer save(SystemParam systemParam) {
        return systemParamMapper.insert(systemParam);
    }

    /**
     * 删除系统参数
     *
     * @param systemParam 系统参数的对象
     * @return 修改的行数
     */
    @Override
    public Integer remove(SystemParam systemParam) {
        return systemParamMapper.delete(systemParam);
    }

    /**
     * 更新系统参数
     *
     * @param systemParam 系统参数的对象
     * @return 修改的行数
     */
    @Override
    public Integer update(SystemParam systemParam) {
        Example example = new Example(SystemParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",systemParam.getId());
        return systemParamMapper.updateByExampleSelective(systemParam,example);
    }

    @Override
    public List<SystemParam> queryByCondition(SystemParamQuery query) {
        Example example = new Example(SystemParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",query.getId());
        criteria.andEqualTo("paramType",query.getParamType());
        criteria.andEqualTo("param",query.getParam());
        criteria.andEqualTo("value",query.getValue());
        criteria.andEqualTo("createdBy",query.getCreatedBy());
        criteria.andEqualTo("updatedBy",query.getUpdatedBy());
        criteria.andEqualTo("version",query.getVersion());
        criteria.andEqualTo("status",query.getStatus());
        return systemParamMapper.selectByExample(example);
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        int count = 0;
        for(Long id: ids){
            count += this.systemParamMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}