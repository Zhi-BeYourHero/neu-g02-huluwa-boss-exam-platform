package com.boss.bes.basedata.dao;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemDTO;
import com.boss.bes.basedata.pojo.dto.subject.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface SubjectDao {
    /**
     * 按条件查询题目列表
     * @param subjectQueryConditionDTO
     * @return
     */
    List<SubjectDataItemDTO> queryByCondition(SubjectQueryConditionDTO subjectQueryConditionDTO);
    /**
     * 增加题目
     * @param addSubjectDTO
     * @return
     */
    int add(InsertSubjectDTO addSubjectDTO);
    /**
     * 删除题目
     * @param deleteSubjectDTO
     * @return
     */
    int delete(DeleteSubjectDTO deleteSubjectDTO);
    /**
     * 更新题目
     * @param updateSubjectDTO
     * @return
     */
    SubjectDataItemDTO update(UpdateSubjectDTO updateSubjectDTO);
    /**
     * 试卷服务请求，按配置信息快速组卷
     * @param combExamConfigItemDTO
     * @return
     */
    List<SubjectDataItemDTO> quickMakePaper(CombExamConfigItemDTO combExamConfigItemDTO);
    /**
     *考试服务 通过题目ID列表获取题目描述，标准答案，分值，类型
     * @param id
     * @return
     */
    SubjectDataItemDTO getSubjectDtoByIds(Long id);
    /**
     * 按查询题目
     * @param deleteSubjectDTO
     * @return
     */
    SubjectDataItemDTO queryById(DeleteSubjectDTO deleteSubjectDTO);
    /**
     * 启用或禁用题目
     * @param updateSubjectDTO
     * @return
     */
     int freeze(UpdateSubjectDTO updateSubjectDTO);
}
