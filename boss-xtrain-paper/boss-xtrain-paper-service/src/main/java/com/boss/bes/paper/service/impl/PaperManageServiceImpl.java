package com.boss.bes.paper.service.impl;

import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.dao.PaperDAO;
import com.boss.bes.paper.dao.PaperSubjectDAO;
import com.boss.bes.paper.dao.PaperSubjectAnswerDAO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperDescriptionDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperDeleteDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSearchDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperListDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperContentDTO;
import com.boss.bes.paper.pojo.dto.managepaper.answer.AnswerDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectInsertDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSubjectDTO;

import com.boss.bes.paper.service.exception.PaperServiceException;
import com.boss.bes.paper.service.util.ConvertUtil;

import com.boss.bes.paper.service.util.SetCommonField;
import com.boss.bes.paper.service.util.PropertyTransfer;

import com.boss.bes.paper.service.PaperManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.impl
 * @Description:
 * @Date: 2020/7/8 20:02
 * @since: 1.0
 */
@Slf4j
@Service
public class PaperManageServiceImpl implements PaperManageService {

    @Autowired
    private PaperDAO paperDAO;

    @Autowired
    private PaperSubjectDAO paperSubjectDAO;

    @Autowired
    private PaperSubjectAnswerDAO paperSubjectAnswerDAO;

    private static final int PAPER_ON_UN_UPLOAD_STATUS=2;
    private static final int PAPER_ON_DISABLE_STATUS=0;
    private static final int ANSWER_ENABLE_STATUS =1;
    private static final int SUBJECT_ENABLE_STATUS =1;


    /**
     *  删除试卷
     * @param id 试卷id
     * @param version 试卷版本
     * @return 删除结果
     */
    @Override
    public int deletePaperService(Long id,Long version) {
        Paper paper=paperDAO.selectOnePaper(id);
        if(paper!=null){
            if(!version.equals(paper.getVersion())){
                throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_PAPER_VERSION_ERROR");
            }
            return paperDAO.deletePaper(id);
        }
        throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_PAPER_NOT_FOUND_ERROR");
    }

    /**
     *  批量删除试卷
     * @param paperDeleteDTO 带有id列表和version列表的参数
     * @return 删除结果
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 3600,rollbackFor = Exception.class)
    @Override
    public int deleteMorePaperService(PaperDeleteDTO paperDeleteDTO) {
        for(int i=0;i<paperDeleteDTO.getId().size();i++){
            if(deletePaperService(paperDeleteDTO.getId().get(i),paperDeleteDTO.getVersion().get(i))<=0){
                return 0;
            }
        }
        return 1;
    }

    /**
     * 判断试卷名是否存在
     * @param name 试卷名
     * @param template 是否为模板
     * @return 判断结果
     */
    @Override
    public boolean ifPaperNameExist(String name,boolean template) {
        Paper paper=new Paper();
        paper.setName(name);
        return !paperDAO.selectPaperByName(paper).isEmpty();
    }

    /**
     * 获取试卷信息
     * @param id 试卷id
     * @return 试卷信息
     */
    @Override
    public PaperDescriptionDTO getPaperInfo(Long id){
        Paper paper=paperDAO.selectOnePaper(id);
        return  ConvertUtil.createAndFill(paper, PaperDescriptionDTO.class);
    }

    /**
     *  新增答案
     * @param answerDTO 新增的答案数据
     * @return 新增结果
     */
    //@SetCommonField(methodType = "insert")
    @Override
    public int insertAnswer(AnswerDTO answerDTO){

        SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
        Long nid = snowFlakeUtil.nextId();
        answerDTO.setId(nid);
        answerDTO.setStatus(ANSWER_ENABLE_STATUS);
        PaperSubjectAnswer answer=ConvertUtil.createAndFill(answerDTO,PaperSubjectAnswer.class);
        return paperSubjectAnswerDAO.insertOneAnswer(answer);
    }


    /**
     *  新增题目
     * @param subjectInsertDTO 新增的题目数据
     * @return 新增结果
     */
    //@SetCommonField(methodType = "insert")
    @Override
    public int insertSubject(SubjectInsertDTO subjectInsertDTO) {

        SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
        Long nid = snowFlakeUtil.nextId();
        subjectInsertDTO.setId(nid);
        subjectInsertDTO.setStatus(SUBJECT_ENABLE_STATUS);
        return paperSubjectDAO.insertSubject(subjectInsertDTO);
    }



    /**
     *  修改答案
     * @param answerDTO 要进行修改的答案数据
     * @return 修改结果
     */
    //@SetCommonField(methodType = "update")
    @Override
    public int modifyAnswer(AnswerDTO answerDTO) {
        PaperSubjectAnswer answer=paperSubjectAnswerDAO.selectOneAnswer(answerDTO.getId());
        if(answer!=null){
            //判断版本是否匹配
            if(answer.getVersion().equals(answerDTO.getVersion())){
                answerDTO.setVersion(answerDTO.getVersion()+1);
                PaperSubjectAnswer subjectAnswer=ConvertUtil.createAndFill(answerDTO,PaperSubjectAnswer.class);
                return paperSubjectAnswerDAO.modifyAnswer(subjectAnswer);
            }else{
                throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_ANSWER_NOT_FOUND_ERROR");
            }
        }
        throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_ANSWER_NOT_FOUND_ERROR");
    }

