package com.boss.bes.paper.service;



/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/11 21:52
 * @since: 1.0
 */
public interface UnitNameService {

    /**
     *  获取用户名
     * @param id 用户id
     * @return 用户名字
     */
    String getNameOfUser(Long id);

    /**
     *  获取公司名
     * @param id 公司id
     * @return 公司名字
     */
    String getNameOfCompany(Long id);

    /**
     *  获取组织机构名
     * @param id 组织机构id
     * @return 组织机构名字
     */
    String getNameOfOrg(Long id);
}
