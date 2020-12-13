package com.boss.bes.paper.api;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.paper.pojo.dto.exam.PaperCompanyListDTO;

import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSubjectDTO;
import com.boss.bes.paper.pojo.vo.managepaper.crud.PaperContentExamVO;

import com.boss.bes.paper.pojo.vo.managepaper.crud.PaperListVO;
import com.boss.bes.paper.pojo.vo.managepaper.crud.PaperQueryVO;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectSearchVO;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.api
 * @Description:
 * @Date: 2020/7/12 12:38
 * @since: 1.0
 */
@RequestMapping("/paper-center/for-exam-center")
public interface ExamCenterApi {

    /**
     *  获取试卷列表
     * @param request 带有公司id的试卷查询请求参数
     * @return 试卷列表
     */
    @PostMapping(value="/getPapers")
    CommonResponse<List<PaperListVO>> doExamGetPapers(@Valid @RequestBody CommonRequest<PaperQueryVO> request);

    /**
     *  试卷发布次数增加
     * @param request 带有试卷id的请求参数
     * @return 增加结果
     */
    @PostMapping(value="/publishTimesAdd")
    CommonResponse<Boolean> doExamPublishTimesAdd(@Valid @RequestBody CommonRequest<Long> request);

    /**
     *  获取试卷内容
     * @param request 带有试卷id的请求参数
     * @return 试卷内容
     */
    @PostMapping(value="/getPaperInfo")
    CommonResponse<PaperContentExamVO> doExamGetPaperInfo(@Valid @RequestBody CommonRequest<Long> request);

    /**
     *   获取指定试卷的指定题目类型的题目
     * @param request 带有试卷id列表和题目类型的请求参数
     * @return 题目列表
     */
    @PostMapping(value = "/getPapersSubjects")
    CommonResponse<Map<Long, PaperSubjectDTO>> doGetPapersSubjects(@Valid @RequestBody CommonRequest<SubjectSearchVO> request);




    /**
     * 获取所有试卷id及对应公司id和试卷名称
     * @return Map
     */
    @PostMapping(value = "/getPaperAllIds")
    CommonResponse<Map<Long, PaperCompanyListDTO>> doGetPaperAllIds();
}
