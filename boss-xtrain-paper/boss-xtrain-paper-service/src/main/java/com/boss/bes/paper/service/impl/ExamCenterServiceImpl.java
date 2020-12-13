package com.boss.bes.paper.service.impl;

import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.paper.dao.PaperDAO;
import com.boss.bes.paper.dao.PaperSubjectDAO;
import com.boss.bes.paper.dao.PaperSubjectAnswerDAO;
import com.boss.bes.paper.pojo.dto.exam.PaperCompanyListDTO;
import com.boss.bes.paper.pojo.dto.exam.PaperContentExamDTO;

import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;

import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSubjectDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectSearchDTO;

import com.boss.bes.paper.service.BaseDataService;
import com.boss.bes.paper.service.ExamCenterService;
import com.boss.bes.paper.service.util.ConvertUtil;
import com.boss.bes.paper.service.util.PropertyTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.impl
 * @Description:
 * @Date: 2020/7/9 12:07
 * @since: 1.0
 */
@Service
public class ExamCenterServiceImpl implements ExamCenterService {

    @Autowired
    private PaperDAO paperDAO;

    @Autowired
    private PaperSubjectDAO paperSubjectDAO;

    @Autowired
    private PaperSubjectAnswerDAO paperSubjectAnswerDAO;

    @Autowired
    private BaseDataService baseDataService;


    /**
     * 获取试卷详细描述信息
     * @param id 试卷id
     * @return 试卷信息
     */
    @Override
    public PaperContentExamDTO getPaperInfoService(Long id) {
        PaperContentExamDTO paperContentDTO =new PaperContentExamDTO();
        Paper paper=paperDAO.selectOnePaper(id);
        ConvertUtil.fill(paper, paperContentDTO);
        //获取试卷题目
        List<PaperSubject> subjects=paperSubjectDAO.selectPaperSubjects(id);

        //主观题
        List<PaperSubject> subjects1=new ArrayList<>();
        //客观题
        List<PaperSubject> subjects2=new ArrayList<>();

        CommonResponse<List<String>> response = baseDataService.doGetAllSubjectTypeName();
        List<String> subjectTypes = response.getBody();
        for(PaperSubject paperSubject:subjects){
            if(subjectTypes.contains(paperSubject.getSubjectType())){
                subjects1.add(paperSubject);
            }else{
                subjects2.add(paperSubject);
            }
        }

        List<PaperSubjectDTO> subjectsDTO1=ConvertUtil.convert(subjects1,PaperSubjectDTO.class);
        List<PaperSubjectDTO> subjectsDTO2=ConvertUtil.convert(subjects2,PaperSubjectDTO.class);
        List<Long> subjectIds1=new ArrayList<>();
        List<Long> subjectIds2=new ArrayList<>();
        for(PaperSubject subject:subjects1){
            subjectIds1.add(subject.getId());
        }
        for(PaperSubject subject:subjects2){
            subjectIds2.add(subject.getId());
        }
        //获取题目答
        List<PaperSubjectAnswer> answers1=paperSubjectAnswerDAO.selectSubjectAnswer(subjectIds1);
        List<PaperSubjectAnswer> answers2=paperSubjectAnswerDAO.selectSubjectAnswer(subjectIds2);
        //设置题目的答案
        PropertyTransfer.setSubjectAnswer(subjectsDTO1,answers1);
        PropertyTransfer.setSubjectAnswer(subjectsDTO2,answers2);
        paperContentDTO.setSubjects1(subjectsDTO1);
        paperContentDTO.setSubjects2(subjectsDTO2);
        return paperContentDTO;
    }


    /**
     *  根据公司id获取试卷列表
     * @param companyId 公司id
     * @return 试卷列表
     */
    @Override
    public List<Paper> getPaperList(Long companyId) {
        return paperDAO.selectPaperByCompanyId(companyId);
    }

    /**
     *  修改试卷发布次数
     * @param id 试卷id
     * @return 修改结果
     */
    @Override
    public int modifyPublishTimes(Long id) {
        Paper paper=paperDAO.selectOnePaper(id);
        PaperModifyDTO paperModifyDTO=new PaperModifyDTO();
        paperModifyDTO.setId(id);
        if(paper.getPublishTimes() == null){
            paper.setPublishTimes(0);
        }
        paperModifyDTO.setPublishTimes(paper.getPublishTimes()+1);
        paperModifyDTO.setStatus(1);
        return paperDAO.modifyPaper(paperModifyDTO);
    }

    /**
     *  根据试卷id列表和题型获取指定题目
     * @param subjectSearchDTO 带有试卷id列表和题目类型的参数
     * @return 题目列表
     */
    @Override
    public Map<Long,PaperSubjectDTO> getPapersSubjects(SubjectSearchDTO subjectSearchDTO) {
        List<Long> subjectIds=new ArrayList<>();
        Map<Long,PaperSubjectDTO> result = new HashMap<>();
        //获取指定类型的试卷题目
        List<PaperSubject> subjects1=
                paperSubjectDAO.selectSubjectsByType(subjectSearchDTO.getPaperList(),subjectSearchDTO.getSubjectType());
        for(PaperSubject subject:subjects1){
            subjectIds.add(subject.getId());
        }
        //获取题目的答案
        List<PaperSubjectDTO> paperSubjectDtos=ConvertUtil.convert(subjects1,PaperSubjectDTO.class);
        if(!subjectIds.isEmpty()){
            List<PaperSubjectAnswer> answers=paperSubjectAnswerDAO.selectSubjectAnswer(subjectIds);
            PropertyTransfer.setSubjectAnswer(paperSubjectDtos,answers);
        }
        for(PaperSubjectDTO paperSubjectDTO:paperSubjectDtos){
            result.put(paperSubjectDTO.getId(),paperSubjectDTO);
        }
        return result;
    }



    public Map<Long, PaperCompanyListDTO> getAllNeedId(){
        Map<Long, PaperCompanyListDTO> result = new HashMap<>();

        List<PaperCompanyListDTO> paperCompanyListDTOs = paperDAO.selectAllPaper();


        for(PaperCompanyListDTO paperCompanyListDTO:paperCompanyListDTOs){
            result.put(paperCompanyListDTO.getId(), paperCompanyListDTO);

        }
        return result;
    }
}
