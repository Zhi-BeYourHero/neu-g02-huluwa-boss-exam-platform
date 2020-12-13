package com.boss.bes.paper.dao.mapper;

import com.boss.bes.paper.pojo.dto.exam.PaperCompanyListDTO;
import com.boss.bes.paper.pojo.entity.Paper;
import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-dao
 * @Description: 试卷mapper类
 * @Date: 2020/7/7 14:25
 * @since: 1.0
 */

@Mapper
public interface PaperMapper extends CommonMapper<Paper> {
    List<PaperCompanyListDTO> getAllIds();

}
