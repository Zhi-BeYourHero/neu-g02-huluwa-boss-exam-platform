package com.boss.bes.basedata.dao;
import com.boss.bes.basedata.dao.categorytree.CategoryTreeList;
import com.boss.bes.basedata.pojo.dto.category.*;
import com.boss.bes.basedata.pojo.entity.Category;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface CategoryDao {
    /**
     * 增加题目类别
     * @param addCategoryDTO
     * @return 1成功，0失败
     */
    int add(InsertCategoryDTO addCategoryDTO);
    /**
     * 更新题目类别
     * @param updateCategoryDTO
     * @return 更新的题目类别参数
     */
    CategoryDTO update(UpdateCategoryDTO updateCategoryDTO);
    /**
     * 删除题目类别
     * @param deleteCategoryDTO
     * @return 删除题目类别
     */
    int delete(DeleteCategoryDTO deleteCategoryDTO);
    /**
     * 返回题目类别列表
     * @return
     */
    List<CategoryDTO> query();
    /**
     * 按条件返回题目类别列表
     * @param categoryQueryConditionDTO
     * @return
     */
    List<CategoryDTO> queryByCondition(CategoryQueryConditionDTO categoryQueryConditionDTO);
    /**
     * 试卷服务：获取题目类别列表
     * @param categoryListConditionDTO
     * @return
     */
    List<CategoryListDTO> getCategorys(CategoryListConditionDTO categoryListConditionDTO);
    /**
     * 获取题目类别树
     * @param orgId
     * @return
     */
    public CategoryTreeList getCategoryTree(Long orgId);
    /**
     * 根据id获取题目类别名称
     * @return
     */
    List<Category> getCategoryName(Long id);
}
