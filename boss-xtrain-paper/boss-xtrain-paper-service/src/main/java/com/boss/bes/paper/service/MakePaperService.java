package com.boss.bes.paper.service;

import com.boss.bes.paper.pojo.dto.makepaper.MakePaperDTO;
import com.boss.bes.paper.pojo.dto.makepaper.QuickMakePaperDTO;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/8 21:34
 * @since: 1.0
 */
public interface MakePaperService {

    /**
     *  快速组卷服务接口
     * @param quickMakePaperDTO 带有组卷配置以及试卷信息的快速组卷参数
     * @return 组卷结果
     */
    int quickMakePaper(QuickMakePaperDTO quickMakePaperDTO);

    /**
     *  标准组卷服务接口
     * @param quickMakePaperDTO 带有组卷配置以及试卷信息的标准组卷参数
     * @return 组卷结果
     */
    int standardMakePaper(QuickMakePaperDTO quickMakePaperDTO);

    /**
     *   保存试卷数据
     * @param paper 要保存的试卷数据
     * @param name 组卷人姓名
     * @return 保存结果
     */
    int savePaperData(MakePaperDTO paper,String name);
}
