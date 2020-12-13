package com.boss.bes.basedata.service.impl;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.basedata.dao.CombExamConfigDao;
import com.boss.bes.basedata.dao.SubjectDao;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemDTO;
import com.boss.bes.basedata.pojo.dto.combexamconfig.InsertCombExamConfigDTO;
import com.boss.bes.basedata.pojo.dto.subject.*;
import com.boss.bes.basedata.pojo.vo.combexamconfig.CombExamConfigDataItemVO;
import com.boss.bes.basedata.pojo.vo.subject.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.basedata.mapper.SubjectMapper;
import com.boss.bes.basedata.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Resource
    private SubjectMapper subjectMapper;
    @Autowired
    private SubjectDao subjectDaoImpl;
    @Autowired
    private CombExamConfigDao combExamConfigDaoImpl;
    @Override
    public CommonPage<SubjectDataItemVO> queryByCondition(SubjectQueryConditionVO object) {
        SubjectQueryConditionDTO subjectQueryConditionDTO = BeanUtil.copyProperties(object,SubjectQueryConditionDTO.class);
        List<SubjectDataItemDTO> subjectDataItemDtos = subjectDaoImpl.queryByCondition(subjectQueryConditionDTO);
        return BeanUtil.copyProperties(restPage(subjectDataItemDtos),CommonPage.class);
    }
    @Override
    public int add(InsertSubjectVO object) {
        SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
        long nid=snowFlakeUtil.nextId();
        InsertSubjectDTO addSubjectDTO = BeanUtil.copyProperties(object,InsertSubjectDTO.class);
        assert addSubjectDTO != null;
        addSubjectDTO.setSubjectId(nid);
        addSubjectDTO.setVersion(1L);
        return subjectDaoImpl.add(addSubjectDTO);
    }
    @Override
    public int batchAdd(List<InsertSubjectVO> list){
        int count = 0;
        int result = 0;
        for(InsertSubjectVO object: list){
            count += add(object);
            result++;
        }
        return count < result ? 0:1;
    }
    @Override
    public int delete(DeleteSubjectVO object) {
        DeleteSubjectDTO deleteSubjectDTO = BeanUtil.copyProperties(object,DeleteSubjectDTO.class);
        return subjectDaoImpl.delete(deleteSubjectDTO);
    }
    @Override
    public int batchRemove(DeleteSubjectsVO object) {
        int count = 0;
        int result = 0;
        String string = object.getIds();
        String[] strs = string.split(",");
        for(String str:strs){
            DeleteSubjectDTO deleteSubjectDTO = new DeleteSubjectDTO();
            deleteSubjectDTO.setSubjectId(Long.parseLong(str));
            count += subjectDaoImpl.delete(deleteSubjectDTO);
            result++;
        }
        return count < result ? 0:1;
    }
    @Override
    public SubjectDataItemVO update(UpdateSubjectVO object) {
        UpdateSubjectDTO updateSubjectDTO = BeanUtil.copyProperties(object,UpdateSubjectDTO.class);
        return BeanUtil.copyProperties(subjectDaoImpl.update(updateSubjectDTO),SubjectDataItemVO.class);
    }
    @Override
    public List<SubjectDataItemVO> quickMakePaper(CombExamConfigDataItemVO object) {
        List<CombExamConfigItemDTO> combExamConfigItemDtos = object.getConfigItems();
        List<SubjectDataItemVO> subjectDataItemVos = new ArrayList<>();
        for(CombExamConfigItemDTO combExamConfigItemDTO: combExamConfigItemDtos){
            List<SubjectDataItemDTO> subjectDataItemDtos = subjectDaoImpl.quickMakePaper(combExamConfigItemDTO);
            for(SubjectDataItemDTO subjectDataItemDTO: subjectDataItemDtos){
                SubjectDataItemVO subjectDataItemVO = BeanUtil.copyProperties(subjectDataItemDTO,SubjectDataItemVO.class);
                subjectDataItemVos.add(subjectDataItemVO);
            }
        }
        return subjectDataItemVos;
    }
    @Override
    public List<SubjectDataItemVO> standardMakePaper(CombExamConfigDataItemVO object) {
        SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
        long nid=snowFlakeUtil.nextId();
        InsertCombExamConfigDTO addCombExamConfigDTO = BeanUtil.copyProperties(object,InsertCombExamConfigDTO.class);
        assert addCombExamConfigDTO != null;
        addCombExamConfigDTO.setCombExamId(nid);
        combExamConfigDaoImpl.add(addCombExamConfigDTO);
        List<CombExamConfigItemDTO> combExamConfigItemDtos = object.getConfigItems();
        List<SubjectDataItemVO> subjectDataItemVos = new ArrayList<>();
        for(CombExamConfigItemDTO combExamConfigItemDTO: combExamConfigItemDtos){
            List<SubjectDataItemDTO> subjectDataItemDtos = subjectDaoImpl.quickMakePaper(combExamConfigItemDTO);
            for(SubjectDataItemDTO subjectDataItemDTO: subjectDataItemDtos){
                SubjectDataItemVO subjectDataItemVO = BeanUtil.copyProperties(subjectDataItemDTO,SubjectDataItemVO.class);
                subjectDataItemVos.add(subjectDataItemVO);
            }
        }
        return subjectDataItemVos;
    }
    @Override
    public List<SubjectDataItemVO> getSubjectDtoByIds(SubjectIdConditionVO object) {
        List<Long> ids = object.getIds();
        List<SubjectDataItemVO> subjectDataItemVos = new ArrayList<>();
        for(Long id:ids){
            SubjectDataItemDTO subjectDataItemDTO = subjectDaoImpl.getSubjectDtoByIds(id);
            SubjectDataItemVO subjectDataItemVO = BeanUtil.copyProperties(subjectDataItemDTO,SubjectDataItemVO.class);
            subjectDataItemVos.add(subjectDataItemVO);
        }
        return subjectDataItemVos;
    }
    @Override
    public SubjectDataItemVO queryById(DeleteSubjectVO object) {
        DeleteSubjectDTO deleteSubjectDTO = BeanUtil.copyProperties(object,DeleteSubjectDTO.class);
        SubjectDataItemDTO subjectDataItemDto = subjectDaoImpl.queryById(deleteSubjectDTO);
        return BeanUtil.copyProperties(subjectDataItemDto,SubjectDataItemVO.class);
    }
    @Override
    public int freeze(UpdateSubjectVO object) {
        UpdateSubjectDTO updateSubjectDTO = BeanUtil.copyProperties(object,UpdateSubjectDTO.class);
        return subjectDaoImpl.freeze(updateSubjectDTO);
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
