package com.boss.bes.basedata.dao.impl;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.basedata.dao.SubjectTypeDao;
import com.boss.bes.basedata.pojo.dto.subjecttype.*;
import com.boss.bes.basedata.pojo.entity.SubjectType;
import com.boss.bes.basedata.mapper.SubjectTypeMapper;
import boss.xtrain.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Component
public class SubjectTypeDaoImpl implements SubjectTypeDao {
    @Autowired
    private SubjectTypeMapper subjectTypeMapper;
    @Override
    public int add(InsertSubjectTypeDTO addSubjectTypeDTO) {
        SubjectType subjectType = BeanUtil.copyProperties(addSubjectTypeDTO,SubjectType.class);
        assert subjectType != null;
        FillPublicField.fillPublicField(subjectType);
        subjectType.setSubjectTypeId(Long.valueOf(SnowFlakeUtil.getId()));
        return subjectTypeMapper.insertSelective(subjectType);
    }
    @Override
    public SubjectTypeDataItemDTO update(UpdateSubjectTypeDTO updateSubjectTypeDTO) {
        updateSubjectTypeDTO.setVersion(updateSubjectTypeDTO.getVersion()+1L);
        SubjectType subjectType = BeanUtil.copyProperties(updateSubjectTypeDTO,SubjectType.class);
        Example example = new Example(SubjectType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("subjectTypeId", updateSubjectTypeDTO.getSubjectTypeId());
        int update = subjectTypeMapper.updateByExampleSelective(subjectType, example);
        if (update == 0) {
            throw new BusinessException("200204", "修改类别信息失败！");
        }
        return BeanUtil.copyProperties(subjectTypeMapper.selectByPrimaryKey(subjectType.getSubjectTypeId()), SubjectTypeDataItemDTO.class);
    }
    @Override
    public int delete(DeleteSubjectTypeDTO deleteSubjectTypeDTO) {
        SubjectType subjectType = BeanUtil.copyProperties(deleteSubjectTypeDTO,SubjectType.class);
        return subjectTypeMapper.deleteByPrimaryKey(subjectType);
    }
    @Override
    public List<SubjectTypeDataItemDTO> queryByCondition(SubjectTypeQueryConditionDTO subjectTypeQueryConditionDTO) {
        return subjectTypeMapper.queryByCondition((subjectTypeQueryConditionDTO));
    }
    @Override
    public List<SubjectTypeListDTO> getSubjectTypes(SubjectTypeListConditionDTO subjectTypeListConditionDTO) {
        return subjectTypeMapper.getSubjectTypes(subjectTypeListConditionDTO);
    }
    @Override
    public List<SubjectType> getAllSubjectTypes() {
        Example example = new Example(SubjectType.class);
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("attribute","主观题");
        example.setOrderByClause("subject_type_id desc");
        return subjectTypeMapper.selectByExample(example);
    }
    @Override
    public List<SubjectType> getAllSubjectTypeNames(){
        Example example = new Example(SubjectType.class);
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("attribute","主观题");
        example.setOrderByClause("subject_type_id desc");
        return subjectTypeMapper.selectByExample(example);
    }

}
