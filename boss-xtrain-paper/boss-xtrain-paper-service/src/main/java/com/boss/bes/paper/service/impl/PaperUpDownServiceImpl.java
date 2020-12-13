package com.boss.bes.paper.service.impl;

import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.dao.PaperDAO;
import com.boss.bes.paper.dao.PaperSubjectDAO;
import com.boss.bes.paper.dao.PaperSubjectAnswerDAO;

import com.boss.bes.paper.service.PaperManageService;
import com.boss.bes.paper.pojo.dto.updownpaper.PaperUpDownloadDTO;
import com.boss.bes.paper.service.PaperUpDownService;
import com.boss.bes.paper.service.exception.PaperServiceException;
import com.boss.bes.paper.service.util.PropertyTransfer;
import com.boss.bes.paper.service.util.ConvertUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.impl
 * @Description:
 * @Date: 2020/7/9 12:34
 * @since: 1.0
 */
@Service
public class PaperUpDownServiceImpl implements PaperUpDownService {

    @Autowired
    private PaperDAO paperDAO;
    @Autowired
    private PaperSubjectDAO paperSubjectDAO;
    @Autowired
    private PaperSubjectAnswerDAO paperSubjectAnswerDAO;
    @Autowired
    private PaperManageService paperManageService;

    private static final int TEMPLATE_STATUS =1;
    private static final int UN_UPLOAD_STATUS=2;
    private static final int UPLOAD_STATUS=3;

    /**
     *  试卷上传服务
     * @param paperData 上传的试卷数据
     * @return 上传结果
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 3600,rollbackFor = Exception.class)
    @Override
    public int uploadPaperService(PaperUpDownloadDTO paperData) {
        //判断模板名是否已经存在
        if((paperManageService.ifPaperNameExist(paperData.getName(),true))){
            throw new PaperServiceException("PAPER_CENTER_PAPER_UPLOAD_TEMPLATE_ENAME_EXIST_ERROR");
        }
        //查找需要上传的试卷数据
        PaperUpDownloadDTO paperUpDownloadDTO= searchForPaper(paperData.getId(),false);
        if(paperUpDownloadDTO!=null){
            //判断试卷状态是否为未上传
            if(paperUpDownloadDTO.getStatus()!=UN_UPLOAD_STATUS){
                throw new PaperServiceException("PAPER_CENTER_PAPER_UPLOAD_PAPER_NOT_NO_UN_UPLOAD_ERROR");
            }
            if(paperDAO.modifyStatus(UPLOAD_STATUS,paperData.getId())<=0){
                throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_PAPER_STATUS_MODIFY_ERROR");
            }
            Paper paper=setTemplate(paperData);
            List<PaperSubject> subjectList=paperUpDownloadDTO.getSubjects();
            List<PaperSubjectAnswer> subjectAnswerList=paperUpDownloadDTO.getAnswers();
            //修改试题以及答案的相关信息
            PropertyTransfer.setSubjectAndAnswer(subjectList,subjectAnswerList,paper.getId(),0L);
            //保存模板数据
            return savePaperData(paper,subjectList,subjectAnswerList);
        }
        return 1;
    }


    /**
     *  试卷下载服务
     * @param paperData 下载的试卷数据
     * @return 下载结果
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 3600,rollbackFor = Exception.class)
    @Override
    public int downloadPaperService(PaperUpDownloadDTO paperData) {
        //判断试卷名是否已经存在
        if((paperManageService.ifPaperNameExist(paperData.getName(),false))){
            throw new PaperServiceException("PAPER_CENTER_PAPER_DOWNLOAD_PAPER_ENAME_EXIST_ERROR");
        }
        //查找需要下载的模板数据
        PaperUpDownloadDTO paperUpDownloadDTO= searchForPaper(paperData.getId(),true);
        if(paperUpDownloadDTO!=null){
            if(paperUpDownloadDTO.getStatus()!=TEMPLATE_STATUS){
                throw new PaperServiceException("PAPER_CENTER_PAPER_DOWNLOAD_TEMPLATE_DISENABLE_ERROR");
            }
            if(paperDAO.modifyDownloadTimes(paperData.getId())<=0){
                throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_PAPER_MODIFY_ERROR");
            }
            Paper paper= setPaper(paperData);
            List<PaperSubject> subjectList=paperUpDownloadDTO.getSubjects();
            List<PaperSubjectAnswer> subjectAnswerList=paperUpDownloadDTO.getAnswers();
            //设置试题以及答案相关信息
            PropertyTransfer.setSubjectAndAnswer(subjectList,subjectAnswerList,paper.getId(),paperData.getCompanyId());
            //保存试卷数据
            return savePaperData(paper,subjectList,subjectAnswerList);
        }
        return 1;
    }

    /**
     * 保存试卷数据
     * @param paper 待保存的试卷数据
     * @param subjects 待保存的题目数据
     * @param answers 待保存的答案数据
     * @return 保存结果
     */
    private int savePaperData(Paper paper,List<PaperSubject> subjects,List<PaperSubjectAnswer> answers){
        //新增试卷
        if(paperDAO.insertOnePaper(paper)<=0){
            throw new PaperServiceException("PAPER_CENTER_PAPER_UPLOAD_TEMPLATE_INSERT_ERROR");
        }
        //批量插入试题
        if(paperSubjectDAO.insertSubjects(subjects)<=0){
            throw new PaperServiceException("PAPER_CENTER_PAPER_UPLOAD_TEMPLATE_SUBJECT_INSERT_ERROR");
        }
        //批量插入答案
        if(paperSubjectAnswerDAO.insertAnswers(answers)<=0){
            throw new PaperServiceException("PAPER_CENTER_PAPER_UPLOAD_TEMPLATE_ANSWER_INSERT_ERROR");
        }
        return 1;
    }

