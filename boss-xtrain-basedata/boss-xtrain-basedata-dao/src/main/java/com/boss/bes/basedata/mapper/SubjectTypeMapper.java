package com.boss.bes.basedata.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.basedata.pojo.dto.subjecttype.SubjectTypeDataItemDTO;
import com.boss.bes.basedata.pojo.dto.subjecttype.SubjectTypeListConditionDTO;
import com.boss.bes.basedata.pojo.dto.subjecttype.SubjectTypeListDTO;
import com.boss.bes.basedata.pojo.dto.subjecttype.SubjectTypeQueryConditionDTO;
import com.boss.bes.basedata.pojo.entity.SubjectType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectTypeMapper extends CommonMapper<SubjectType> {
    /**
     * 题目类型元素列表
     * @param subjectTypeQueryConditionDTO
     * @return
     */
    List<SubjectTypeDataItemDTO> queryByCondition(SubjectTypeQueryConditionDTO subjectTypeQueryConditionDTO);
    /**
     * 试卷服务获取题目类型列表
     * @param subjectTypeListConditionDTO
     * @return
     */
    List<SubjectTypeListDTO> getSubjectTypes(SubjectTypeListConditionDTO subjectTypeListConditionDTO);
}