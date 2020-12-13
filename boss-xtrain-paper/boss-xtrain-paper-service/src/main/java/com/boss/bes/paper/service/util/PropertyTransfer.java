package com.boss.bes.paper.service.util;

import boss.xtrain.core.data.convention.common.CommonResponse;

import boss.xtrain.util.id.SnowFlakeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.pojo.entity.PaperSubject;

import com.boss.bes.paper.pojo.dto.makepaper.MakePaperDTO;
import com.boss.bes.paper.pojo.dto.makepaper.SubjectsDTO;
import com.boss.bes.paper.pojo.dto.makepaper.ConfigDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSubjectDTO;
import com.boss.bes.paper.pojo.dto.managepaper.answer.AnswerDTO;
import com.boss.bes.paper.pojo.vo.managepaper.crud.PaperSubjectItemVO;
import com.boss.bes.paper.pojo.vo.managepaper.crud.SubjectAnswerItemVO;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/9 11:10
 * @since: 1.0
 */
public class PropertyTransfer {

    private PropertyTransfer(){}


    /**
     * 答案信息批量修改
     * @param subjectId 题目id
     * @param answers  答案列表
     * @param companyId  公司id
     * @param subject 题目
     */
    private static void setAnswer(Long subjectId,List<PaperSubjectAnswer> answers,Long companyId,PaperSubject subject){
        for(PaperSubjectAnswer answer:answers){
            if(answer.getSubjectId().equals(subject.getId())){

                SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
                Long nid = snowFlakeUtil.nextId();
                answer.setId(nid);
                answer.setVersion(1L);
                answer.setSubjectId(subjectId);
                answer.setCompanyId(companyId);
                if(answer.getImageUrl()==null){
                    answer.setImageUrl("");
                }
            }
        }
    }

    /**
     * 试卷上传下载的试卷题目信息的修改
     * @param subjects 题目列表
     * @param companyId 公司id
     * @param answers 答案列表
     * @param id 试卷id
     */
    public  static  void setSubjectAndAnswer(List<PaperSubject> subjects, List<PaperSubjectAnswer> answers,
                                             Long id, Long companyId){
        for(PaperSubject subject:subjects){

            SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
            Long subjectId = snowFlakeUtil.nextId();
            setAnswer(subjectId,answers,companyId,subject);

            subject.setId(subjectId);
            subject.setPaperId(id);

            subject.setCompanyId(companyId);
        }
    }


    /**
     *  组卷功能中的试卷信息修改填充
     */
    public static void setSubjectAndAnswer(List<PaperSubject> subjects, List<PaperSubjectAnswer> answers
            , MakePaperDTO paperDTO,Long id,Long companyId){
        for(SubjectsDTO subject : paperDTO.getSubjects()){
            PaperSubject paperSubject= ConvertUtil.createAndFill(subject,PaperSubject.class);
            paperSubject.setSubject(subject.getName());
            paperSubject.setPaperId(id);
            paperSubject.setStatus(1);
            FillPublicField.fill(paperSubject);
            if(paperSubject.getCompanyId()==null||paperSubject.getCompanyId()==0L){
                paperSubject.setCompanyId(companyId);
            }
            for(PaperSubjectAnswer answer1:subject.getAnswers()){
                PaperSubjectAnswer answer=ConvertUtil.createAndFill(answer1,PaperSubjectAnswer.class);
                answer.setSubjectId(paperSubject.getId());
                answer.setStatus(1);
                FillPublicField.fill(answer);
                if(answer.getCompanyId()==null||answer.getCompanyId()==0L){
                    answer.setCompanyId(companyId);
                }
                answers.add(answer);
            }
            subjects.add(paperSubject);
        }
    }

    /**
     * 获取试卷总分
     * @param subjects 题目列表
     * @return 总分
     */
    public static BigDecimal getScoreSum(List<PaperSubject> subjects){
        double scoreSum = 0;
        for(PaperSubject subject:subjects){
            scoreSum+=subject.getScore().doubleValue();
        }
        return BigDecimal.valueOf(scoreSum);
    }

    /**
     * 将配置参数从JSON 形式转换为DTO参数
     * @param response 调取服务响应的数据
     * @return 配置列表
     */
    public static List<ConfigDTO> transferResponseToList(CommonResponse response){
        String responseString= JSON.toJSONString(response.getBody());
        JSONObject obj=com.alibaba.fastjson.JSON.parseObject(responseString);
        return JSON.parseArray(JSON.toJSONString(obj.get("list")),ConfigDTO.class);
    }

    public static List<ConfigDTO> transferResponseToRow(CommonResponse response){
        String responseString= JSON.toJSONString(response.getBody());
        JSONObject obj=com.alibaba.fastjson.JSON.parseObject(responseString);
        return JSON.parseArray(JSON.toJSONString(obj.get("rows")),ConfigDTO.class);
    }





    /**
     * 将配置列表总条数从JSON转换为long类型
     * @param response 调取服务响应的数据
     * @return 配置总份数
     */
    public static long transferResponseToLong(CommonResponse response){
        String responseString= JSON.toJSONString(response.getBody());
        JSONObject obj=com.alibaba.fastjson.JSON.parseObject(responseString);
        return obj.getLongValue("total");
    }


    /**
     * 将答案分配到对应的题目下
     * @param paperSubjectList 题目列表
     * @param answers 待分配的答案列表
     */
    public static void setSubjectAnswer(List<PaperSubjectDTO> paperSubjectList, List<PaperSubjectAnswer> answers){
        for(PaperSubjectDTO paperSubject: paperSubjectList){
            List<AnswerDTO> answerList=new ArrayList<>();
            for(PaperSubjectAnswer answer: answers){
                if(paperSubject.getId().equals(answer.getSubjectId())){
                    answerList.add(ConvertUtil.createAndFill(answer,AnswerDTO.class));
                }
            }
            paperSubject.setAnswers(answerList);
        }
    }

    public static void setAnswer(List<PaperSubjectDTO> paperSubjectList, List<PaperSubjectItemVO> paperSubjectItemVos){
        for(PaperSubjectDTO paperSubject: paperSubjectList){
            for(PaperSubjectItemVO subjectItemVO: paperSubjectItemVos){
                if(paperSubject.getId().equals(subjectItemVO.getId())){
                    subjectItemVO.setAnswers(ConvertUtil.convert(paperSubject.getAnswers(), SubjectAnswerItemVO.class));
                }
            }
        }
    }


}
