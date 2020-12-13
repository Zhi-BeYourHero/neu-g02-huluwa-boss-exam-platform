package com.boss.bes.basedata.service.impl;

import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.basedata.dao.CombExamConfigDao;
import com.boss.bes.basedata.dao.CombExamConfigItemDao;
import com.boss.bes.basedata.mapper.CategoryMapper;
import com.boss.bes.basedata.mapper.SubjectTypeMapper;
import com.boss.bes.basedata.pojo.dto.combexamconfig.*;
import com.boss.bes.basedata.pojo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.basedata.mapper.CombExamConfigItemMapper;
import com.boss.bes.basedata.service.CombExamConfigItemService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombExamConfigItemServiceImpl implements CombExamConfigItemService{

    @Resource
    private CombExamConfigItemMapper combExamConfigItemMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private SubjectTypeMapper subjectTypeMapper;
    @Autowired
    private CombExamConfigDao combExamConfigDao;
    @Autowired
    private CombExamConfigItemDao combExamConfigItemDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addConfigItem(List<CombExamConfigItemDTO> itemList) {
        if(itemList.isEmpty()){
            return false;
        }
        Long combExamConfigId = itemList.get(0).getCombExamId();
        if(combExamConfigId == null){
            return false;
        }
        //删除所有明细
        DeleteCombExamConfigDTO combExamConfigDelDto = new DeleteCombExamConfigDTO();
        combExamConfigDelDto.setCombExamId(combExamConfigId);
        combExamConfigItemDao.delete(combExamConfigDelDto);
        List<CombExamConfigItem> configItemList = new ArrayList<>();
        for(CombExamConfigItemDTO c : itemList){
            CombExamConfigItem combExamConfigItem = BeanUtil.copyProperties(c,CombExamConfigItem.class);
            Example example = new Example(Category.class);
            final Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("name", c.getCategoryName());
            List<Category> category = categoryMapper.selectByExample(example);
            assert combExamConfigItem != null;
            combExamConfigItem.setCategoryId(category.get(0).getCategoryId());
            Example example2 = new Example(SubjectType.class);
            final Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("subjectTypeName", c.getSubjectTypeName());
            List<SubjectType> subjectType = subjectTypeMapper.selectByExample(example2);
            combExamConfigItem.setSubjectTypeId(subjectType.get(0).getSubjectTypeId());
            configItemList.add(combExamConfigItem);
        }
        //插入明细
        for(CombExamConfigItem c : configItemList){
            c.setCombExamConfigItemId(Long.valueOf(SnowFlakeUtil.getId()));
        }
        return combExamConfigItemDao.save(configItemList)==configItemList.size();
    }
}
