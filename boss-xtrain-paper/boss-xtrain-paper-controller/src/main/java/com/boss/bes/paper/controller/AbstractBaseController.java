package com.boss.bes.paper.controller;

import boss.xtrain.core.data.convention.common.CommonResponse;
import com.github.pagehelper.PageHelper;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.controller
 * @Description:
 * @Date: 2020/7/12 18:12
 * @since: 1.0
 */
public abstract class AbstractBaseController {

    /**
     * 分页前调用
     * @param pageIndex 分页位置
     * @param pageSize  分页大小
     */
    protected void doBeforePagination(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
    }

    /**
     * 封装应答报文
     * @param object
     * @return
     */
    protected CommonResponse<Object> buildCommonResponse(Object object) {
        CommonResponse<Object> response = new CommonResponse<>();
        response.setBody(object);
        return response;
    }
}
