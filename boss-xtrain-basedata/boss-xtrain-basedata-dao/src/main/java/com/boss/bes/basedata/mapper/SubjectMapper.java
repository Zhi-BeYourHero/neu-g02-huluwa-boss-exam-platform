package com.boss.bes.basedata.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemDTO;
import com.boss.bes.basedata.pojo.dto.subject.DeleteSubjectDTO;
import com.boss.bes.basedata.pojo.dto.subject.SubjectDataItemDTO;
import com.boss.bes.basedata.pojo.dto.subject.SubjectQueryConditionDTO;
import com.boss.bes.basedata.pojo.entity.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper extends CommonMapper<Subject> {
    /**
     * 按条件查询题目
     * @param subjectQueryConditionDTO
     * @return
     */
    List<SubjectDataItemDTO> queryByCondition(SubjectQueryConditionDTO subjectQueryConditionDTO);
    /**
     * 查询题目类别Id按题目类别名称
     * @param categoryName
     * @return
     */
    Long findCategoryIdByCategoryName(String categoryName);
    /**
     * 查询题目类型Id按题目类型名称
     * @param subjectTypeName
     * @return
     */
    Long findSubjectTypeIdBySubjectTypeName(String subjectTypeName);
    /**
     * 删除题目答案通过题目id
     * @param id
     * @return
     */
    int deleteSubjectAnswer(Long id);
    /**
     * 快速组卷
     * @param combExamConfigItemDTO
     * @return
     */
    List<SubjectDataItemDTO> quickMakePaper(CombExamConfigItemDTO combExamConfigItemDTO);
    /**
     * 获取题目通过Id
     * @param id
     * @return
     */
    SubjectDataItemDTO getSubjectDtoByIds(@Param("id") Long id);
    /**
     * 按id查询题目
     * @param deleteSubjectDTO
     * @return
     */
    SubjectDataItemDTO queryById(DeleteSubjectDTO deleteSubjectDTO);
}