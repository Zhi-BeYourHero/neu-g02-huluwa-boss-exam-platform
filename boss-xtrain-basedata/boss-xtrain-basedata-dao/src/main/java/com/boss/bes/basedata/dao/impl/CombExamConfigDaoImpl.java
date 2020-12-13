package com.boss.bes.basedata.dao.impl;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.basedata.dao.CombExamConfigDao;
import com.boss.bes.basedata.pojo.dto.combexamconfig.*;
import com.boss.bes.basedata.pojo.entity.CombExamConfig;
import com.boss.bes.basedata.pojo.entity.CombExamConfigItem;
import com.boss.bes.basedata.mapper.CombExamConfigItemMapper;
import com.boss.bes.basedata.mapper.CombExamConfigMapper;
import boss.xtrain.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class CombExamConfigDaoImpl implements CombExamConfigDao {
    @Autowired
    private CombExamConfigMapper combExamConfigMapper;
    @Autowired
    private CombExamConfigItemMapper combExamConfigItemMapper;
    @Override
    public List<CombExamConfigDataItemDTO> queryByCondition(CombExamConfigQueryConditionDTO combExamConfigQueryConditionDTO) {
        return combExamConfigMapper.queryByCondition((combExamConfigQueryConditionDTO));
    }
    @Override
    public int add(InsertCombExamConfigDTO addCombExamConfigDTO) {
        CombExamConfig combExamConfig = BeanUtil.copyProperties(addCombExamConfigDTO, CombExamConfig.class);
        assert combExamConfig != null;
        combExamConfig.setVersion(1L);
        int result = combExamConfigMapper.insertSelective(combExamConfig);
        List<CombExamConfigItemDTO> configItems = addCombExamConfigDTO.getConfigItems();
        for(CombExamConfigItemDTO configItem : configItems){
            SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
            long nid=snowFlakeUtil.nextId();
            CombExamConfigItem combExamConfigItem = BeanUtil.copyProperties(configItem,CombExamConfigItem.class);
            assert combExamConfigItem != null;
            combExamConfigItem.setCombExamConfigItemId(nid);
            combExamConfigItem.setCombExamId(addCombExamConfigDTO.getCombExamId());
            combExamConfigItem.setCategoryId(combExamConfigItemMapper.findCategoryIdByCategoryName(configItem.getCategoryName()));
            combExamConfigItem.setSubjectTypeId(combExamConfigItemMapper.findSubjectTypeIdBySubjectTypeName(configItem.getSubjectTypeName()));
            combExamConfigItemMapper.insertSelective(combExamConfigItem);
        }
        return result;
    }
    @Override
    public int delete(DeleteCombExamConfigDTO deleteCombExamConfigDTO) {
        CombExamConfig combExamConfig = BeanUtil.copyProperties(deleteCombExamConfigDTO, CombExamConfig.class);
        assert combExamConfig != null;
        combExamConfigItemMapper.deleteCombExamConfigItem(combExamConfig.getCombExamId());
        return combExamConfigMapper.deleteByPrimaryKey(combExamConfig);
    }
    @Override
    public CombExamConfigDataItemDTO update(UpdateCombExamConfigDTO updateCombExamConfigDTO) {
        updateCombExamConfigDTO.setVersion(updateCombExamConfigDTO.getVersion()+1L);
        CombExamConfig combExamConfig = BeanUtil.copyProperties(updateCombExamConfigDTO, CombExamConfig.class);
        Example example = new Example(CombExamConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("combExamId", updateCombExamConfigDTO.getCombExamId());
        int update = combExamConfigMapper.updateByExampleSelective(combExamConfig, example);
        if (update == 0) {
            throw new BusinessException("200504", "修改配置信息失败!");
        }
        List<CombExamConfigItemDTO> configItems = updateCombExamConfigDTO.getConfigItems();
        for(CombExamConfigItemDTO configItem : configItems){
            CombExamConfigItem combExamConfigItem = BeanUtil.copyProperties(configItem,CombExamConfigItem.class);
            assert combExamConfigItem != null;
            combExamConfigItem.setCombExamId(updateCombExamConfigDTO.getCombExamId());
            combExamConfigItem.setCategoryId(combExamConfigItemMapper.findCategoryIdByCategoryName(configItem.getCategoryName()));
            combExamConfigItem.setSubjectTypeId(combExamConfigItemMapper.findSubjectTypeIdBySubjectTypeName(configItem.getSubjectTypeName()));
            combExamConfigItemMapper.updateByPrimaryKeySelective(combExamConfigItem);
        }
        assert combExamConfig != null;
        CombExamConfig combExamConfig1 = combExamConfigMapper.selectByPrimaryKey(combExamConfig.getCombExamId());
        return BeanUtil.copyProperties(combExamConfig1,CombExamConfigDataItemDTO.class);
    }
    @Override
    public List<CombExamConfigListDTO> getConfigList(CombExamConfigListConditionDTO combExamConfigListConditionDTO) {
        return combExamConfigMapper.getConfigList(combExamConfigListConditionDTO);
    }
    @Override
    public List<CombExamConfigItemListDTO> getConfigItem(CombExamConfigItemListConditionDTO combExamConfigItemListConditionDTO) {
        return combExamConfigItemMapper.getConfigItem(combExamConfigItemListConditionDTO);
    }
    @Override
    public CombExamConfigDataItemDTO queryById(DeleteCombExamConfigDTO deleteCombExamConfigDTO) {
        return combExamConfigMapper.queryById(deleteCombExamConfigDTO);
    }
    @Override
    public int freeze(UpdateCombExamConfigDTO updateCombExamConfigDTO) {
        updateCombExamConfigDTO.setVersion(updateCombExamConfigDTO.getVersion()+1L);
        CombExamConfig combExamConfig = BeanUtil.copyProperties(updateCombExamConfigDTO,CombExamConfig.class);
        Example example = new Example(CombExamConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("combExamId", updateCombExamConfigDTO.getCombExamId());
        int update = combExamConfigMapper.updateByExampleSelective(combExamConfig, example);
        if (update == 0) {
            throw new BusinessException("200504", "修改配置信息失败!");
        }
        return update;
    }
}
