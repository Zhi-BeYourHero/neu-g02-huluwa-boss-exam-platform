package com.boss.bes.basedata.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.basedata.pojo.dto.dictionary.DictionaryDTO;
import com.boss.bes.basedata.pojo.dto.dictionary.DictionaryListConditionDTO;
import com.boss.bes.basedata.pojo.dto.dictionary.DictionaryQueryConditionDTO;
import com.boss.bes.basedata.pojo.entity.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryMapper extends CommonMapper<Dictionary> {
    /**
     * 返回查询的全部字典
     * @return
     */
    List<DictionaryDTO> query();
    /**
     * 按指定条件返回查询字典
     * @param dictionaryQueryConditionDTO
     * @return
     */
    List<DictionaryDTO> queryByCondition(DictionaryQueryConditionDTO dictionaryQueryConditionDTO);
    /**
     * 删除字典通过字典id
     * @param id
     * @return
     */
    int deleteDictionary(Long id);
    /**
     * 返回字典
     * @param dictionaryListConditionDTO
     * @return
     */
    List<DictionaryDTO> getDictionarys(DictionaryListConditionDTO dictionaryListConditionDTO);
    List<DictionaryDTO> getDictionaryByCategory(DictionaryListConditionDTO dictionaryListConditionDTO);
}