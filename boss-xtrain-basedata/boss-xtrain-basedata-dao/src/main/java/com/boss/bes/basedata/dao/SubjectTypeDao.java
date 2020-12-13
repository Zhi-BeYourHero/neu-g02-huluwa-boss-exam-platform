package com.boss.bes.basedata.dao;
import com.boss.bes.basedata.pojo.dto.subjecttype.*;
import com.boss.bes.basedata.pojo.entity.SubjectType;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface SubjectTypeDao {
    /**
     * 增加题目类型
     * @param addSubjectTypeDTO
     * @return
     */
    int add(InsertSubjectTypeDTO addSubjectTypeDTO);
    /**
     * 更新题目类型
     * @param updateSubjectTypeDTO
     * @return
     */
    SubjectTypeDataItemDTO update(UpdateSubjectTypeDTO updateSubjectTypeDTO);
    /**
     * 删除题目类型
     * @param deleteSubjectTypeDTO
     * @return
     */
    int delete(DeleteSubjectTypeDTO deleteSubjectTypeDTO);
    /**
     * 按条件查询题目类型
     * @param subjectTypeQueryConditionDTO
     * @return
     */
    List<SubjectTypeDataItemDTO> queryByCondition(SubjectTypeQueryConditionDTO subjectTypeQueryConditionDTO);
    /**
     * 获取题型列表
     * @param subjectTypeListConditionDTO
     * @return
     */
    List<SubjectTypeListDTO> getSubjectTypes(SubjectTypeListConditionDTO subjectTypeListConditionDTO);
    /**
     * 获取所有题目类型
     * @return
     */
    List<SubjectType> getAllSubjectTypes();
    /**
     * 获取所有name题目类型
     * @return
     */
    List<SubjectType> getAllSubjectTypeNames();
}
