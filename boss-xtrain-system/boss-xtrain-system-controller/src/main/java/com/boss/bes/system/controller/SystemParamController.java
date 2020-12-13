package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.log.api.annotation.ApiLog;
import com.boss.bes.system.service.SystemParamService;
import com.boss.bes.system.entity.SystemParam;
import com.boss.bes.system.dto.SystemParamDto;
import com.boss.bes.system.query.SystemParamQuery;
import com.boss.bes.system.vo.ResourceVo;
import com.boss.bes.system.vo.SystemParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统参数的控制器类
 * @create 2020-07-09 15:07
 * @since 1.0
 */
@ApiLog
@Api("系统参数服务接口")
@RestController
@CrossOrigin
@RequestMapping("/system/param")
public class SystemParamController extends AbstractController {

    @Resource
    private SystemParamService systemParamService;

    @PreAuthorize("@ss.hasPermi('param')")
    @ApiOperation("在数据库中保存新增的系统参数")
    @PostMapping("add")
    public CommonResponse<Integer> create(@RequestBody @Valid  SystemParamDto systemParamDto){

        Integer ret = this.systemParamService.save(systemParamDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('param')")
    @ApiOperation("在数据库中更新系统参数")
    @PostMapping("update")
    public  CommonResponse<Integer> update(@RequestBody @Valid  SystemParamDto systemParamDto){

        Integer ret = this.systemParamService.update(systemParamDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('param')")
    @ApiOperation("在数据库中删除系统参数")
    @PostMapping("delete")
    public CommonResponse<Integer> delete(@RequestBody @Valid  SystemParamDto systemParamDto){

        Integer ret = this.systemParamService.remove(systemParamDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('param')")
    @ApiOperation("在数据库中查询系统参数")
    @PostMapping("query")
    public  CommonResponse<List<SystemParamVo>> query(@RequestBody @Valid SystemParamQuery systemParamQuery){
        List<SystemParam> returnDto = systemParamService.query(systemParamQuery);
        List<SystemParamVo> systemParamVo = doObjectTransf(returnDto);
        return  buildCommonResponseSuccess(systemParamVo);
    }

    @PreAuthorize("@ss.hasPermi('param')")
    @ApiOperation("在数据库中查询系统参数")
    @PostMapping("queryId")
    public  CommonResponse<SystemParamVo> query(@RequestBody @Valid Long id){

        SystemParamQuery systemParamQuery = new SystemParamQuery();
        systemParamQuery.setId(id);
        List<SystemParam> returnDto = systemParamService.query(systemParamQuery);
        List<SystemParamVo> systemParamVo = doObjectTransf(returnDto);
        SystemParamVo systemParamVo1 = systemParamVo.get(0);
        return  buildCommonResponseSuccess(systemParamVo1);
    }

    @PreAuthorize("@ss.hasPermi('param')")
    @ApiOperation("在数据库中获取所有系统参数信息")
    @PostMapping("/listAll")
    public CommonResponse<CommonPage<SystemParamVo>> listAll(){
        List<SystemParam> systemParams = systemParamService.listAll();
        CommonPage<SystemParamVo> commonPage = new CommonPage<>();
        List<SystemParamVo> systemParamVos = doObjectTransf(systemParams);
        commonPage.setRows(systemParamVos);
        commonPage.setTotal(systemParams.size());
        return buildCommonResponseSuccess(commonPage);
    }

    @PreAuthorize("@ss.hasPermi('param')")
    @ApiOperation("批量删除")
    @PostMapping("/batchDelete")
    public CommonResponse<Integer> batchDelete(@RequestBody @Valid CommonRequest<List<Long>> request){
        List<Long> ids = request.getBody();
        Long[] idsArr = ids.toArray(new Long[]{});
        Integer ret = this.systemParamService.batchRemove(idsArr);
        return buildCommonResponseSuccess(ret);
    }



    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<SystemParamVo> doObjectTransf(List<SystemParam> returnDto) {
        List<SystemParamVo> returnVo = new ArrayList<>();
        for(SystemParam systemParam: returnDto){
            returnVo.add(new SystemParamVo(systemParam));
        }
        return returnVo;
    }
}