    /**
     * 模板上传前信息修改
     * @param paperData 模板数据
     * @return 修改后的模板
     */
    private Paper setTemplate(PaperUpDownloadDTO paperData){
        Paper tmpPaper=paperDAO.selectOnePaper(paperData.getId());
        Paper paper=ConvertUtil.createAndFill(paperData,Paper.class);
        paper.setTemplate(true);
        paper.setCombExamMan(tmpPaper.getCombExamMan());
        paper.setDownloadTimes(0);
        paper.setStatus(TEMPLATE_STATUS);
        //填充id、版本、组卷人等基本信息

        SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
        Long nid = snowFlakeUtil.nextId();
        paper.setId(nid);
        paper.setVersion(1L);
        paper.setCompanyId(0L);
        return paper;
    }

    /**
     * 试卷下载前信息修改
     * @param paperData 试卷数据
     * @return 修改好的试卷数据
     */
    private Paper setPaper(PaperUpDownloadDTO paperData){
        Paper paper= paperDAO.selectOneTemplate(paperData.getId());
        paper.setName(paperData.getName());
        paper.setCompanyId(paperData.getCompanyId());
        paper.setDownloadTimes(0);
        paper.setTemplate(false);
        paper.setStatus(UN_UPLOAD_STATUS);
        //填充id、版本、组卷人等基本信息

        SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
        Long nid = snowFlakeUtil.nextId();
        paper.setId(nid);
        paper.setVersion(1L);
        paper.setCompanyId((0L));
        return paper;
    }

    /**
     *   查询要操作的试卷/模板数据
     * @param id 试卷/模板id
     * @param template 是否为模板
     * @return 试卷/模板数据
     */
    @Override
    public PaperUpDownloadDTO searchForPaper(Long id, Boolean template) {
        //查询试卷信息
        Paper paper=(Boolean.TRUE.equals(template)? paperDAO.selectOneTemplate(id): paperDAO.selectOnePaper(id));
        //查询试卷试题
        List<PaperSubject> subjects= paperSubjectDAO.selectPaperSubjects(id);
        List<Long> subjectIds=new ArrayList<>();
        for(PaperSubject subject:subjects){
            subjectIds.add(subject.getId());
        }
        //查询试题答案
        List<PaperSubjectAnswer> answers=paperSubjectAnswerDAO.selectSubjectAnswer(subjectIds);
        PaperUpDownloadDTO paperUpDownloadDTO =ConvertUtil.createAndFill(paper, PaperUpDownloadDTO.class);
        paperUpDownloadDTO.setSubjects(subjects);
        paperUpDownloadDTO.setAnswers(answers);
        return paperUpDownloadDTO;
    }
}
