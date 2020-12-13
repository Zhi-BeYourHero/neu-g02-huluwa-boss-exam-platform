package com.boss.bes.basedata.service;

import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CombExamConfigItemService{
    /**
     * 添加配置明细
     * @param itemList
     * @return
     */
    boolean addConfigItem(List<CombExamConfigItemDTO> itemList);
}
