package com.boss.bes.paper.service.impl;

import com.alibaba.fastjson.JSON;

import com.boss.bes.paper.pojo.dto.makepaper.MakePaperDTO;
import com.boss.bes.paper.pojo.dto.makepaper.QuickMakePaperDTO;
import com.boss.bes.paper.pojo.dto.makepaper.SubjectsDTO;
import com.boss.bes.paper.pojo.entity.Paper;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.RequestHeader;

import com.boss.bes.paper.dao.PaperDAO;
import com.boss.bes.paper.dao.PaperSubjectDAO;
import com.boss.bes.paper.dao.PaperSubjectAnswerDAO;

import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.service.MakePaperService;
import com.boss.bes.paper.service.UnitNameService;
import com.boss.bes.paper.service.BaseDataService;
import com.boss.bes.paper.service.PaperManageService;
import com.boss.bes.paper.service.util.ConvertUtil;
import com.boss.bes.paper.service.util.FillPublicField;
import com.boss.bes.paper.service.util.PropertyTransfer;
import com.boss.bes.paper.service.util.BaseUser;
import com.boss.bes.paper.service.util.JwtUtil;
import com.boss.bes.paper.service.util.ControllerAspect;
import com.boss.bes.paper.utils.exception.PaperException;
import com.boss.bes.paper.utils.exception.PaperExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigItemReturnVO;
import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigMakeVO;

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
public class MakePaperServiceImpl implements MakePaperService {

    @Autowired
    private PaperDAO paperDAO;
    @Autowired
    private PaperSubjectDAO paperSubjectDAO;
    @Autowired
    private PaperSubjectAnswerDAO paperSubjectAnswerDAO;
    @Autowired
    private PaperManageService paperManageService;
    @Autowired
    private BaseDataService getBaseDataService;
    @Autowired
    private UnitNameService getNameOfUnit;

    private static final int QUICK_TYPE=1;
    private static final int STANDARD_TYPE=1;


