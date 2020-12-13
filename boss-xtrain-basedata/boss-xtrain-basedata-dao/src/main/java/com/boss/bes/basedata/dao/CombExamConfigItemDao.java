package com.boss.bes.basedata.dao;
import com.boss.bes.basedata.pojo.dto.combexamconfig.DeleteCombExamConfigDTO;
import com.boss.bes.basedata.pojo.entity.CombExamConfigItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CombExamConfigItemDao {
    /**
     * 增加配置明细
     * @param itemList
     * @return
     */
    int save(List<CombExamConfigItem> itemList);

    /**
     * 删除配置明细
     * @param c
     */
    void delete(DeleteCombExamConfigDTO c);

    /**
     * 修改配置明细
     * @param itemList
     * @return
     */
    int update(List<CombExamConfigItem> itemList);

    /**
     * 批量删除配置明细
     * @param deleteItemIds
     */
    void deleteByIds(List<Long> deleteItemIds);

    /**
     * 批量删除配置明细
     * @param idList
     */
    void deleteByConfigIds(List<Long> idList);
}