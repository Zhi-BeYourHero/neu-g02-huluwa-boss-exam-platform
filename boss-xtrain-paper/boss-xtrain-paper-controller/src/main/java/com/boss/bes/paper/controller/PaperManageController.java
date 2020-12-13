package com.boss.bes.paper.controller;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.log.api.annotation.ApiLog;
import com.boss.bes.paper.pojo.dto.managepaper.crud.*;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectInsertDTO;
import com.boss.bes.paper.pojo.dto.managepaper.answer.AnswerDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.boss.bes.paper.exception.PaperExceptionCode;
import com.boss.bes.paper.pojo.vo.managepaper.crud.*;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectModifyVO;
import com.boss.bes.paper.pojo.vo.managepaper.answer.AnswerModifyVO;
import com.boss.bes.paper.pojo.vo.managepaper.answer.AnswerAddVO;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectInsertVO;
import com.boss.bes.paper.service.util.ConvertUtil;
import com.boss.bes.paper.service.ParameterConvertService;

import com.boss.bes.paper.service.util.PropertyTransfer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.boss.bes.paper.service.PaperManageService;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.controller
 * @Description:
 * @Date: 2020/7/9 11:32
 * @since: 1.0
 */
@Api("试卷维护")
@ApiLog(msg = "PaperManageController Log")
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequestMapping("/PaperManage")
public class PaperManageController extends AbstractBaseController{

    @Autowired
    private PaperManageService paperManageService;

    @Autowired
    private ParameterConvertService parameterConvertService;


    /**
     *   获取试卷详细描述信息
     * @param request 带有试卷id的请求参数
     * @return 试卷详细信息
     */
    @ApiOperation("获取试卷详细信息")
    @PostMapping(value="/paperDescription")
    public CommonResponse<PaperDescriptionVO> doGetPaperInfor(@Valid @ApiParam @RequestBody CommonRequest<Long> request) {
        PaperDescriptionDTO paperDescriptDTO=paperManageService.getPaperInfo(request.getBody());
        PaperDescriptionVO paperDescriptionVO=ConvertUtil.createAndFill(paperDescriptDTO, PaperDescriptionVO.class);
        //组卷人、难度等类型数据的转换
        parameterConvertService.fill(paperDescriptDTO,paperDescriptionVO);
        parameterConvertService.fillName(paperDescriptDTO,paperDescriptionVO);
        return CommonResponseUtil.success(paperDescriptionVO);
    }


