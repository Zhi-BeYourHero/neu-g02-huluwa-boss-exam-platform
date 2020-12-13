package com.boss.bes.basedata.service;

import boss.xtrain.core.data.convention.common.CommonPage;
import com.boss.bes.basedata.pojo.entity.SubjectType;
import com.boss.bes.basedata.pojo.vo.subjecttype.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectTypeService{
    /**
     * 增加题目类型
     * @param object
     * @return
     */
    int add(InsertSubjectTypeVO object);
    int batchAdd(List<InsertSubjectTypeVO> list);
    /**
     * 更新题目类型
     * @param object
     * @return
     */
    SubjectTypeDataItemVO update(UpdateSubjectTypeVO object);
    /**
     * 删除题目类型
     * @param object
     * @return
     */
    int delete(DeleteSubjectTypeVO object);
    /**
     * 删除多个题目类型
     * @param object
     * @return
     */
    int batchRemove(DeleteSubjectTypesVO object);
    /**
     * 查询题目列表
     * @param object
     * @return
     */
    CommonPage<SubjectTypeDataItemVO> queryByCondition(SubjectTypeQueryConditionVO object);
    /**
     * 获取题目类型列表
     * @param object
     * @return
     */
    List<SubjectTypeListVO> getSubjectTypes(SubjectTypeListConditionVO object);
    /**
     * 获取所有题目类型
     * @return
     */
    List<SubjectType> getAllSubjectTypes();
    List<SubjectType> getAllSubjectTypeNames();

}
