package com.boss.bes.paper.service;

import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperDescriptionDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperDeleteDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSearchDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperListDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperContentDTO;
import com.boss.bes.paper.pojo.dto.managepaper.answer.AnswerDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectInsertDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectModifyDTO;


import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/8 19:59
 * @since: 1.0
 */
public interface PaperManageService {

    /**
     * 判断试卷名是否存在
     * @param name 试卷名
     * @param template 是否为模板
     * @return 判断结果
     */
    boolean ifPaperNameExist(String name,boolean template);

    /**
     *  删除试卷
     * @param id 试卷id
     * @param version 试卷版本
     * @return 删除结果
     */
    int deletePaperService(Long id,Long version);

    /**
     * 获取试卷信息
     * @param id 试卷id
     * @return 试卷信息
     */
    PaperDescriptionDTO getPaperInfo(Long id);

    /**
     *  新增答案
     * @param answerDTO 新增的答案数据
     * @return 新增结果
     */
    int insertAnswer(AnswerDTO answerDTO);

    /**
     *  新增题目
     * @param subjectInsertDTO 新增的题目数据
     * @return 新增结果
     */
    int insertSubject(SubjectInsertDTO subjectInsertDTO);

    /**
     *  修改答案
     * @param answerDTO 要进行修改的答案数据
     * @return 修改结果
     */
    int modifyAnswer(AnswerDTO answerDTO);

    /**
     *  修改题目
     * @param subjectModifyDTO 要进行修改的题目数据
     * @return 修改结果
     */
    int modifySubject(SubjectModifyDTO subjectModifyDTO);

    /**
     *  修改试卷
     * @param paperModifyDTO 要进行修的试卷数据
     * @return 修改结果
     */
    int modifyPaper(PaperModifyDTO paperModifyDTO);


    /**
     * 获取试卷份数
     * @param condition 试卷查询条件
     * @return 符合查询条件的试卷总份数
     */
    int getPaperCount(PaperSearchDTO condition);

    /**
     *  批量删除试卷
     * @param paperDeleteDTO 带有id列表和version列表的参数
     * @return 删除结果
     */
    int deleteMorePaperService(PaperDeleteDTO paperDeleteDTO);

    /**
     *  删除题目
     * @param id 题目id
     * @return 删除结果
     */
    int deleteOneSubject(Long id);

    /**
     * 获取试卷详细描述信息
     * @param id 试卷id
     * @return 试卷信息
     */
    PaperContentDTO getPaperInfoService(Long id);

    /**
     *  删除答案
     * @param id 删除结果
     * @return 删除结果
     */
    int deleteOneAnswer(Long id);

    /**
     *  根据条件查询试卷
     * @param condition 试卷查询条件
     * @return 符合查询条件的试卷列表
     */
    List<PaperListDTO> searchPaperByConditionService(PaperSearchDTO condition);

    /**
     *  修改试卷禁用状态
     * @param id 试卷id
     * @return 修改结果
     */
    Boolean modifyPaperStatusDisable(Long id);

    /**
     *  修改试卷启用状态
     * @param id 试卷id
     * @return 修改结果
     */
    Boolean modifyPaperStatusEnable(Long id);

}
