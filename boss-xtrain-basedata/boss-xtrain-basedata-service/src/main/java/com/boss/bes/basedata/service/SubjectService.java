package com.boss.bes.basedata.service;

import boss.xtrain.core.data.convention.common.CommonPage;
import com.boss.bes.basedata.pojo.vo.combexamconfig.CombExamConfigDataItemVO;
import com.boss.bes.basedata.pojo.vo.subject.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService{
    /**
     * 查询题目
     * @param object
     * @return
     */
    CommonPage<SubjectDataItemVO> queryByCondition(SubjectQueryConditionVO object);
    /**
     * 增加题目
     * @param object
     * @return
     */
    int add(InsertSubjectVO object);
    int batchAdd(List<InsertSubjectVO> list);
    /**
     * 删除题目
     * @param object
     * @return
     */
    int delete(DeleteSubjectVO object);
    /**
     * 删除多个题目
     * @param object
     * @return
     */
    int batchRemove(DeleteSubjectsVO object);
    /**
     * 更新题目
     * @param object
     * @return
     */
    SubjectDataItemVO update(UpdateSubjectVO object);
    /**
     * 快速组卷
     * @param object
     * @return
     */
    List<SubjectDataItemVO> quickMakePaper(CombExamConfigDataItemVO object);
    /**
     * 标准组卷
     * @param object
     * @return
     */
    List<SubjectDataItemVO> standardMakePaper(CombExamConfigDataItemVO object);
    /**
     * 通过id获取题目
     * @param object
     * @return
     */
    List<SubjectDataItemVO> getSubjectDtoByIds(SubjectIdConditionVO object);
    /**
     * 修改题目时获取题目信息
     * @param object
     * @return
     */
    SubjectDataItemVO queryById(DeleteSubjectVO  object);
    /**
     * 启用或禁用题目
     * @param object
     * @return
     */
    int freeze(UpdateSubjectVO object);
}
