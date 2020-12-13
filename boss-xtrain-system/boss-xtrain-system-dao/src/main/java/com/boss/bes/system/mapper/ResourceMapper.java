package com.boss.bes.system.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.system.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper extends CommonMapper<Resource> {
    List<String> selectResourcePermsByUserId(Long userId);
}