package com.boss.bes.system.dao.impl;

import com.boss.bes.system.BeanCopierUtil;
import com.boss.bes.system.dao.ResourceDao;
import com.boss.bes.system.dto.ResourceDto;
import com.boss.bes.system.mapper.ResourceMapper;
import com.boss.bes.system.query.ResourceQuery;
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
public class ResourceDaoImpl implements ResourceDao {
    @Resource
    private ResourceMapper resourceMapper;

    @Override
    public Integer save(com.boss.bes.system.entity.Resource resource) {
        return resourceMapper.insert(resource);
    }

    @Override
    public Integer remove(com.boss.bes.system.entity.Resource resource) {
        return resourceMapper.delete(resource);
    }

    @Override
    public Integer update(com.boss.bes.system.entity.Resource resource) {
        Example example = new Example(com.boss.bes.system.entity.Resource.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",resource.getId());
        return resourceMapper.updateByExampleSelective(resource,example);
    }

    @Override
    public List<com.boss.bes.system.entity.Resource> queryByCondition(ResourceQuery query) {
        Example example = new Example(com.boss.bes.system.entity.Resource.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",query.getId());
        criteria.andEqualTo("tenantName",query.getTenantName());
        criteria.andEqualTo("code",query.getCode());
        criteria.andEqualTo("orderIndex",query.getOrderIndex());
        criteria.andEqualTo("parentId",query.getParentId());
        criteria.andEqualTo("url",query.getUrl());
        criteria.andEqualTo("openImg",query.getOpenImg());
        criteria.andEqualTo("closeImg",query.getCloseImg());
        criteria.andEqualTo("resourceType",query.getResourceType());
        criteria.andEqualTo("leaf",query.getLeaf());
        criteria.andEqualTo("remark",query.getRemark());
        criteria.andEqualTo("action",query.getAction());
        criteria.andEqualTo("createdBy",query.getCreatedBy());
        criteria.andEqualTo("updatedBy",query.getUpdatedBy());
        criteria.andEqualTo("status",query.getStatus());
        criteria.andEqualTo("version",query.getVersion());
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<ResourceDto> queryResource(Example example) {
        List<com.boss.bes.system.entity.Resource> tResources = resourceMapper.selectByExample(example);
        return BeanCopierUtil.copyPropertiesOfList(tResources, ResourceDto.class);
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        int count = 0;
        for(Long id: ids){
            count += this.resourceMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}