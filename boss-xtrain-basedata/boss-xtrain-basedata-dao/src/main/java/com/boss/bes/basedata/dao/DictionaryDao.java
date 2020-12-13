package com.boss.bes.basedata.dao;

import com.boss.bes.basedata.pojo.dto.dictionary.*;
import com.boss.bes.basedata.pojo.entity.Dictionary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryDao {
    /**
     * 增加字典
     * @param insertDictionaryDTO
     * @return 1成功，0失败
     */
    int add(InsertDictionaryDTO insertDictionaryDTO);
    /**
     * 更新字典
     * @param updateDictionaryDTO
     * @return 更新的字典
     */
    DictionaryDTO update(UpdateDictionaryDTO updateDictionaryDTO);
    /**
     * 删除字典
     * @param deleteDictionaryDTO
     * @return 删除字典
     */
    int delete(DeleteDictionaryDTO deleteDictionaryDTO);
    /**
     * 返回字典
     * @return
     */
    List<DictionaryDTO> query();
    /**
     * 获取字典列表
     * @param dictionaryListConditionDTO
     * @return
     */
    List<DictionaryDTO> getDictionarys(DictionaryListConditionDTO dictionaryListConditionDTO);
    /**
     * 获取字典列表
     * @param dictionaryListConditionDTO
     * @return
     */
    List<DictionaryDTO> getDictionaryByCategory(DictionaryListConditionDTO dictionaryListConditionDTO);
    /**
     * 按条件返回字典列表
     * @param dictionaryQueryConditionDTO
     * @return
     */
    List<DictionaryDTO> queryByCondition(DictionaryQueryConditionDTO dictionaryQueryConditionDTO);
    /**
     * 根据类别获取字典
     *
     * @param category
     * @return
     */
    List<Dictionary> queryDictionaryByCategory(String category);
}
