package com.boss.bes.paper.controller;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.boss.bes.paper.pojo.dto.makepaper.ConfigDTO;
import com.boss.bes.paper.pojo.dto.makepaper.ConfigItemDTO;
import com.boss.bes.paper.pojo.dto.makepaper.QuickMakePaperDTO;
import com.boss.bes.paper.pojo.dto.makepaper.StandardMakeDTO;
import com.boss.bes.paper.pojo.vo.makepaper.config.CombExamConfigItemListConditionVO;

import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigQueryVO;
import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigVO;
import com.boss.bes.paper.pojo.vo.makepaper.make.MakePaperVO;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectTypeListConditionVO;
import com.boss.bes.paper.service.util.ConvertUtil;
import com.boss.bes.paper.service.util.PropertyTransfer;

import com.boss.bes.paper.utils.exception.PaperExceptionCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.boss.bes.paper.service.BaseDataService;
import com.boss.bes.paper.service.MakePaperService;
import com.boss.bes.paper.service.ParameterConvertService;
import com.boss.bes.paper.service.SystemManageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.controller
 * @Description:
 * @Date: 2020/7/9 11:32
 * @since: 1.0
 */
@SuppressWarnings("unchecked")
@Api("组卷")
@RestController
@CrossOrigin
@RequestMapping("/paper-center/makepaper")
public class MakePaperController extends AbstractBaseController {

    @Autowired
    private MakePaperService createPaperService;


    @Autowired
    private BaseDataService getBaseDataService;

    @Autowired
    private SystemManageService getSystemManageService;

    @Autowired
    private ParameterConvertService parameterConvertService;

