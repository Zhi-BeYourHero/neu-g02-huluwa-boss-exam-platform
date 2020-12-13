package com.boss.bes.basedata.dao.impl;
import com.boss.bes.basedata.dao.CombExamConfigItemDao;
import com.boss.bes.basedata.mapper.CombExamConfigItemMapper;
import com.boss.bes.basedata.pojo.dto.combexamconfig.DeleteCombExamConfigDTO;
import com.boss.bes.basedata.pojo.entity.CombExamConfigItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Component
public class CombExamConfigItemDaoImpl implements CombExamConfigItemDao {
    @Autowired
    private CombExamConfigItemMapper combExamConfigItemMapper;
    @Override
    public int save(List<CombExamConfigItem> itemList) {
        return combExamConfigItemMapper.insertBatch(itemList);
    }

    @Override
    public int update(List<CombExamConfigItem> itemList) {
        return combExamConfigItemMapper.updateBatch(itemList);
    }

    @Override
    public void delete(DeleteCombExamConfigDTO c) {
        Example example = new Example(CombExamConfigItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("combExamId",c.getCombExamId());
        combExamConfigItemMapper.deleteByExample(example);
    }

    @Override
    public void deleteByIds(List<Long> deleteItemIds) {
        Example example = new Example(CombExamConfigItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("combExamConfigItemId",deleteItemIds);
        combExamConfigItemMapper.deleteByExample(example);
    }
    @Override
    public void deleteByConfigIds(List<Long> idList) {
        Example example = new Example(CombExamConfigItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("combExamId",idList);
        combExamConfigItemMapper.deleteByExample(example);
    }
}