    /**
     *  快速组卷服务
     * @param quickMakePaperDTO 带有组卷配置以及试卷信息的快速组卷参数
     * @return 组卷结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 3600,rollbackFor = Exception.class)
    public int quickMakePaper(QuickMakePaperDTO quickMakePaperDTO) {
        //判断试卷名是否已经存在
        if((paperManageService.ifPaperNameExist(quickMakePaperDTO.getName(),false))){
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_PAPER_DOWNLOAD_PAPER_ENAME_EXIST_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
        }
        //调取基础数据服务进行组卷并获取返回的试卷题目数据
        List<SubjectsDTO> subjects= getMakePaperSubjects(quickMakePaperDTO.getHeader()
                ,quickMakePaperDTO.getConfig(),QUICK_TYPE);
        MakePaperDTO makePaperDTO= ConvertUtil.createAndFill(quickMakePaperDTO,MakePaperDTO.class);
        //设置题目分数
        setSubjectScore(quickMakePaperDTO.getConfig().getConfigItems(),subjects);
        makePaperDTO.setSubjects(subjects);
        //保存试卷题目数据
        return savePaperData(makePaperDTO, getNameOfUnit.getNameOfUser(getBaseUser().getUserId()));
    }

    /**
     *  标准组卷服务
     * @param quickMakePaperDTO 带有组卷配置以及试卷信息的标准组卷参数
     * @return 组卷结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 3600,rollbackFor = Exception.class)
    public int standardMakePaper(QuickMakePaperDTO quickMakePaperDTO) {
        //判断试卷名是否已经存在
        if((paperManageService.ifPaperNameExist(quickMakePaperDTO.getName(),false))){
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_PAPER_DOWNLOAD_PAPER_ENAME_EXIST_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
        }
        MakePaperDTO makePaperDTO= ConvertUtil.createAndFill(quickMakePaperDTO,MakePaperDTO.class);
        //调用基础数据标准组卷服务并获取题目数据
        List<SubjectsDTO> subjects= getMakePaperSubjects(quickMakePaperDTO.getHeader()
                ,quickMakePaperDTO.getConfig(),STANDARD_TYPE);
        //设置题目分数
        setSubjectScore(quickMakePaperDTO.getConfig().getConfigItems(),subjects);
        makePaperDTO.setSubjects(subjects);
        //保存试卷数据
        return savePaperData(makePaperDTO, getNameOfUnit.getNameOfUser(getBaseUser().getUserId()));
    }

    /**
     *   保存试卷数据
     * @param paperDTO 要保存的试卷数据
     * @param name 组卷人姓名
     * @return 保存结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 3600,rollbackFor = Exception.class)
    public int savePaperData(MakePaperDTO paperDTO,String name) {
        Paper paper=ConvertUtil.createAndFill(paperDTO,Paper.class);
        FillPublicField.fill(paper);
        paper.setCombExamMan(name);
        paper.setStatus(2);
        List<PaperSubject> subjects=new ArrayList<>();
        List<PaperSubjectAnswer> answers=new ArrayList<>();
        //设置题目的答案
        PropertyTransfer.setSubjectAndAnswer(subjects,answers,paperDTO,paper.getId(),paperDTO.getCompanyId());
        //设置试卷总分
        paper.setScore(PropertyTransfer.getScoreSum(subjects));
        //试卷插入
        if(paperDAO.insertOnePaper(paper)<=0){
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_MAKE_PAPER_PAPER_INSERT_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
        }
        //题目插入
        if(paperSubjectDAO.insertSubjects(subjects)<=0){
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_MAKE_PAPER_SUBJECT_INSERT_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
        }
        //答案插入
        if(paperSubjectAnswerDAO.insertAnswers(answers)<=0){
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_MAKE_PAPER_ANSWER_INSERT_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
        }
        return 1;
    }

    /**
     *  组卷功能中给返回的题目添加分数属性值
     * @param configItems 配置详细项列表
     * @param subjects 题目列表
     */
    private void setSubjectScore(List<ConfigItemReturnVO> configItems, List<SubjectsDTO> subjects){
        for(ConfigItemReturnVO config: configItems){
            int subjectNum=0;
            for(SubjectsDTO subject:subjects){
                if(config.getSubjectTypeName().equals(subject.getSubjectTypeName())
                        &&config.getCategoryName().equals(subject.getCategoryName())
                        && config.getDifficulty().toString().equals(subject.getDifficulty().toString())){
                    subject.setScore(config.getScore());
                    subjectNum++;
                }
            }
            if(subjectNum!=config.getNum()){
                throw new PaperException("230105",
                        config.getCategoryName()+"题型的"+config.getDifficultyName()+"难度的"+config.getSubjectTypeName()+"数量不足，组卷失败");
            }
        }
    }

    /**
     *  获取当前用户信息
     * @return 用户信息
     */
    private BaseUser getBaseUser(){
        BaseUser baseUser = new BaseUser();
        try{
            String token = ControllerAspect.threadLocal.get();
            ConvertUtil.fill(JwtUtil.getJwtModel(token),baseUser);
        }catch(Exception e){
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_PUBLIC_FIELD_FILL_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
        }
        return baseUser;
    }

    /**
     * 调用基础数据服务并获取返回的题目数据
     * @param header 请求头部信息
     * @param config 配置信息
     * @param type 组卷类别（快速/标准）
     * @return 题目列表
     */
    private List<SubjectsDTO> getMakePaperSubjects(RequestHeader header, ConfigMakeVO config, int type){
        CommonRequest<ConfigMakeVO> request=new CommonRequest<>();
        request.setHeader(header);
        request.setBody(config);
        CommonResponse response;
        if(type==QUICK_TYPE){
            //调用基础数据服务快速组卷服务
            response=getBaseDataService.quickMakePaper(request);
        }else{
            //调用基础数据服务标准组卷服务
            response=getBaseDataService.standardMakePaper(request);
        }
        //转换json数据获取返回的题目数据
        List<SubjectsDTO> subjects= JSON.parseArray(response.getBody().toString(),SubjectsDTO.class);
        if(subjects.isEmpty()){
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_MAKE_PAPER_SUBJECT_NOT_FOUND_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
        }
        return subjects;
    }
}