    /**
     *  试卷删除
     * @param request 带有试卷id和version的请求参数
     * @return 删除结果
     */
    @ApiOperation("删除一份试卷")
    @DeleteMapping(value="/deletePaper")
    public CommonResponse<Boolean> deletePaper(@Valid @ApiParam @RequestBody CommonRequest<PaperDeleteVO> request) {
        Long id=request.getBody().getId().get(0);
        Long version=request.getBody().getVersion().get(0);
        if ((paperManageService.deletePaperService(id,version) <= 0)) {
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_DELETE_PAPER_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_DELETE_PAPER_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *  试卷批量删除
     * @param request 带有id列表和对应的version列表的请求参数
     * @return 删除结果
     */
    @ApiOperation("删除多份试卷")
    @DeleteMapping(value="/deletePapers")
    public CommonResponse<Boolean> deletePapers(@Valid @ApiParam @RequestBody CommonRequest<PaperDeleteVO> request){
        if ((paperManageService.deleteMorePaperService(ConvertUtil.createAndFill(request.getBody(), PaperDeleteDTO.class)) <= 0)) {
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_DELETE_PAPERS_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_DELETE_PAPERS_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *  修改试卷信息
     * @param request 带有试卷修改信息的请求参数
     * @return 修改结果
     */
    @ApiOperation("修改试卷")
    @PutMapping(value="/modifyPaper")
    public CommonResponse<Boolean> modifyPaper(@Valid @ApiParam @RequestBody CommonRequest<PaperModifyVO> request){
        PaperModifyDTO paperModifyDTO=ConvertUtil.createAndFill(request.getBody(),PaperModifyDTO.class);
        if(paperManageService.modifyPaper(paperModifyDTO)<=0){
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_PAPER_MODIFY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_PAPER_MODIFY_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *  修改题目内容
     * @param request 带有题目修改信息的请求参数
     * @return 修改结果
     */
    @ApiOperation("修改题目")
    @PutMapping(value="/modifySubject")
    public CommonResponse<Boolean> doModifySubject(@Valid @ApiParam @RequestBody CommonRequest<SubjectModifyVO> request){
        SubjectModifyDTO subjectModifyDTO=ConvertUtil.createAndFill(request.getBody(), SubjectModifyDTO.class);

        paperManageService.modifySubject(subjectModifyDTO);


        return CommonResponseUtil.success(true);
    }

    /**
     *  修改答案信息
     * @param request 带有答案修改信息的请求参数
     * @return 修改结果
     */
    @ApiOperation("修改答案")
    @PutMapping(value="/modifyAnswer")
    public CommonResponse<Boolean> doModifyAnswer(@Valid @ApiParam @RequestBody CommonRequest<AnswerModifyVO> request){

        return CommonResponseUtil.success(true);
    }

    /**
     *  删除答案
     * @param request 带有答案id的请求参数
     * @return 删除结果
     */
    @ApiOperation("删除答案")
    @DeleteMapping(value="/deleteAnswer")
    public CommonResponse<Boolean> doDeleteOneAnswer(@Valid @ApiParam @RequestBody CommonRequest<Long> request){
        if(paperManageService.deleteOneAnswer(request.getBody())<=0){
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_ANSWER_DELETE_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_ANSWER_DELETE_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *  删除题目
     * @param request 带有试题id的请求参数
     * @return 删除结果
     */
    @ApiOperation("删除题目")
    @DeleteMapping(value = "/deleteSubject")
    public CommonResponse<Boolean> deleteOneSubject(@Valid @ApiParam @RequestBody CommonRequest<Long> request){
        if(paperManageService.deleteOneSubject(request.getBody())<=0){
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_SUBJECT_DELETE_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_SUBJECT_DELETE_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *  添加答案
     * @param request 带有添加的答案信息的请求参数
     * @return 添加结果
     */
    @ApiOperation("添加答案")
    @PostMapping(value = "/addAnswer")
    public CommonResponse<Boolean> addOneAnswer(@Valid @RequestBody CommonRequest<AnswerAddVO> request){
        AnswerDTO answerDTO=ConvertUtil.createAndFill(request.getBody(),AnswerDTO.class);
        if(paperManageService.insertAnswer(answerDTO)<=0){
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_ANSWER_ADD_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_ANSWER_ADD_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *  添加题目
     * @param request 带有添加的题目信息请求参数
     * @return 添加结果
     */
    @ApiOperation("添加题目")
    @PostMapping(value = "/addSubject")
    public CommonResponse<Boolean> addOneSubject(@Valid @RequestBody CommonRequest<SubjectInsertVO> request){
        SubjectInsertDTO subjectInsertDTO=ConvertUtil.createAndFill(request.getBody(),SubjectInsertDTO.class);
        if(paperManageService.insertSubject(subjectInsertDTO)<=0){
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_SUBJECT_ADD_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_SUBJECT_ADD_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *  修改试卷状态
     * @param request 带有试卷id和修改状态的请求参数
     * @return 修改结果
     */
    @ApiOperation("修改试卷禁用状态")
    @PutMapping(value="/modifyPaperStatusEnable")
    public CommonResponse<Boolean> doModifyPaperStatusEnable(@RequestBody CommonRequest<PaperStatusModifyVO> request){
        if(Boolean.TRUE.equals(request.getBody().getStatus())){
            if(Boolean.TRUE.equals(paperManageService.modifyPaperStatusDisable(request.getBody().getId()))){
                return CommonResponseUtil.success(true);
            }
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_PAPER_STATUS_MODIFY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_PAPER_STATUS_MODIFY_ERROR.getMessage());
        }else{
            if(Boolean.TRUE.equals(paperManageService.modifyPaperStatusEnable(request.getBody().getId()))){
                return CommonResponseUtil.success(true);
            }
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_PAPER_STATUS_MODIFY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_PAPER_STATUS_MODIFY_ERROR.getMessage());
        }
    }

    /**
     *   根据试卷id获取试卷内容
     * @param request 带有试卷id的请求参数
     * @return 试卷内容
     */
    @ApiOperation("获取试卷内容")
    @PostMapping(value="/paperInfo")
    public CommonResponse<PaperDetailVO> doGetPaperInfo(@Valid @ApiParam @RequestBody CommonRequest<Long> request){
        //Long id=request.getBody();@Valid @ApiParam @RequestBody
        PaperContentDTO paperContentDTO =paperManageService.getPaperInfoService(request.getBody());
        PaperDetailVO paperContentVO =ConvertUtil.createAndFill(paperContentDTO, PaperDetailVO.class);
        //填充转换试卷类型、难度、组卷人等参数
        parameterConvertService.fill(paperContentDTO, paperContentVO);
        //题目数据集合的整体参数转换
        List<PaperSubjectItemVO> subjects=
                parameterConvertService.convert(paperContentDTO.getSubjects(),PaperSubjectItemVO.class);
        PropertyTransfer.setAnswer(paperContentDTO.getSubjects(),subjects);
        paperContentVO.setSubjects(subjects);
        return CommonResponseUtil.success(paperContentVO);
    }

    /**
     *   根据条件获取试卷列表
     * @param request 带有试卷查询条件的请求参数
     * @return 试卷列表
     */
    @ApiOperation("获取试卷列表")
    @PostMapping(value="/getPapers")
    public CommonResponse<PapersVO> doGetPapers(@Valid @ApiParam @RequestBody CommonRequest<PaperSearchVO> request){
        //分页设置
        doBeforePagination(request.getBody().getPageIndex(),request.getBody().getPageSize());
        PaperSearchDTO paperSearchDTO= ConvertUtil.createAndFill(request.getBody(),PaperSearchDTO.class);
        List<PaperListDTO> paperListDtos =paperManageService.searchPaperByConditionService(paperSearchDTO);
        //参数转换
        List<PaperListVO> paperListVos=parameterConvertService.convert(paperListDtos,PaperListVO.class);
        PapersVO paperData=new PapersVO();
        //获取并设置本次查询符合条件的试卷总份数
        paperData.setTotalPaper(paperManageService.getPaperCount(paperSearchDTO));
        paperData.setPaperListVos(paperListVos);
        return CommonResponseUtil.success(paperData);
    }









}
