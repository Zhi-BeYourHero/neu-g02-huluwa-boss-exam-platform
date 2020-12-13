package com.boss.bes.paper.api;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonPage;

import com.boss.bes.paper.pojo.vo.makepaper.config.CombExamConfigItemListConditionVO;
import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigItemVO;
import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigQueryVO;
import com.boss.bes.paper.pojo.vo.makepaper.make.MakePaperVO;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectTypeListConditionVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.api
 * @Description:
 * @Date: 2020/7/12 12:27
 * @since: 1.0
 */
@RequestMapping("/paper-center/makepaper")
public interface CreatePaperApi {

    /**
     *  快速组卷
     * @param request 带有组卷配置以及试卷信息的请求参数
     * @return 组卷结果
     */
    @PostMapping(value="/quick")
    CommonResponse<Boolean> doQuickMakePaper(@Valid @RequestBody CommonRequest<MakePaperVO> request);


    /**
     *  标准组卷
     * @param request 带有标准组卷配置以及试卷信息的请求参数
     * @return 组卷结果
     */
    @PostMapping(value = "/standard")
    CommonResponse<Boolean> doStandardMakePaper(@Valid @RequestBody CommonRequest<MakePaperVO> request);

    /**
     *  从基础数据服务中获取题目类型列表
     * @param request 带有组织机构id的请求参数
     * @return 题目类型列表
     */
    @PostMapping(value = "/getSubjectTypes")
    CommonResponse doGetSubjectTypes(@Valid @RequestBody CommonRequest<SubjectTypeListConditionVO> request);

    /**
     *  从基础数据服务中获取题目类别列表
     * @param request 带有组织机构id的请求参数
     * @return 题目类别列表
     */
    @PostMapping(value = "/getCategorys")
    CommonResponse doGetCategorys(@Valid @RequestBody CommonRequest<SubjectTypeListConditionVO> request);

    /**
     *   从系统服务中获取难度列表
     * @param request 带有字典类别数值的请求参数
     * @return 难度列表
     */
    @PostMapping(value = "/getDifficultys")
    CommonResponse doGetDifficultys(@Valid @RequestBody CommonRequest<String> request);


    /**
     *   从系统服务中获取试卷类型列表
     * @param request 带有字典数据类别的请求参数
     * @return 试卷类型列表
     */
    @PostMapping(value = "/getPaperTypes")
    CommonResponse doGetPaperTypes(@Valid @RequestBody CommonRequest<String> request);

    /**
     *   从基础数据服务中获取配置列表
     * @param request 带有配置列表查询条件的请求参数
     * @return 配置列表
     */
    @PostMapping(value = "/getConfigList")
    CommonResponse<CommonPage> doGetConfigList(@Valid @RequestBody CommonRequest<ConfigQueryVO> request);

    /**
     *   从基础数据服务中获取配置列表详细项
     * @param request 带有配置id的请求参数
     * @return 配置详细项列表
     */
    @PostMapping(value = "/getConfigItem")
    CommonResponse<List<ConfigItemVO>> doGetConfigItemList(@Valid @RequestBody CommonRequest<CombExamConfigItemListConditionVO> request);

    /**
     *  从系统管理服务中获取公司列表
     * @param request 带有组织机构id的请求参数
     * @return 公司列表
     */
    @PostMapping(value = "/listCompanyByOrgId")
    CommonResponse doGetUserNameByUserId(@Valid @RequestBody CommonRequest<Long> request);

}
