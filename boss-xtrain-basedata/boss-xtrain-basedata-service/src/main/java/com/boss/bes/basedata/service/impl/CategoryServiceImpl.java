package com.boss.bes.basedata.service.impl;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.basedata.dao.CategoryDao;
import com.boss.bes.basedata.dao.categorytree.CategoryTreeList;
import com.boss.bes.basedata.pojo.dto.category.*;
import com.boss.bes.basedata.pojo.entity.Category;
import com.boss.bes.basedata.pojo.vo.category.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.basedata.mapper.CategoryMapper;
import com.boss.bes.basedata.service.CategoryService;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Resource
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryDao categoryDaoImpl;
    @Override
    public int add(InsertCategoryVO object){
        InsertCategoryDTO addCategoryDTO = BeanUtil.copyProperties(object, InsertCategoryDTO.class);
        return categoryDaoImpl.add(addCategoryDTO);
    }
    @Override
    public CategoryVO update(UpdateCategoryVO object) {
        UpdateCategoryDTO updateCategoryDTO =  BeanUtil.copyProperties(object, UpdateCategoryDTO.class);
        return BeanUtil.copyProperties(categoryDaoImpl.update(updateCategoryDTO), CategoryVO.class);
    }
    @Override
    public int delete(DeleteCategoryVO object) {
        DeleteCategoryDTO deleteCategoryDTO =  BeanUtil.copyProperties(object, DeleteCategoryDTO.class);
        return categoryDaoImpl.delete(deleteCategoryDTO);
    }
    @Override
    public int batchRemove(DeleteCategorysVO object) {
        int count = 0;
        int result = 0;
        String string = object.getIds();
        String[] strs = string.split(",");
        for(String str:strs){
            DeleteCategoryDTO deleteCategoryDTO = new DeleteCategoryDTO();
            deleteCategoryDTO.setCategoryId(Long.parseLong(str));
            count += categoryDaoImpl.delete(deleteCategoryDTO);
            result++;
        }
        return count < result? 0:1;
    }
    @Override
    public CommonPage<CategoryVO> queryByCondition(CategoryQueryConditionVO object) {
        CategoryQueryConditionDTO categoryQueryConditionDTO =  BeanUtil.copyProperties(object, CategoryQueryConditionDTO.class);
        List<CategoryDTO> categoryDtos = categoryDaoImpl.queryByCondition(categoryQueryConditionDTO);
        return BeanUtil.copyProperties(restPage(categoryDtos), CommonPage.class);
    }

    @Override
    public List<CategoryListVO> getCategorys(CategoryListConditionVO object) {
        CategoryListConditionDTO categoryListConditionDTO = BeanUtil.copyProperties(object, CategoryListConditionDTO.class);
        List<CategoryListDTO> categoryListDtos = categoryDaoImpl.getCategorys(categoryListConditionDTO);
        List<CategoryListVO> categoryListVos = new ArrayList<>();
        for (CategoryListDTO categoryListDTO: categoryListDtos){
            CategoryListVO categoryListVO =  BeanUtil.copyProperties(categoryListDTO, CategoryListVO.class);
            categoryListVos.add(categoryListVO);
        }
        return categoryListVos;
    }
    @Override
    public List<Category> getCategoryName(Long id){
        return categoryDaoImpl.getCategoryName(id);
    }
    @Override
    public CategoryTreeList getCategoryTree(Long orgId) {
        return categoryDaoImpl.getCategoryTree(orgId);
    }
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setPages(pageInfo.getPages());
        result.setPageIndex(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        return result;
    }
}
