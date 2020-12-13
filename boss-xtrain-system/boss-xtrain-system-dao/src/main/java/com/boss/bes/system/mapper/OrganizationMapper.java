package com.boss.bes.system.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.system.entity.Organization;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrganizationMapper extends CommonMapper<Organization> {
    Organization selectNameByPrimaryKey(Long id);
}