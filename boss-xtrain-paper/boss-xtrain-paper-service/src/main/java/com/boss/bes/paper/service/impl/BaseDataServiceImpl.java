package com.boss.bes.paper.service.impl;

import com.boss.bes.paper.service.BaseDataService;
import com.boss.bes.paper.utils.exception.PaperExceptionCode;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.boss.bes.paper.pojo.vo.makepaper.config.CombExamConfigItemListConditionVO;
import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigQueryVO;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectTypeListConditionVO;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.impl
 * @Description:
 * @Date: 2020/7/9 12:37
 * @since: 1.0
 */
@Service
public class BaseDataServiceImpl implements BaseDataService {

    /**
     * 调用基础数据服务获取所有题目类型失败返回错误
     * @param
     * @return 题目类型列表
     */
    @Override
    public CommonResponse<List<String>> doGetAllSubjectTypeName () {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_SUBJECT_TYPE_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_SUBJECT_TYPE_ERROR.getMessage());
    }

    /**
     * 调用基础数据服务获取题目类型失败返回错误
     * @param request 带有组织机构id的请求参数
     * @return 题目类型列表
     */
    @Override
    public CommonResponse getSubjectTypes(@RequestBody CommonRequest<SubjectTypeListConditionVO> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_SUBJECT_TYPE_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_SUBJECT_TYPE_ERROR.getMessage());
    }

    /**
     * 调用基础数据服务获取题目类别失败返回错误
     * @param request 带有组织机构id的请求参数
     * @return 题目类别列表
     */
    @Override
    public CommonResponse getCategorys(CommonRequest<SubjectTypeListConditionVO> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_CATEGORY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_CATEGORY_ERROR.getMessage());
    }

    /**
     * 调用基础数据服务获取配置列表失败返回错误
     * @param request 带有配置查询条件的请求参数
     * @return 题目类别列表
     */
    @Override
    public CommonResponse getConfigList(CommonRequest<ConfigQueryVO> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_CONFIG_LIST_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_CONFIG_LIST_ERROR.getMessage());
    }

    /**
     * 调用基础数据服务获取配置详细内容失败返回错误
     * @param request 带有配置id的请求参数
     * @return 详细配置项列表
     */
    @Override
    public CommonResponse getConfigItem(CommonRequest<CombExamConfigItemListConditionVO> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_CONFIG_ITEM_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_GET_CONFIG_ITEM_ERROR.getMessage());
    }

    /**
     *  调用基础数据服务进行快速组卷失败返回错误
     * @param request 带有配置信息的请求参数
     * @return 题目列表
     */
    @Override
    public CommonResponse quickMakePaper(CommonRequest request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_QUICK_MAKE_PAPER_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_QUICK_MAKE_PAPER_ERROR.getMessage());
    }

    /**
     *  调用基础数据服务进行标准组卷失败返回错误
     * @param request 带有配置信息的请求参数
     * @return 题目列表
     */
    @Override
    public CommonResponse standardMakePaper(CommonRequest request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_STANDARD_MAKE_PAPER_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_BASEDATA_SERVICE_STANDARD_MAKE_PAPER_ERROR.getMessage());
    }

    /**
     *   获取字典数据失败返回错误
     * @param request 带字典类型字符串值的请求数据
     * @return 字典数据
     */
    @Override
    public CommonResponse getDictionaryByCategory(CommonRequest<String> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
    }
}
