package com.boss.bes.basedata.dao.impl;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.basedata.dao.CategoryDao;
import com.boss.bes.basedata.dao.categorytree.CategoryTreeList;
import com.boss.bes.basedata.dao.categorytree.CategoryTreeNode;
import com.boss.bes.basedata.pojo.dto.category.*;
import com.boss.bes.basedata.pojo.entity.Category;
import com.boss.bes.basedata.mapper.CategoryMapper;
import boss.xtrain.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.*;

@Component
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public int add(InsertCategoryDTO addCategoryDTO) {
        Category category = BeanUtil.copyProperties(addCategoryDTO, Category.class);
        assert category != null;
        FillPublicField.fillPublicField(category);
        category.setCategoryId(Long.valueOf(SnowFlakeUtil.getId()));
        return categoryMapper.insertSelective(category);
    }
    @Override
    public CategoryDTO update(UpdateCategoryDTO updateCategoryDTO) {
        updateCategoryDTO.setVersion(updateCategoryDTO.getVersion()+1L);
        Category category = BeanUtil.copyProperties(updateCategoryDTO, Category.class);
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId", updateCategoryDTO.getCategoryId());
        int update = categoryMapper.updateByExampleSelective(category, example);
        if (update == 0) {
            throw new BusinessException("200204", "修改类别信息失败！");
        }
        assert category != null;
        Category category1 = categoryMapper.selectByPrimaryKey(category.getCategoryId());
        return BeanUtil.copyProperties(category1, CategoryDTO.class);
    }
    @Override
    public int delete(DeleteCategoryDTO deleteCategoryDTO) {
        Category category = BeanUtil.copyProperties(deleteCategoryDTO, Category.class);
        return categoryMapper.deleteByPrimaryKey(category);
    }
    @Override
    public List<CategoryDTO> query() {
        return categoryMapper.query();
    }
    @Override
    public List<CategoryDTO> queryByCondition(CategoryQueryConditionDTO categoryQueryConditionDTO) {
        return  categoryMapper.queryByCondition((categoryQueryConditionDTO));
    }
    @Override
    public List<CategoryListDTO> getCategorys(CategoryListConditionDTO categoryListConditionDTO) {
        return categoryMapper.getCategorys(categoryListConditionDTO);
    }
    @Override
    public List<Category> getCategoryName(Long id){
        Example example = new Example(Category.class);
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",id);
        return categoryMapper.selectByExample(example);
    }
    @Override
    public CategoryTreeList getCategoryTree(Long orgId) {
        //选择符合组织id的题目类别
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orgId", orgId);
        List<Category> categories = categoryMapper.selectByExample(example);
        Map<Long, CategoryTreeNode> nodes = new HashMap<>();
        CategoryTreeList treeList = new CategoryTreeList();
        for(Category c: categories){
            //得到父节点和新节点
            CategoryTreeNode parent = nodes.get(c.getParentId());
            CategoryTreeNode node = new CategoryTreeNode();
            node.setCategoryId(c.getCategoryId());
            node.setName(c.getName());
            //父节点不为空则放到父节点下
            if(parent != null){
                parent.addChildren(node);
            }
            //否则放到树下
            else {
                treeList.addTreeNode(node);
            }
            nodes.put(orgId,node);
        }
        return treeList;
    }
}
