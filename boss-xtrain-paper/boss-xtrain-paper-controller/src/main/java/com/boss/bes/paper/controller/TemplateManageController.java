package com.boss.bes.paper.controller;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.boss.bes.paper.pojo.dto.managepaper.answer.AnswerDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperContentDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperDeleteDTO;
import com.boss.bes.paper.pojo.dto.managepaper.crud.PaperModifyDTO;
import com.boss.bes.paper.pojo.dto.managepaper.subject.SubjectModifyDTO;
import com.boss.bes.paper.pojo.vo.managepaper.crud.PaperSubjectItemVO;
import com.boss.bes.paper.pojo.vo.managetemplate.crud.TemplateDeleteVO;
import com.boss.bes.paper.pojo.vo.managetemplate.crud.TemplateModifyVO;
import com.boss.bes.paper.pojo.vo.managetemplate.crud.TemplateSubjectModifyVO;
import com.boss.bes.paper.pojo.vo.managetemplate.template.TemplateDetailVO;
import com.boss.bes.paper.pojo.vo.managetemplate.template.TemplateListVO;
import com.boss.bes.paper.pojo.vo.managetemplate.template.TemplateVO;
import com.boss.bes.paper.service.util.ConvertUtil;
import com.boss.bes.paper.utils.exception.PaperExceptionCode;

import com.boss.bes.paper.pojo.dto.managetemplate.TemplateListDTO;
import com.boss.bes.paper.pojo.dto.managetemplate.TemplateSearchDTO;
import com.boss.bes.paper.pojo.vo.managetemplate.crud.TemplateSearchVO;
import com.boss.bes.paper.service.PaperManageService;
import com.boss.bes.paper.service.ParameterConvertService;
import com.boss.bes.paper.service.TemplateManageService;
import com.boss.bes.paper.service.util.PropertyTransfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.controller
 * @Description:
 * @Date: 2020/7/9 11:33
 * @since: 1.0
 */
@Api("模板管理接口")
@RestController
@CrossOrigin
@RequestMapping("education/bes/v1/paper")
public class TemplateManageController extends AbstractBaseController{

    @Autowired
    private TemplateManageService templateManageService;

    @Autowired
    private PaperManageService paperManageService;

    @Autowired
    private ParameterConvertService parameterConvertService;

    /**
     *   查询获取模板列表
     * @param request 带模板查询条件（名字、组卷时间等）的请求参数
     * @return 模板列表
     */
    @ApiOperation("查询获取模板列表")
    @PostMapping(value = "/getPaperTemplateList")
    public CommonResponse<TemplateVO> searchTemplateService(@Valid @RequestBody  CommonRequest<TemplateSearchVO> request){
        //分页查询设置request.getBody().getPageIndex(),request.getBody().getPageSize()
        doBeforePagination(request.getBody().getPageIndex(),request.getBody().getPageSize());
        TemplateSearchDTO templateSearchDTO= ConvertUtil.createAndFill(request.getBody(),TemplateSearchDTO.class);
        List<TemplateListDTO> templateListDTO = templateManageService.searchTemplateService(templateSearchDTO);
        //参数转换
        List<TemplateListVO> templateListVO=parameterConvertService.convert(templateListDTO,TemplateListVO.class);
        TemplateVO templateData=new TemplateVO();
        templateData.setTotalPaper(templateManageService.getTemplateCount(templateSearchDTO));
        templateData.setTemplates(templateListVO);
        return CommonResponseUtil.success(templateData);
    }

    /**
     *   根据模板id获取模板内容
     * @param request 带有模板id的请求参数
     * @return 模板内容
     */
    @ApiOperation("获取模板内容")
    @PostMapping(value="/getTemplateInfo")
    public CommonResponse<TemplateDetailVO> doBaseDataGetPaperInfo(@Valid @RequestBody CommonRequest<Long> request){
        PaperContentDTO paperContent =paperManageService.getPaperInfoService(request.getBody());
        //参数转换
        TemplateDetailVO templateContentVO =ConvertUtil.createAndFill(paperContent, TemplateDetailVO.class);
        parameterConvertService.fill(paperContent, templateContentVO);
        List<PaperSubjectItemVO> subjects=
                parameterConvertService.convert(paperContent.getSubjects(),PaperSubjectItemVO.class);
        PropertyTransfer.setAnswer(paperContent.getSubjects(),subjects);
        templateContentVO.setSubjects(subjects);
        return CommonResponseUtil.success(templateContentVO);
    }

    /**
     *   根据模板id和版本删除模板
     * @param request 带有模板id以及version的请求参数
     * @return 删除结果
     */
    @PreAuthorize("@ss.hasPermi('paperTemplate')")
    @ApiOperation("删除一份模板")
    @DeleteMapping(value="/deleteTemplate")
    public CommonResponse<Boolean> doBaseDataDeleteTemplateService(@Valid @RequestBody  CommonRequest<TemplateDeleteVO> request){
        Long version=request.getBody().getVersion().get(0);
        Long id=request.getBody().getId().get(0);
        if ((paperManageService.deletePaperService(id,version) <= 0)) {
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_DELETE_PAPER_ERROR.getCode(),
                    PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_DELETE_PAPER_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *   根据模板id和版本批量删除模板
     * @param request 带有id和version集合的请求参数
     * @return 删除结果
     */
    @PreAuthorize("@ss.hasPermi('paperTemplate')")
    @ApiOperation("删除多份模板")
    @DeleteMapping(value="/deleteTemplates")
    public CommonResponse<Boolean> doBaseDataDeletePapers(@Valid @RequestBody CommonRequest<TemplateDeleteVO> request){
        if ((paperManageService.deleteMorePaperService(ConvertUtil.createAndFill(request.getBody(), PaperDeleteDTO.class)) <= 0)) {
            return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_DELETE_PAPERS_ERROR.getCode(),
                    PaperExceptionCode.PAPER_CENTER_PAPER_MANAGE_DELETE_PAPERS_ERROR.getMessage());
        }
        return CommonResponseUtil.success(true);
    }

    /**
     *   修改模板内容
     * @param request 带有模板修改数据的请求参数
     * @return 修改结果
     */
    @PreAuthorize("@ss.hasPermi('paperTemplate')")
    @ApiOperation("修改模板")
    @PutMapping(value="/modifyTemplate")
    public CommonResponse<Boolean> doBaseDataModifyTemplate(@Valid @RequestBody CommonRequest<TemplateModifyVO> request){
        PaperModifyDTO paperModifyDto=ConvertUtil.createAndFill(request.getBody(),PaperModifyDTO.class);
        List<SubjectModifyDTO> subjects=ConvertUtil.convert(request.getBody().getSubject(),SubjectModifyDTO.class);
        //获取所有题目答案数据并整合到List集合中
        List<AnswerDTO> answers=new ArrayList<>();
        for(TemplateSubjectModifyVO subject:request.getBody().getSubject()){
            List<AnswerDTO> answerDtos=ConvertUtil.convert(subject.getAnswers(),AnswerDTO.class);
            answers.addAll(answerDtos);
        }
        templateManageService.modifyTemplateService(paperModifyDto,subjects,answers);

        return CommonResponseUtil.success(true);
    }


}
