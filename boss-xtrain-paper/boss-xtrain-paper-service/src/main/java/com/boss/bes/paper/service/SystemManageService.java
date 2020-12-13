package com.boss.bes.paper.service;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.paper.service.impl.SystemManageServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/11 21:44
 * @since: 1.0
 */
@FeignClient(value = "system-center",fallback = SystemManageServiceImpl.class)
public interface SystemManageService {

    /**
     *   根据用户id获取用户名字
     * @param request 带用户id的请求参数
     * @return 用户名字
     */
    @PostMapping(value = "/system/user/getUsernameById",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse<String> getUsernameById(@RequestBody CommonRequest<Long> request);

    /**
     *  根据公司id获取公司名字
     * @param request 带公司id的请求参数
     * @return 公司名字
     */
    @PostMapping(value = "/system/company/getCompanyNameByCompanyId",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse<String> selectNameByPrimaryKey1(@RequestBody CommonRequest<Long> request);

    /**
     *   根据组织机构id获取机构名
     * @param request 带组织机构id的请求参数
     * @return 组织机构名
     */
    @PostMapping(value = "/system/organization/getOrganizationNameById",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse<String> selectNameByPrimaryKey(@RequestBody CommonRequest<Long> request);


    /**
     *   根据组织机构id获取公司列表
     * @param request 带组织机构id的请求参数
     * @return 公司列表
     */
    @PostMapping(value = "/system/company/getCompanyByOrganizationId",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse doListCompanyByOrgId(@RequestBody CommonRequest<Long> request);

}
