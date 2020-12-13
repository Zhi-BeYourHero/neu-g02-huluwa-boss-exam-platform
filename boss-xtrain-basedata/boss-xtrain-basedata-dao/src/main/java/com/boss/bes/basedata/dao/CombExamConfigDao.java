package com.boss.bes.basedata.dao;
import com.boss.bes.basedata.pojo.dto.combexamconfig.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface CombExamConfigDao {
    /**
     * 按查询条件返回组卷配置信息
     * @param combExamConfigQueryConditionDTO
     * @return
     */
    List<CombExamConfigDataItemDTO> queryByCondition(CombExamConfigQueryConditionDTO combExamConfigQueryConditionDTO);
    /**
     * 增加组卷配置
     * @param addCombExamConfigDTO
     * @return
     */
    int add(InsertCombExamConfigDTO addCombExamConfigDTO);
    /**
     * 删除组卷配置
     * @param deleteCombExamConfigDTO
     * @return
     */
    int delete(DeleteCombExamConfigDTO deleteCombExamConfigDTO);
    /**
     * 更新组卷配置
     * @param updateCombExamConfigDTO
     * @return
     */
    CombExamConfigDataItemDTO update(UpdateCombExamConfigDTO updateCombExamConfigDTO);
    /**
     *试卷服务发送请求给基础数据获取配置列表
     * @param combExamConfigListConditionDTO
     * @return
     */
    List<CombExamConfigListDTO> getConfigList(CombExamConfigListConditionDTO combExamConfigListConditionDTO);
    /**
     *试卷服务发送请求获取配置的明细项
     * @param combExamConfigItemListConditionDTO
     * @return
     */
    List<CombExamConfigItemListDTO> getConfigItem(CombExamConfigItemListConditionDTO combExamConfigItemListConditionDTO);
    /**
     * 按查询题目
     * @param deleteCombExamConfigDTO
     * @return
     */
    CombExamConfigDataItemDTO queryById(DeleteCombExamConfigDTO deleteCombExamConfigDTO);
    /**
     * 启用或禁用组卷配置
     * @param updateCombExamConfigDTO
     * @return
     */
    int freeze(UpdateCombExamConfigDTO updateCombExamConfigDTO);

}
