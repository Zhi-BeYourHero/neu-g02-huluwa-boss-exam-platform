package com.boss.bes.paper.service.impl;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.paper.service.SystemManageService;
import com.boss.bes.paper.utils.exception.PaperExceptionCode;
import boss.xtrain.core.util.CommonResponseUtil;
import org.springframework.stereotype.Component;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.impl
 * @Description:
 * @Date: 2020/7/11 21:44
 * @since: 1.0
 */
@Component
public class SystemManageServiceImpl implements SystemManageService {



    /**
     *   获取用户名字失败返回错误
     * @param request 带用户id的请求参数
     * @return 用户名字
     */
    @Override
    public CommonResponse<String> getUsernameById(CommonRequest<Long> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
    }

    /**
     *  获取公司名字失败返回错误
     * @param request 带公司id的请求参数
     * @return 公司名字
     */
    @Override
    public CommonResponse<String> selectNameByPrimaryKey1(CommonRequest<Long> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
    }

    /**
     *   获取组织机构名字失败返回错误
     * @param request 带组织机构id的请求参数
     * @return 组织机构名
     */
    @Override
    public CommonResponse<String> selectNameByPrimaryKey(CommonRequest<Long> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
    }

    /**
     *   获取公司列表识别返回错误
     * @param request 带组织机构id的请求参数
     * @return 公司列表
     */
    @Override
    public CommonResponse doListCompanyByOrgId(CommonRequest<Long> request) {
        return CommonResponseUtil.error(PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_GET_SYSTEM_SERVICE_GET_DICTIONARY_ERROR.getMessage());
    }
}
