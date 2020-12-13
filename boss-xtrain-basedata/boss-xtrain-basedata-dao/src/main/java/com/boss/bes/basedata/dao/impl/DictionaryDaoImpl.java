package com.boss.bes.basedata.dao.impl;

import boss.xtrain.core.exception.BusinessException;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.basedata.dao.DictionaryDao;
import com.boss.bes.basedata.mapper.DictionaryMapper;
import com.boss.bes.basedata.pojo.dto.dictionary.*;
import com.boss.bes.basedata.pojo.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 字典
 * @create 2020-07-08 18:43
 * @since
 */
@Component
public class DictionaryDaoImpl implements DictionaryDao {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    public int add(InsertDictionaryDTO addDictionaryDTO) {
        Dictionary dictionary = BeanUtil.copyProperties(addDictionaryDTO, Dictionary.class);
        assert dictionary != null;
        FillPublicField.fillPublicField(dictionary);
        dictionary.setId(Long.valueOf(SnowFlakeUtil.getId()));
        return dictionaryMapper.insertSelective(dictionary);
    }
    @Override
    public DictionaryDTO update(UpdateDictionaryDTO updateDictionaryDTO) {
        updateDictionaryDTO.setVersion(updateDictionaryDTO.getVersion()+1L);
        Dictionary dictionary = BeanUtil.copyProperties(updateDictionaryDTO, Dictionary.class);
        Example example = new Example(Dictionary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", updateDictionaryDTO.getId());
        int update = dictionaryMapper.updateByExampleSelective(dictionary, example);
        if (update == 0) {
            throw new BusinessException("200104", "修改字典信息失败！");
        }
        assert dictionary != null;
        Dictionary dictionary1 = dictionaryMapper.selectByPrimaryKey(dictionary.getId());
        return BeanUtil.copyProperties(dictionary1, DictionaryDTO.class);
    }
    @Override
    public int delete(DeleteDictionaryDTO deleteCategoryDTO) {
        Dictionary dictionary = BeanUtil.copyProperties(deleteCategoryDTO, Dictionary.class);
        return dictionaryMapper.deleteByPrimaryKey(dictionary);
    }
    @Override
    public List<DictionaryDTO> query() {
        return dictionaryMapper.query();
    }
    @Override
    public List<DictionaryDTO> getDictionarys(DictionaryListConditionDTO dictionaryListConditionDTO) {
        return dictionaryMapper.getDictionarys(dictionaryListConditionDTO);
    }
    @Override
    public List<DictionaryDTO> getDictionaryByCategory(DictionaryListConditionDTO dictionaryListConditionDTO) {
        return dictionaryMapper.getDictionaryByCategory(dictionaryListConditionDTO);
    }
    @Override
    public List<DictionaryDTO> queryByCondition(DictionaryQueryConditionDTO dictionaryQueryConditionDTO) {
        return  dictionaryMapper.queryByCondition((dictionaryQueryConditionDTO));
    }
    @Override
    public List<Dictionary> queryDictionaryByCategory(String category) {
        Example example = new Example(Dictionary.class);
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("category", category);
        example.setOrderByClause("id desc");
        return dictionaryMapper.selectByExample(example);
    }
}

