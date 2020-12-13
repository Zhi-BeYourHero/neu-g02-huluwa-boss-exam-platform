package com.boss.bes.basedata.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.basedata.pojo.dto.category.CategoryDTO;
import com.boss.bes.basedata.pojo.dto.category.CategoryListConditionDTO;
import com.boss.bes.basedata.pojo.dto.category.CategoryListDTO;
import com.boss.bes.basedata.pojo.dto.category.CategoryQueryConditionDTO;
import com.boss.bes.basedata.pojo.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends CommonMapper<Category> {
    /**
     * 返回查询的全部题目类别
     * @return
     */
    List<CategoryDTO> query();
    /**
     * 按指定条件返回查询题目类别
     * @param categoryQueryConditionDTO
     * @return
     */
    List<CategoryDTO> queryByCondition(CategoryQueryConditionDTO categoryQueryConditionDTO);
    /**
     * 返回题类别列表
     * @param categoryListConditionDTO
     * @return
     */
    List<CategoryListDTO> getCategorys(CategoryListConditionDTO categoryListConditionDTO);
}