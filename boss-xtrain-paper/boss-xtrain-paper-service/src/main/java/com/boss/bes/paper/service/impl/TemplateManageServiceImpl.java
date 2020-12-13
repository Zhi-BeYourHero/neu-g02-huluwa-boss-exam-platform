package com.boss.bes.paper.service.impl;

import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.dao.PaperDAO;
import com.boss.bes.paper.dao.PaperSubjectDAO;
import com.boss.bes.paper.dao.PaperSubjectAnswerDAO;

import com.boss.bes.paper.pojo.dto.managetemplate.TemplateListDTO;
import com.boss.bes.paper.pojo.dto.managetemplate.TemplateSearchDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.answer.AnswerDTO;
import com.boss.bes.paper.service.TemplateManageService;
import com.boss.bes.paper.service.util.ConvertUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.impl
 * @Description:
 * @Date: 2020/7/9 12:38
 * @since: 1.0
 */
@Service
public class TemplateManageServiceImpl implements TemplateManageService {

    @Autowired
    private PaperDAO paperDAO;

    @Autowired
    private PaperSubjectDAO paperSubjectDAO;

    @Autowired
    private PaperSubjectAnswerDAO paperSubjectAnswerDAO;

    /**
     * 模板查询
     * @param templateDTO 模板查询条件
     * @return 符合条件的模板列表
     */
    @Override
    public List<TemplateListDTO> searchTemplateService(TemplateSearchDTO templateDTO) {
        List<Paper> papers=paperDAO.selectTemplate(templateDTO);
        return ConvertUtil.convert(papers,TemplateListDTO.class);
    }

    /**
     * 获取模板份数
     * @param templateDTO 模板查询条件
     * @return 符合条件的模板份数
     */
    @Override
    public int getTemplateCount(TemplateSearchDTO templateDTO) {
        return paperDAO.selectTemplateCount(templateDTO);
    }

    /**
     *  修改模板
     * @param paper 要修改的模板信息
     * @param subjects 要修改的试题列表信息
     * @param answers 要修改的答案列表信息
     * @return 修改结果
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 3600,rollbackFor = Exception.class)
    @Override
    public int modifyTemplateService(PaperModifyDTO paper, List<SubjectModifyDTO> subjects, List<AnswerDTO> answers) {
        if(paperDAO.modifyPaper(paper)<=0){
            return -1;
        }
        for(SubjectModifyDTO subject: subjects){
            if(paperSubjectDAO.modifySubject(subject)<=0){
                return -1;
            }
        }
        for(AnswerDTO answer: answers){
            if(paperSubjectAnswerDAO.modifyAnswer(answer)<=0){
                return -1;
            }
        }
        return 1;
    }


}
