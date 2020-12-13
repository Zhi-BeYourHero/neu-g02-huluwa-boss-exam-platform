package com.boss.bes.paper.service;

import com.boss.bes.paper.pojo.dto.exam.PaperCompanyListDTO;
import com.boss.bes.paper.pojo.dto.exam.PaperContentExamDTO;

import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSubjectDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectSearchDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/9 12:07
 * @since: 1.0
 */
public interface ExamCenterService {

    /**
     * 获取试卷详细描述信息
     * @param id 试卷id
     * @return 试卷信息
     */
    PaperContentExamDTO getPaperInfoService(Long id);

    /**
     *  根据公司id获取试卷列表
     * @param companyId 公司id
     * @return 试卷列表
     */
    List<Paper> getPaperList(Long companyId);

    /**
     *  修改试卷发布次数
     * @param id 试卷id
     * @return 修改结果
     */
    int modifyPublishTimes(Long id);

    /**
     *  根据试卷id列表和题型获取指定题目
     * @param subjectSearchDTO 带有试卷id列表和题目类型的参数
     * @return 题目列表
     */
    Map<Long,PaperSubjectDTO> getPapersSubjects(SubjectSearchDTO subjectSearchDTO);



    /**
     * 获取所有试卷id及对应的公司id,试卷名
     * @return Map<试卷id，<公司id,试卷名>>
     */
    Map<Long, PaperCompanyListDTO> getAllNeedId();
}
