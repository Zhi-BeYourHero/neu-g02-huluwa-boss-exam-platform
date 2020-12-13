package com.boss.bes.basedata.service;

import boss.xtrain.core.data.convention.common.CommonPage;
import com.boss.bes.basedata.dao.categorytree.CategoryTreeList;
import com.boss.bes.basedata.pojo.entity.Category;
import com.boss.bes.basedata.pojo.vo.category.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService{
    /**
     * 增加题目类别
     * @param object
     * @return
     */
    int add(InsertCategoryVO object);
    /**
     * 更新题目类别
     * @param object
     * @return
     */
    CategoryVO update(UpdateCategoryVO object);
    /**
     * 删除题目类别
     * @param object
     * @return
     */
    int delete(DeleteCategoryVO object);
    /**
     * 多次删除题目类别
     * @param object
     * @return
     */
    int batchRemove(DeleteCategorysVO object);
    /**
     * 获
     * @param object
     * @return
     */
    CommonPage<CategoryVO> queryByCondition(CategoryQueryConditionVO object);
    /**
     * 试卷获取题目类别列表
     * @param object
     * @return
     */
    List<CategoryListVO> getCategorys(CategoryListConditionVO object);
    /**
     * 获取题目类别树
     * @param orgId
     * @return
     */
    public CategoryTreeList getCategoryTree(Long orgId);
    List<Category> getCategoryName(Long id);
}
