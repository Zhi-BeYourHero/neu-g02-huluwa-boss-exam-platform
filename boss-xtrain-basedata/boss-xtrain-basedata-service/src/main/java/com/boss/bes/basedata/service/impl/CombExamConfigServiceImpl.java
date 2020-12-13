package com.boss.bes.basedata.service.impl;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.basedata.dao.CombExamConfigDao;
import com.boss.bes.basedata.pojo.dto.combexamconfig.*;
import com.boss.bes.basedata.pojo.vo.combexamconfig.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.basedata.mapper.CombExamConfigMapper;
import com.boss.bes.basedata.service.CombExamConfigService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombExamConfigServiceImpl implements CombExamConfigService{
    @Resource
    private CombExamConfigMapper combExamConfigMapper;
    @Autowired
    private CombExamConfigDao combExamConfigDaoImpl;
    @Override
    public CommonPage<CombExamConfigDataItemVO> queryByCondition(CombExamConfigQueryConditionVO object) {
        CombExamConfigQueryConditionDTO combExamConfigQueryConditionDTO = BeanUtil.copyProperties(object, CombExamConfigQueryConditionDTO.class);
        List<CombExamConfigDataItemDTO> combExamConfigDataItemDtos = combExamConfigDaoImpl.queryByCondition(combExamConfigQueryConditionDTO);
        return BeanUtil.copyProperties(restPage(combExamConfigDataItemDtos), CommonPage.class);
    }
    @Override
    public int add(InsertCombExamConfigVO object) {
        SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
        InsertCombExamConfigDTO addCombExamConfigDTO = BeanUtil.copyProperties(object, InsertCombExamConfigDTO.class);
        Long nid = snowFlakeUtil.nextId();
        assert addCombExamConfigDTO != null;
        addCombExamConfigDTO.setCombExamId(nid);
        return combExamConfigDaoImpl.add(addCombExamConfigDTO);
    }
    @Override
    public int delete(DeleteCombExamConfigVO object) {
        DeleteCombExamConfigDTO deleteCombExamConfigDTO = BeanUtil.copyProperties(object, DeleteCombExamConfigDTO.class);
        return combExamConfigDaoImpl.delete(deleteCombExamConfigDTO);
    }
    @Override
    public int batchRemove(DeleteCombExamConfigsVO object) {
        int count = 0;
        int result = 0;
        String string = object.getIds();
        String[] strs = string.split(",");
        for(String str:strs){
            DeleteCombExamConfigDTO deleteDeleteCombExamConfigDTO = new DeleteCombExamConfigDTO();
            deleteDeleteCombExamConfigDTO.setCombExamId(Long.parseLong(str));
            count += combExamConfigDaoImpl.delete(deleteDeleteCombExamConfigDTO);
            result++;
        }
        return count < result ? 0:1;
    }
    @Override
    public CombExamConfigDataItemVO update(UpdateCombExamConfigVO object) {
        UpdateCombExamConfigDTO updateCombExamConfigDTO = BeanUtil.copyProperties(object, UpdateCombExamConfigDTO.class);
        return BeanUtil.copyProperties(combExamConfigDaoImpl.update(updateCombExamConfigDTO),CombExamConfigDataItemVO.class);
    }
    @Override
    public CommonPage<CombExamConfigListVO> getConfigList(CombExamConfigListConditionVO object) {
        CombExamConfigListConditionDTO combExamConfigListConditionDTO = BeanUtil.copyProperties(object, CombExamConfigListConditionDTO.class);
        List<CombExamConfigListDTO> combExamConfigListDtos =combExamConfigDaoImpl.getConfigList(combExamConfigListConditionDTO);
        return BeanUtil.copyProperties(restPage(combExamConfigListDtos), CommonPage.class);
    }
    @Override
    public List<CombExamConfigItemListVO> getConfigItem(CombExamConfigItemListConditionVO object) {
        CombExamConfigItemListConditionDTO combExamConfigItemListConditionDTO = BeanUtil.copyProperties(object, CombExamConfigItemListConditionDTO.class);
        List<CombExamConfigItemListDTO> combExamConfigItemListDtos = combExamConfigDaoImpl.getConfigItem(combExamConfigItemListConditionDTO);
        List<CombExamConfigItemListVO> combExamConfigItemListVos = new ArrayList<>();
        for (CombExamConfigItemListDTO combExamConfigItemListDTO: combExamConfigItemListDtos){
            CombExamConfigItemListVO combExamConfigItemListVO = BeanUtil.copyProperties(combExamConfigItemListDTO, CombExamConfigItemListVO.class);
            combExamConfigItemListVos.add(combExamConfigItemListVO);
        }
        return combExamConfigItemListVos;
    }
    @Override
    public CombExamConfigDataItemVO queryById(DeleteCombExamConfigVO object) {
        DeleteCombExamConfigDTO deleteCombExamConfigDTO = BeanUtil.copyProperties(object, DeleteCombExamConfigDTO.class);
        CombExamConfigDataItemDTO combExamConfigDataItemDto = combExamConfigDaoImpl.queryById(deleteCombExamConfigDTO);
        return BeanUtil.copyProperties(combExamConfigDataItemDto,CombExamConfigDataItemVO.class);
    }
    @Override
    public int freeze(UpdateCombExamConfigVO object) {
        UpdateCombExamConfigDTO updateCombExamConfigDTO = BeanUtil.copyProperties(object, UpdateCombExamConfigDTO.class);
        return combExamConfigDaoImpl.freeze(updateCombExamConfigDTO);
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
