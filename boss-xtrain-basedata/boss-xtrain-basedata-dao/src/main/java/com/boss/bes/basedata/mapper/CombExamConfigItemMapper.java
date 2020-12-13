package com.boss.bes.basedata.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemListConditionDTO;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemListDTO;
import com.boss.bes.basedata.pojo.entity.CombExamConfigItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombExamConfigItemMapper extends CommonMapper<CombExamConfigItem> {
    /**
     * 通过题目类别名称找寻题目ID
     * @param categoryName
     * @return
     */
    Long findCategoryIdByCategoryName(String categoryName);
    /**
     * 通过题目类型名称找寻题目ID
     * @param subjectTypeName
     * @return
     */
    Long findSubjectTypeIdBySubjectTypeName(String subjectTypeName);
    /**
     * 删除组卷配置明细项
     * @param id
     * @return
     */
    int deleteCombExamConfigItem(Long id);
    /**
     * 查询组卷配置列表
     * @param combExamConfigItemListConditionDTO
     * @return
     */
    List<CombExamConfigItemListDTO> getConfigItem(CombExamConfigItemListConditionDTO combExamConfigItemListConditionDTO);
    /**
     * 批量插入配置明细
     * @param itemList
     * @return
     */
    int insertBatch(@Param("itemList") List<CombExamConfigItem> itemList);

    /**
     * 批量更新配置明细
     * @param itemList
     * @return
     */
    int updateBatch(@Param("itemList") List<CombExamConfigItem> itemList);
}