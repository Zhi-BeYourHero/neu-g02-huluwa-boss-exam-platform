package com.boss.bes.basedata.service.impl;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.basedata.dao.SubjectTypeDao;
import com.boss.bes.basedata.pojo.dto.subjecttype.*;
import com.boss.bes.basedata.pojo.entity.SubjectType;
import com.boss.bes.basedata.pojo.vo.subjecttype.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.basedata.mapper.SubjectTypeMapper;
import com.boss.bes.basedata.service.SubjectTypeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectTypeServiceImpl implements SubjectTypeService{
    @Resource
    private SubjectTypeMapper subjectTypeMapper;
    @Autowired
    private SubjectTypeDao subjectTypeDaoImpl;
    @Override
    public int add(InsertSubjectTypeVO object) {
        InsertSubjectTypeDTO addSubjectTypeDTO = BeanUtil.copyProperties(object, InsertSubjectTypeDTO.class);
        return subjectTypeDaoImpl.add(addSubjectTypeDTO);
    }
    @Override
    public int batchAdd(List<InsertSubjectTypeVO> list) {
        int count = 0;
        int result = 0;
        for(InsertSubjectTypeVO object: list){
            InsertSubjectTypeDTO addSubjectTypeDTO = BeanUtil.copyProperties(object, InsertSubjectTypeDTO.class);
            count += subjectTypeDaoImpl.add(addSubjectTypeDTO);
            result++;
        }
        return count < result ? 0:1;
    }
    @Override
    public SubjectTypeDataItemVO update(UpdateSubjectTypeVO object) {
        UpdateSubjectTypeDTO updateSubjectTypeDTO = BeanUtil.copyProperties(object, UpdateSubjectTypeDTO.class);
        return BeanUtil.copyProperties(subjectTypeDaoImpl.update(updateSubjectTypeDTO), SubjectTypeDataItemVO.class);
    }
    @Override
    public int delete(DeleteSubjectTypeVO object) {
        DeleteSubjectTypeDTO deleteSubjectTypeDTO = BeanUtil.copyProperties(object, DeleteSubjectTypeDTO.class);
        return subjectTypeDaoImpl.delete(deleteSubjectTypeDTO);
    }
    @Override
    public int batchRemove(DeleteSubjectTypesVO object) {
        int count = 0;
        int result = 0;
        String string = object.getIds();
        String[] strs = string.split(",");
        for(String str:strs){
            DeleteSubjectTypeDTO deleteSubjectTypeDTO = new DeleteSubjectTypeDTO();
            deleteSubjectTypeDTO.setSubjectTypeId(Long.parseLong(str));
            count += subjectTypeDaoImpl.delete(deleteSubjectTypeDTO);
            result++;
        }
        return count < result ? 0:1;
    }
    @Override
    public CommonPage<SubjectTypeDataItemVO> queryByCondition(SubjectTypeQueryConditionVO object) {
        SubjectTypeQueryConditionDTO subjectTypeQueryConditionDTO = BeanUtil.copyProperties(object, SubjectTypeQueryConditionDTO.class);
        List<SubjectTypeDataItemDTO> subjectTypeDataItemDtos = subjectTypeDaoImpl.queryByCondition(subjectTypeQueryConditionDTO);
        return BeanUtil.copyProperties(restPage(subjectTypeDataItemDtos), CommonPage.class);
    }
    @Override
    public List<SubjectTypeListVO> getSubjectTypes(SubjectTypeListConditionVO object) {
        SubjectTypeListConditionDTO subjectTypeListConditionDTO = BeanUtil.copyProperties(object, SubjectTypeListConditionDTO.class);
        List<SubjectTypeListDTO> subjectTypeListDtos = subjectTypeDaoImpl.getSubjectTypes(subjectTypeListConditionDTO);
        List<SubjectTypeListVO> subjectTypeListVos = new ArrayList<>();
        for (SubjectTypeListDTO subjectTypeListDTO: subjectTypeListDtos){
            SubjectTypeListVO subjectTypeListVO = BeanUtil.copyProperties(subjectTypeListDTO, SubjectTypeListVO.class);
            subjectTypeListVos.add(subjectTypeListVO);
        }
        return subjectTypeListVos;
    }
    @Override
    public List<SubjectType> getAllSubjectTypes(){
        return subjectTypeDaoImpl.getAllSubjectTypes();
    }
    @Override
    public List<SubjectType> getAllSubjectTypeNames(){
        return subjectTypeDaoImpl.getAllSubjectTypes();
    }

    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setPages(pageInfo.getPages());
        result.setPageIndex(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        return result;
    }
}
