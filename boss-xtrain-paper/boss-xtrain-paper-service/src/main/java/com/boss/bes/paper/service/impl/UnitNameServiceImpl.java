package com.boss.bes.paper.service.impl;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.RequestHeader;

import com.boss.bes.paper.service.SystemManageService;

import com.boss.bes.paper.service.UnitNameService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.impl
 * @Description:
 * @Date: 2020/7/11 21:52
 * @since: 1.0
 */
@Service
@SuppressWarnings("unchecked")
public class UnitNameServiceImpl implements UnitNameService {

    @Resource
    private SystemManageService getSystemManageService;

    private RequestHeader header=new RequestHeader();

    private CommonRequest<Long> request=new CommonRequest<>();

    /**
     *  获取用户名
     * @param id 用户id
     * @return 用户名字
     */
    @Override
    public String getNameOfUser(Long id) {
        request.setHeader(header);
        request.setBody(id);
        CommonResponse<String> response = getSystemManageService.getUsernameById(request);
        return response.getBody()==null?"":response.getBody();
    }

    /**
     *  获取公司名
     * @param id 公司id
     * @return 公司名字
     */
    @Override
    public String getNameOfCompany(Long id) {
        request.setHeader(header);
        request.setBody(id);
        CommonResponse<String> response = getSystemManageService.selectNameByPrimaryKey1(request);
        String companyName = response.getBody();

        return companyName;
    }

    /**
     *  获取组织机构名
     * @param id 组织机构id
     * @return 组织机构名字
     */
    @Override
    public String getNameOfOrg(Long id) {
        request.setHeader(header);
        request.setBody(id);
        CommonResponse<String> response = getSystemManageService.selectNameByPrimaryKey(request);

        String orgName = response.getBody();
        return orgName==null?"":orgName;
    }


}
