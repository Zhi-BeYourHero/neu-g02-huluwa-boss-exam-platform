package com.boss.bes.basedata.service;

import boss.xtrain.core.data.convention.common.CommonPage;
import com.boss.bes.basedata.pojo.vo.combexamconfig.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CombExamConfigService{
    /**
     * 获取组卷配置列表
     * @param object
     * @return
     */
    CommonPage<CombExamConfigDataItemVO> queryByCondition(CombExamConfigQueryConditionVO object);
    /**
     * 增加组卷配置
     * @param object
     * @return
     */
    int add(InsertCombExamConfigVO object);
    /**
     * 删除组卷配置
     * @param object
     * @return
     */
    int delete(DeleteCombExamConfigVO object);
    /**
     * 删除多个题目
     * @param object
     * @return
     */
    int batchRemove(DeleteCombExamConfigsVO object);
    /**
     * 修改组卷配置
     * @param object
     * @return
     */
    CombExamConfigDataItemVO update(UpdateCombExamConfigVO object);
    /**
     * 获取组卷配置列表
     * @param object
     * @return
     */
    CommonPage<CombExamConfigListVO>  getConfigList(CombExamConfigListConditionVO object);
    /**
     * 获取组卷配置明细列表
     * @param object
     * @return
     */
    List<CombExamConfigItemListVO> getConfigItem(CombExamConfigItemListConditionVO object);
    /**
     * 修改题目时获取题目信息
     * @param object
     * @return
     */
    CombExamConfigDataItemVO queryById(DeleteCombExamConfigVO  object);
    /**
     * 启用或禁用组卷配置
     * @param object
     * @return
     */
    int freeze(UpdateCombExamConfigVO object);
}
