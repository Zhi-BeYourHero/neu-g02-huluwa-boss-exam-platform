package com.boss.bes.paper.controller;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;

import com.boss.bes.paper.api.ExamCenterApi;
import com.boss.bes.paper.pojo.dto.exam.PaperCompanyListDTO;
import com.boss.bes.paper.pojo.dto.exam.PaperContentExamDTO;

import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperSubjectDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectSearchDTO;
import com.boss.bes.paper.pojo.vo.managepaper.crud.*;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectSearchVO;
import com.boss.bes.paper.service.util.ConvertUtil;

import com.boss.bes.paper.utils.exception.PaperException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.service.ExamCenterService;

import com.boss.bes.paper.service.ParameterConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.controller
 * @Description:
 * @Date: 2020/7/12 9:15
 * @since: 1.0
 */
//@Api("提供给考试服务的试卷服务接口")
@RestController
@RequestMapping("/for-exam-center")
public class ExamCenterController extends AbstractBaseController implements ExamCenterApi {

    @Autowired
    private ExamCenterService examCenterService;

    @Autowired
    private ParameterConvertService parameterConvertService;

    /**
     *  获取试卷列表
     * @param request 带有公司id的试卷查询请求参数
     * @return 试卷列表
     */
    @Override
    @ApiOperation("获取试卷列表")
    @PostMapping(value="/getPapers")
    public CommonResponse<List<PaperListVO>> doExamGetPapers(@Valid @RequestBody CommonRequest<PaperQueryVO> request){
        List<Paper> papers= examCenterService.getPaperList(request.getBody().getId());
        List<PaperListVO> paperListVos =ConvertUtil.convert(papers,PaperListVO.class);
        return CommonResponseUtil.success(paperListVos);
    }

    /**
     *  试卷发布次数增加
     * @param request 带有试卷id的请求参数
     * @return 增加结果
     */
    @Override
    @ApiOperation("发布次数增加")
    @PostMapping(value="/publishTimesAdd")
    public CommonResponse<Boolean> doExamPublishTimesAdd(@Valid @RequestBody CommonRequest<Long> request){
        if(examCenterService.modifyPublishTimes(request.getBody())>0){
            return CommonResponseUtil.success(true);
        }
        throw new PaperException("220603","发布次数修改失败");
    }

    /**
     *  获取试卷内容
     * @param request 带有试卷id的请求参数
     * @return 试卷内容
     */
    @Override
    @ApiOperation("获取试卷内容")
    @PostMapping(value="/getPaperInfo")
    public CommonResponse<PaperContentExamVO> doExamGetPaperInfo(@Valid @RequestBody CommonRequest<Long> request){
        Long id = request.getBody();
        PaperContentExamDTO paperContentDTO =examCenterService.getPaperInfoService(id);
        PaperContentExamVO paperContentVO =ConvertUtil.createAndFill(paperContentDTO, PaperContentExamVO.class);

        return CommonResponseUtil.success(paperContentVO);
    }

    /**
     *   获取指定试卷的指定题目类型的题目
     * @param request 带有试卷id列表和题目类型的请求参数
     * @return 题目列表
     */
    @Override
    @ApiOperation("获取指定类型的试卷题目")
    @PostMapping(value = "/getPapersSubjects")
    public CommonResponse<Map<Long,PaperSubjectDTO>> doGetPapersSubjects(@Valid @RequestBody CommonRequest<SubjectSearchVO> request){
        SubjectSearchDTO subjectSearchDTO=new SubjectSearchDTO();

        subjectSearchDTO.setPaperList(request.getBody().getPaperList());
        //将字符串类型的题目类型数据转换为对应的长整数类型数据
        subjectSearchDTO.setSubjectType(request.getBody().getSubjectType());

        //将Dto数据转换为VO数据

        return  CommonResponseUtil.success(examCenterService.getPapersSubjects(subjectSearchDTO));
    }


    /**
     * 获取所有试卷id及对应公司id和试卷名称
     * @return Map
     */
    @Override
    @ApiOperation("获取所有试卷id及对应公司id和试卷名称")
    @PostMapping(value = "/getPaperAllIds")
    public CommonResponse<Map<Long, PaperCompanyListDTO>> doGetPaperAllIds(){
        return CommonResponseUtil.success(examCenterService.getAllNeedId());
    }
}
