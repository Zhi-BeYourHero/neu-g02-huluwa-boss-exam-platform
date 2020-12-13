package com.boss.bes.basedata.service;

import boss.xtrain.core.data.convention.common.CommonPage;
import com.boss.bes.basedata.pojo.entity.Dictionary;
import com.boss.bes.basedata.pojo.vo.dictionary.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DictionaryService{
    /**
     * 增加字典
     * @param object
     * @return
     */
    int add(InsertDictionaryVO object);
    int batchAdd(List<InsertDictionaryVO> list);
    /**
     * 更新字典
     * @param object
     * @return
     */
    DictionaryVO update(UpdateDictionaryVO object);
    /**
     * 删除字典
     * @param object
     * @return
     */
    int delete(DeleteDictionaryVO object);
    /**
     * 删除多个字典
     * @param object
     * @return
     */
    int batchRemove(DeleteDictionarysVO object);
    /**
     * 获取字典
     * @param object
     * @return
     */
    List<DictionaryVO> getDictionarys(DictionaryListConditionVO object);
    /**
     * 获取字典
     * @param object
     * @return
     */
    List<DictionaryVO> getDictionaryByCategory(DictionaryListConditionVO object);
    /**
     * 查询字典列表
     * @param object
     * @return
     */
    CommonPage<DictionaryVO> queryByCondition(QueryDictionaryVO object);
    /**
     * 根据类别获取字典
     *
     * @param category
     * @return
     */
    List<Dictionary> queryDictionaryByCategory(String category);
}