    /**
     *  修改题目
     * @param subjectModifyDTO 要进行修改的题目数据
     * @return 修改结果
     */
    //@SetCommonField(methodType = "update")
    @Override
    public int modifySubject(SubjectModifyDTO subjectModifyDTO) {
        PaperSubject paperSubject=paperSubjectDAO.selectOneSubject(subjectModifyDTO.getId());
        if(paperSubject!=null){
            //判断版本匹配
            if(paperSubject.getVersion().equals(subjectModifyDTO.getVersion())){
                PaperSubject subject=ConvertUtil.createAndFill(subjectModifyDTO,PaperSubject.class);
                return paperSubjectDAO.modifySubject(subject);
            }else{
                throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_VERSION_ERROR");
            }
        }
        throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_SUBJECT_NOT_FOUND_ERROR");
    }

    /**
     *  修改试卷
     * @param paperModifyDTO 要进行修的试卷数据
     * @return 修改结果
     */
    //@SetCommonField(methodType = "update")
    @Override
    public int modifyPaper(PaperModifyDTO paperModifyDTO) {
        Paper paper=paperDAO.selectOnePaper(paperModifyDTO.getId());
        if(paper!=null){
            //判断版本匹配
            if(!paper.getVersion().equals(paperModifyDTO.getVersion())){
                throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_VERSION_ERROR");
            }
            //判断试卷名是否重复
            else if(!paper.getName().equals(paperModifyDTO.getName())&&ifPaperNameExist(paperModifyDTO.getName(),false)){
                throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_PAPER_NAME_EXIST_ERROR");
            }else{
                //试卷总分更新
                BigDecimal bigDecimal=BigDecimal.valueOf(getScoreSum(paperModifyDTO.getId()));
                paperModifyDTO.setScore(bigDecimal);
                paperModifyDTO.setVersion(paperModifyDTO.getVersion()+1);
                Paper paper1=ConvertUtil.createAndFill(paperModifyDTO,Paper.class);
                return paperDAO.modifyPaper(paper1);
            }
        }
        throw new PaperServiceException("PAPER_CENTER_PAPER_MANAGE_PAPER_NOT_FOUND_ERROR");
    }

    /**
     * 更新获取试卷总分
     * @param paperId 试卷id
     * @return 试卷总分
     */
    private double getScoreSum(Long paperId){
        double scoreSum = 0;
        List<PaperSubject> subjects=paperSubjectDAO.selectPaperSubjects(paperId);
        for(PaperSubject subject:subjects){
            scoreSum+=subject.getScore().doubleValue();
        }
        return scoreSum;
    }




    /**
     *  删除题目
     * @param id 题目id
     * @return 删除结果
     */
    @Override
    public int deleteOneSubject(Long id) {
        return paperSubjectDAO.deleteOneSubject(id);
    }

    /**
     * 获取试卷详细描述信息
     * @param id 试卷id
     * @return 试卷信息
     */
    @Override
    public PaperContentDTO getPaperInfoService(Long id) {
        PaperContentDTO paperContentDTO =new PaperContentDTO();
        Paper paper=paperDAO.selectOnePaper(id);
        ConvertUtil.fill(paper, paperContentDTO);
        //获取试卷题目
        List<PaperSubject> subjects=paperSubjectDAO.selectPaperSubjects(id);
        List<PaperSubjectDTO> subjectsDTO=ConvertUtil.convert(subjects,PaperSubjectDTO.class);
        List<Long> subjectIds=new ArrayList<>();
        for(PaperSubject subject:subjects){
            subjectIds.add(subject.getId());
        }
        //获取题目答案
        List<PaperSubjectAnswer> answers=paperSubjectAnswerDAO.selectSubjectAnswer(subjectIds);
        //设置题目的答案
        PropertyTransfer.setSubjectAnswer(subjectsDTO,answers);
        paperContentDTO.setSubjects(subjectsDTO);
        return paperContentDTO;
    }

    /**
     *  删除答案
     * @param id 删除结果
     * @return 删除结果
     */
    @Override
    public int deleteOneAnswer(Long id) {
        return paperSubjectAnswerDAO.deleteAnswer(id);
    }

    /**
     * 获取试卷份数
     * @param condition 试卷查询条件
     * @return 符合查询条件的试卷总份数
     */
    @Override
    public int getPaperCount(PaperSearchDTO condition) {
        return paperDAO.selectPaperCount(condition);
    }

    /**
     *  根据条件查询试卷
     * @param condition 试卷查询条件
     * @return 符合查询条件的试卷列表
     */
    @Override
    public List<PaperListDTO> searchPaperByConditionService(PaperSearchDTO condition) {
        List<Paper> paperList=paperDAO.selectPaper(condition);
        return ConvertUtil.convert(paperList,PaperListDTO.class);
    }


    /**
     *  修改试卷禁用状态
     * @param id 试卷id
     * @return 修改结果
     */
    @Override
    public Boolean modifyPaperStatusDisable(Long id) {
        Paper paper=paperDAO.selectOnePaper(id);
        if(paper!=null&&paper.getStatus()!=PAPER_ON_DISABLE_STATUS){
            PaperModifyDTO paperModifyDTO=new PaperModifyDTO();
            paperModifyDTO.setId(id);
            paperModifyDTO.setStatus(PAPER_ON_DISABLE_STATUS);
            return paperDAO.modifyPaper(paperModifyDTO)>0;
        }
        return false;
    }

    /**
     *  修改试卷启用状态
     * @param id 试卷id
     * @return 修改结果
     */
    @Override
    public Boolean modifyPaperStatusEnable(Long id) {
        Paper paper=paperDAO.selectOnePaper(id);
        if(paper.getStatus()==PAPER_ON_DISABLE_STATUS){
            PaperModifyDTO paperModifyDTO=new PaperModifyDTO();
            paperModifyDTO.setId(id);
            paperModifyDTO.setStatus(PAPER_ON_UN_UPLOAD_STATUS);
            return paperDAO.modifyPaper(paperModifyDTO)>0;
        }
        return false;
    }

}
