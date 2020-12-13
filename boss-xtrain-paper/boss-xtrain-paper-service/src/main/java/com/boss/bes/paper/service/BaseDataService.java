package com.boss.bes.paper.service;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;

import com.boss.bes.paper.service.impl.BaseDataServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.boss.bes.paper.pojo.vo.makepaper.config.CombExamConfigItemListConditionVO;
import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigQueryVO;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectTypeListConditionVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/9 12:37
 * @since: 1.0
 */
@FeignClient(value = "basedata",fallback = BaseDataServiceImpl.class)
public interface BaseDataService {

    /**
     * 获取所有主观题题目类型
     * @return List
     */
    @PostMapping("/education/bes/v1/basedata/getAllSubjectTypeName")
    CommonResponse<List<String>> doGetAllSubjectTypeName ();

    /**
     * 获取题目类型
     * @param request 带有组织机构id的请求参数
     * @return 题目类型列表
     */
    @PostMapping(value="/education/bes/v1/basedata/api/getSubjectTypes",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse getSubjectTypes(@Valid @RequestBody CommonRequest<SubjectTypeListConditionVO> request);

    /**
     * 获取题目类别
     * @param request 带有组织机构id的请求参数
     * @return 题目类别列表
     */
    @PostMapping(value="/education/bes/v1/basedata/api/getCategorys",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse getCategorys(@Valid @RequestBody CommonRequest<SubjectTypeListConditionVO> request);

    /**
     * 获取配置列表
     * @param request 带有配置查询条件的请求参数
     * @return 题目类别列表
     */
    @PostMapping(value="/education/bes/v1/basedata/api/getConfigList",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse getConfigList(@Valid @RequestBody CommonRequest<ConfigQueryVO> request);

    /**
     * 获取详细配置项列表
     * @param request 带有配置id的请求参数
     * @return 详细配置项列表
     */
    @PostMapping(value="/education/bes/v1/basedata/api/getConfigItem",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse getConfigItem(@Valid @RequestBody CommonRequest<CombExamConfigItemListConditionVO> request);

    /**
     *  快速组卷
     * @param request 带有配置信息的请求参数
     * @return 题目列表
     */
    @PostMapping(value="/education/bes/v1/basedata/api/quickMakePaper",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse quickMakePaper(@Valid @RequestBody CommonRequest request);

    /**
     *  标准组卷
     * @param request 带有配置信息的请求参数
     * @return 题目列表
     */
    @PostMapping(value="/education/bes/v1/basedata/api/standardMakePaper",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse standardMakePaper(@Valid @RequestBody CommonRequest request);

    /**
     *   根据条件获取字典数据
     * @param request 带字典类型字符串值的请求数据
     * @return 字典数据
     */
    @PostMapping(value = "/education/bes/v1/basedata/queryDictionaryByCategory",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse getDictionaryByCategory(@RequestBody CommonRequest<String> request);
}
