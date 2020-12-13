package com.boss.bes.basedata.service.impl;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.basedata.dao.DictionaryDao;
import com.boss.bes.basedata.pojo.dto.dictionary.*;
import com.boss.bes.basedata.pojo.entity.Dictionary;
import com.boss.bes.basedata.pojo.vo.dictionary.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.basedata.mapper.DictionaryMapper;
import com.boss.bes.basedata.service.DictionaryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService{
    @Resource
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DictionaryDao dictionaryDaoImpl;
    @Override
    public int add(InsertDictionaryVO object){
        InsertDictionaryDTO addDictionaryDTO = BeanUtil.copyProperties(object, InsertDictionaryDTO.class);
        return dictionaryDaoImpl.add(addDictionaryDTO);
    }
    @Override
    public int batchAdd(List<InsertDictionaryVO> list) {
        int count = 0;
        int result = 0;
        for(InsertDictionaryVO object: list){
            InsertDictionaryDTO addDictionaryDTO = BeanUtil.copyProperties(object, InsertDictionaryDTO.class);
            count += dictionaryDaoImpl.add(addDictionaryDTO);
            result++;
        }
        return count < result ? 0:1;
    }
    @Override
    public DictionaryVO update(UpdateDictionaryVO object) {
        UpdateDictionaryDTO updateDictionaryDTO = BeanUtil.copyProperties(object, UpdateDictionaryDTO.class);
        return BeanUtil.copyProperties(dictionaryDaoImpl.update(updateDictionaryDTO), DictionaryVO.class);
    }
    @Override
    public int delete(DeleteDictionaryVO object) {
        DeleteDictionaryDTO deleteDictionaryDTO = BeanUtil.copyProperties(object, DeleteDictionaryDTO.class);
        return dictionaryDaoImpl.delete(deleteDictionaryDTO);
    }
    @Override
    public int batchRemove(DeleteDictionarysVO object) {
        int count = 0;
        int result = 0;
        String string = object.getIds();
        String[] strs = string.split(",");
        for(String str:strs){
            DeleteDictionaryDTO deleteDictionaryDTO = new DeleteDictionaryDTO();
            deleteDictionaryDTO.setId(Long.parseLong(str));
            count += dictionaryDaoImpl.delete(deleteDictionaryDTO);
            result++;
        }
        return count < result? 0:1;
    }
    @Override
    public CommonPage<DictionaryVO> queryByCondition(QueryDictionaryVO object) {
        DictionaryQueryConditionDTO dictionaryQueryConditionDTO = BeanUtil.copyProperties(object, DictionaryQueryConditionDTO.class);
        List<DictionaryDTO> dictionaryDtos = dictionaryDaoImpl.queryByCondition(dictionaryQueryConditionDTO);
        return BeanUtil.copyProperties(restPage(dictionaryDtos), CommonPage.class);
    }
    @Override
    public List<DictionaryVO> getDictionarys(DictionaryListConditionVO object) {
        DictionaryListConditionDTO dictionaryListConditionDTO = BeanUtil.copyProperties(object, DictionaryListConditionDTO.class);
        List<DictionaryDTO> dictionaryDtos = dictionaryDaoImpl.getDictionarys(dictionaryListConditionDTO);
        List<DictionaryVO> dictionaryVos = new ArrayList<>();
        for (DictionaryDTO dictionaryDTO: dictionaryDtos){
            DictionaryVO dictionaryVO = BeanUtil.copyProperties(dictionaryDTO, DictionaryVO.class);
            dictionaryVos.add(dictionaryVO);
        }
        return dictionaryVos;
    }
    @Override
    public List<DictionaryVO> getDictionaryByCategory(DictionaryListConditionVO object) {
        DictionaryListConditionDTO dictionaryListConditionDTO = BeanUtil.copyProperties(object, DictionaryListConditionDTO.class);
        List<DictionaryDTO> dictionaryDtos = dictionaryDaoImpl.getDictionaryByCategory(dictionaryListConditionDTO);
        List<DictionaryVO> dictionaryVos = new ArrayList<>();
        for (DictionaryDTO dictionaryDTO: dictionaryDtos){
            DictionaryVO dictionaryVO = BeanUtil.copyProperties(dictionaryDTO, DictionaryVO.class);
            dictionaryVos.add(dictionaryVO);
        }
        return dictionaryVos;
    }
    @Override
    public List<Dictionary> queryDictionaryByCategory(String category) {
        return dictionaryDaoImpl.queryDictionaryByCategory(category);
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
