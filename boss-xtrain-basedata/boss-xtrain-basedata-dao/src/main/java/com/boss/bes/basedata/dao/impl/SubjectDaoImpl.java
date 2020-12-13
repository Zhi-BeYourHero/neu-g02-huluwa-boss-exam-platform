package com.boss.bes.basedata.dao.impl;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.basedata.dao.SubjectDao;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemDTO;
import com.boss.bes.basedata.pojo.dto.subject.*;
import com.boss.bes.basedata.pojo.entity.Subject;
import com.boss.bes.basedata.pojo.entity.SubjectAnswer;
import com.boss.bes.basedata.mapper.SubjectAnswerMapper;
import com.boss.bes.basedata.mapper.SubjectMapper;
import boss.xtrain.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class SubjectDaoImpl implements SubjectDao {
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private SubjectAnswerMapper subjectAnswerMapper;
    @Override
    public List<SubjectDataItemDTO> queryByCondition(SubjectQueryConditionDTO subjectQueryConditionDTO) {
        return  subjectMapper.queryByCondition((subjectQueryConditionDTO));
    }
    @Override
    public int add(InsertSubjectDTO addSubjectDTO) {
        Subject subject = BeanUtil.copyProperties(addSubjectDTO,Subject.class);
        assert subject != null;
        subject.setCatetoryId(subjectMapper.findCategoryIdByCategoryName(addSubjectDTO.getCategoryName()));
        subject.setSubjectTypeId(subjectMapper.findSubjectTypeIdBySubjectTypeName((addSubjectDTO.getSubjectTypeName())));
        int result = subjectMapper.insertSelective(subject);
        List<SubjectAnswer> answers = addSubjectDTO.getAnswers();
        for(SubjectAnswer subjectAnswer : answers){
            SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
            long nid=snowFlakeUtil.nextId();
            subjectAnswer.setSubjectAnswerId(nid);
            subjectAnswer.setSubjectId(addSubjectDTO.getSubjectId());
            subjectAnswerMapper.insertSelective(subjectAnswer);
        }
        return result;
    }
    @Override
    public int delete(DeleteSubjectDTO deleteSubjectDTO) {
        Subject subject = BeanUtil.copyProperties(deleteSubjectDTO,Subject.class);
        assert subject != null;
        subjectMapper.deleteSubjectAnswer(subject.getSubjectId());
        return subjectMapper.deleteByPrimaryKey(subject);
    }
    @Override
    public SubjectDataItemDTO update(UpdateSubjectDTO updateSubjectDTO) {
        updateSubjectDTO.setVersion(updateSubjectDTO.getVersion()+1L);
        Subject subject = BeanUtil.copyProperties(updateSubjectDTO,Subject.class);
        assert subject != null;
        subject.setCatetoryId(subjectMapper.findCategoryIdByCategoryName(updateSubjectDTO.getCategoryName()));
        subject.setSubjectTypeId(subjectMapper.findSubjectTypeIdBySubjectTypeName((updateSubjectDTO.getSubjectTypeName())));
        Example example = new Example(Subject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("subjectId", updateSubjectDTO.getSubjectId());
        int update = subjectMapper.updateByExampleSelective(subject, example);
        if (update == 0) {
            throw new BusinessException("200404", "修改题目信息失败！");
        }
        List<SubjectAnswer> answers = updateSubjectDTO.getAnswers();
        for(SubjectAnswer subjectAnswer : answers){
            if(subjectAnswer.getSubjectAnswerId()!=null){
                subjectAnswerMapper.updateByPrimaryKey(subjectAnswer);
            }
            else{
                SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
                long nid=snowFlakeUtil.nextId();
                subjectAnswer.setSubjectAnswerId(nid);
                subjectAnswer.setSubjectId(updateSubjectDTO.getSubjectId());
                subjectAnswerMapper.insertSelective(subjectAnswer);
            }
        }
        Subject subject1 = subjectMapper.selectByPrimaryKey(subject.getSubjectId());
        return BeanUtil.copyProperties(subject1,SubjectDataItemDTO.class);
    }
    @Override
    public List<SubjectDataItemDTO> quickMakePaper(CombExamConfigItemDTO combExamConfigItemDTO) {
        return  subjectMapper.quickMakePaper(combExamConfigItemDTO);
    }
    @Override
    public SubjectDataItemDTO getSubjectDtoByIds(Long id) {
        return subjectMapper.getSubjectDtoByIds(id);
    }
    @Override
    public SubjectDataItemDTO queryById(DeleteSubjectDTO deleteSubjectDTO) {
        return subjectMapper.queryById(deleteSubjectDTO);
    }
    @Override
    public int freeze(UpdateSubjectDTO updateSubjectDTO) {
        updateSubjectDTO.setVersion(updateSubjectDTO.getVersion()+1L);
        Subject subject = BeanUtil.copyProperties(updateSubjectDTO,Subject.class);
        Example example = new Example(Subject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("subjectId", updateSubjectDTO.getSubjectId());
        criteria.andEqualTo("version",updateSubjectDTO.getVersion()-1L);
        int update = subjectMapper.updateByExampleSelective(subject, example);
        if (update == 0) {
            throw new BusinessException("200404", "修改题目信息失败！");
        }
        return update;
    }
}
