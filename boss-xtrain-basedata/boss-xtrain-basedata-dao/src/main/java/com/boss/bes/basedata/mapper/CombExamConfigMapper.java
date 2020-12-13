package com.boss.bes.basedata.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.basedata.pojo.dto.combexamconfig.*;
import com.boss.bes.basedata.pojo.entity.CombExamConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombExamConfigMapper extends CommonMapper<CombExamConfig> {
    /**
     * 按条件查询组卷配置
     * @param combExamConfigQueryConditionDTO
     * @return
     */
    List<CombExamConfigDataItemDTO> queryByCondition(CombExamConfigQueryConditionDTO combExamConfigQueryConditionDTO);
    /**
     * 试卷服务获取组卷配置列表
     * @param combExamConfigListConditionDTO
     * @return
     */
    List<CombExamConfigListDTO> getConfigList(CombExamConfigListConditionDTO combExamConfigListConditionDTO);
    /**
     * 按id查询题目
     * @param deleteCombExamConfigDTO
     * @return
     */
    CombExamConfigDataItemDTO queryById(DeleteCombExamConfigDTO deleteCombExamConfigDTO);
}