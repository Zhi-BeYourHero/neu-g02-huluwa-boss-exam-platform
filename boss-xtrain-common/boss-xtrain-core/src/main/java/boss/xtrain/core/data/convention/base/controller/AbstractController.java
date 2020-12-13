package boss.xtrain.core.data.convention.base.controller;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.exception.error.CommonErrorCode;
import boss.xtrain.core.util.AppUtil;
import boss.xtrain.core.util.CommonResponseUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 所有的controller存在的公用的功能
 * @create 2020-07-08
 * @since
 */
public class AbstractController {

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    public <T> CommonResponse<T> toCommonResponse(int rows)
    {
        return rows > 0 ? CommonResponseUtil.success() : CommonResponseUtil.error(CommonErrorCode.OPERATION_ERROR.getCode(), CommonErrorCode.OPERATION_ERROR.getMessage());
    }

    /**
     * 封装响应对象到CommonPage中
     * */
    public <T> CommonResponse<CommonPage<T>> constructResponseWithPage(Page<Object> pageInfo,List<T> list){
        CommonPage<T> commonPage = new CommonPage<>();
        commonPage.setPageIndex(pageInfo.getPageNum());
        commonPage.setPages(pageInfo.getPages());
        commonPage.setPageSize(pageInfo.getPageSize());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setRows(list);
        CommonResponse<CommonPage<T>> commonPageCommonResponse = CommonResponseUtil.success();
        commonPageCommonResponse.setBody(commonPage);
        return commonPageCommonResponse;
    }

    /**
     * 封装应答包
     */
    protected <T> CommonResponse<T> buildCommonResponseSuccess(T body){
        CommonResponse<T> commonResponse=new CommonResponse<>();
        commonResponse.setBody(body);
        //调用工具类设置版本等信息
        AppUtil.setResponseExtendInfo(commonResponse);
        return commonResponse;
    }
}