    /**
     *  快速组卷
     * @param request 带有组卷配置以及试卷信息的请求参数
     * @return 组卷结果
     */
    @PreAuthorize("@ss.hasPermi('makePaper')")
    @ApiOperation("快速组卷")
    @PostMapping(value="/quick")
    public CommonResponse<Boolean> doQuickMakePaper(@Valid @RequestBody CommonRequest<MakePaperVO> request) {
        QuickMakePaperDTO quickMakePaperDTO=ConvertUtil.createAndFill(request.getBody(),QuickMakePaperDTO.class);
        quickMakePaperDTO.setHeader(request.getHeader());
        //调用快速组卷服务实现快速组卷功能
        if(createPaperService.quickMakePaper(quickMakePaperDTO)>0){
            return CommonResponseUtil.success(true);
        }
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_QUICK_MAKE_PAPER_ERROR.getCode(),
                PaperExceptionCode.PAPER_CENTER_QUICK_MAKE_PAPER_ERROR.getMessage());
    }

    /**
     *  标准组卷
     * @param request 带有标准组卷配置以及试卷信息的请求参数
     * @return 组卷结果
     */
    @PreAuthorize("@ss.hasPermi('makePaper')")
    @ApiOperation("标准组卷")
    @PostMapping(value = "/standard")
    public CommonResponse<Boolean> doStandardMakePaper(@Valid @RequestBody CommonRequest<MakePaperVO> request) {
        StandardMakeDTO standardMakeDTO=ConvertUtil.createAndFill(request.getBody(),StandardMakeDTO.class);
        standardMakeDTO.setHeader(request.getHeader());
        //调用标准组卷服务实现标准组卷功能
        createPaperService.standardMakePaper(standardMakeDTO);
            return CommonResponseUtil.success(true);

    }

    /**
     *  从基础数据服务中获取题目类型列表
     * @param request 带有组织机构id的请求参数
     * @return 题目类型列表
     */
    @Cacheable(cacheNames = {"BaseDataServiceSubjectTypes"})
    @ApiOperation("从基础数据服务中获取题目类型")
    @PostMapping(value = "/getSubjectTypes")
    public CommonResponse doGetSubjectTypes(@Valid @RequestBody CommonRequest<SubjectTypeListConditionVO> request){
        request.getBody().setOrgId(1L);
        return getBaseDataService.getSubjectTypes(request);
    }

    /**
     *  从基础数据服务中获取题目类别列表
     * @param request 带有组织机构id的请求参数
     * @return 题目类别列表
     */
    @ApiOperation("从基础数据服务中获取题类别")
    @Cacheable(cacheNames = {"BaseDataServiceCategorys"})
    @PostMapping(value = "/getCategorys")
    public CommonResponse doGetCategorys(@Valid @RequestBody CommonRequest<SubjectTypeListConditionVO> request){
        request.getBody().setOrgId(1L);
        return getBaseDataService.getCategorys(request);
    }

    /**
     *   从基础服务中获取难度列表
     * @param request 带有字典类别数值的请求参数
     * @return 难度列表
     */
    @ApiOperation("从基础服务中获取难度")
    @Cacheable(cacheNames = {"SystemServiceDifficultyList"})
    @PostMapping(value = "/getDifficultys")
    public CommonResponse doGetDifficultys(@Valid @RequestBody CommonRequest<String> request){
        CommonRequest<String> request1=new CommonRequest<>();
        request1.setBody("难度");
        return getBaseDataService.getDictionaryByCategory(request1);
    }

    /**
     *   从基础数据中获取试卷类型列表
     * @param request 带有字典数据类别的请求参数
     * @return 试卷类型列表
     */
    @ApiOperation("获取试卷类型")
    @Cacheable(cacheNames = {"SystemServicePaperTypeList"})
    @PostMapping(value = "/getPaperTypes")
    public CommonResponse doGetPaperTypes(@Valid @RequestBody CommonRequest<String> request){
        CommonRequest<String> request1=new CommonRequest<>();
        request1.setBody("试卷类型");
        return getBaseDataService.getDictionaryByCategory(request1);
    }

    /**
     *   从基础数据服务中获取配置列表
     * @param request 带有配置列表查询条件的请求参数
     * @return 配置列表
     */
    @PreAuthorize("@ss.hasPermi('makePaper')")
    @ApiOperation("从基础数据服务中获取配置列表")
    @PostMapping(value = "/getConfigList")
    public CommonResponse<CommonPage> doGetConfigList(@Valid @RequestBody CommonRequest<ConfigQueryVO> request){
        //调用基础数据服务接口获取配置列表数据
        CommonResponse response= getBaseDataService.getConfigList(request);
        //转换json数据为指定类型的List数据
        List<ConfigDTO> configDtoList= PropertyTransfer.transferResponseToRow(response);
        //DTO参数转换为VO参数PropertyTransfer.transferResponseToLong(response)

        CommonPage<ConfigDTO> configCommonPage=new CommonPage<>();
        configCommonPage.setPageIndex(1);
        configCommonPage.setPageSize(5);
        configCommonPage.setPages(1);
        configCommonPage.setRows(configDtoList);
        configCommonPage.setTotal(5);
        return CommonResponseUtil.success(configCommonPage);
    }

    /**
     *   从基础数据服务中获取配置列表详细项
     * @param request 带有配置id的请求参数
     * @return 配置详细项列表
     */
    @PreAuthorize("@ss.hasPermi('makePaper')")
    @ApiOperation("从基础数据服务中获取配置列表详细项")
    @PostMapping(value = "/getConfigItem")
    public CommonResponse<List<ConfigItemDTO>> doGetConfigItemList(@Valid @RequestBody CommonRequest<CombExamConfigItemListConditionVO> request){
        //调用基础数据服务接口获取配置详细项数据
        CommonResponse response= getBaseDataService.getConfigItem(request);
        //json数据转换为指定类型List数据
        String listTxt = JSONArray.toJSONString(response.getBody());
        List<ConfigItemDTO> configItemDtos=JSON.parseArray(listTxt,ConfigItemDTO.class);

        //DTO参数转换为VO参数
        return CommonResponseUtil.success(configItemDtos);
    }

    /**
     *  从系统管理服务中获取公司列表
     * @param request 带有组织机构id的请求参数
     * @return 公司列表
     */
    @PreAuthorize("@ss.hasPermi('makePaper')")
    @ApiOperation("获取公司列表")
    @PostMapping(value = "/listCompanyByOrgId")
    public CommonResponse doGetUserNameByUserId(@Valid @RequestBody CommonRequest<Long> request){
        return getSystemManageService.doListCompanyByOrgId(request);
    }





}
