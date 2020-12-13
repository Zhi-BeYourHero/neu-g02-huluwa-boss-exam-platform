package com.boss.bes.paper.service;

import com.boss.bes.paper.pojo.dto.managetemplate.TemplateListDTO;
import com.boss.bes.paper.pojo.dto.managetemplate.TemplateSearchDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.answer.AnswerDTO;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/9 12:38
 * @since: 1.0
 */
@Service
public interface TemplateManageService {

    /**
     * 模板查询
     * @param templateDTO 模板查询条件
     * @return 符合条件的模板列表
     */
    List<TemplateListDTO> searchTemplateService(TemplateSearchDTO templateDTO);

    /**
     * 获取模板份数
     * @param templateDTO 模板查询条件
     * @return 符合条件的模板份数
     */
    int getTemplateCount(TemplateSearchDTO templateDTO);

    /**
     *  修改模板
     * @param paper 要修改的模板信息
     * @param subjects 要修改的试题列表信息
     * @param answers 要修改的答案列表信息
     * @return 修改结果
     */
    int modifyTemplateService(PaperModifyDTO paper, List<SubjectModifyDTO> subjects, List<AnswerDTO> answers);
}
