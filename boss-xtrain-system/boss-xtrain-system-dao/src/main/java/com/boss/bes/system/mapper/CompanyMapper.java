package com.boss.bes.system.mapper;


import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.system.entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper extends CommonMapper<Company> {
    Company selectNameByPrimaryKey(Long id);
}