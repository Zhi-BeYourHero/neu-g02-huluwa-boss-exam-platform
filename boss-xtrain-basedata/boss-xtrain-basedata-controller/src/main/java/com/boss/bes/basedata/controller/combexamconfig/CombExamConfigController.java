package com.boss.bes.basedata.controller.combexamconfig;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.basedata.BusinessErrorCode;
import com.boss.bes.basedata.SuccessResponse;
import com.boss.bes.basedata.pojo.dto.combexamconfig.CombExamConfigItemDTO;
import com.boss.bes.basedata.pojo.vo.combexamconfig.*;
import com.boss.bes.basedata.service.CombExamConfigItemService;
import com.boss.bes.basedata.service.CombExamConfigService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("组卷配置Controller")
@RestController
@CrossOrigin
public class CombExamConfigController extends AbstractController {
    @Autowired
    private CombExamConfigService combExamConfigServiceImpl;
    @Autowired
    private CombExamConfigItemService combExamConfigItemServiceImpl;

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("获取配置列表")
    @PostMapping("/education/bes/v1/basedata/combExamConfigs")
    public CommonResponse<CommonPage<CombExamConfigDataItemVO>> doQuerycombExamConfig (@Valid @RequestBody CommonRequest<CombExamConfigQueryConditionVO> request){
        CombExamConfigQueryConditionVO combExamConfigQueryConditionVO = request.getBody();
        PageHelper.startPage(combExamConfigQueryConditionVO.getStart(),combExamConfigQueryConditionVO.getSize());
        CommonPage<CombExamConfigDataItemVO> page = combExamConfigServiceImpl.queryByCondition(combExamConfigQueryConditionVO);
        return SuccessResponse.success(page);
    }

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("增加配置")
    @PostMapping("/education/bes/v1/basedata/addCombExamConfig")
    public CommonResponse<Integer> doAddCombExamConfig(@Valid @RequestBody CommonRequest<InsertCombExamConfigVO> request){
        int result = combExamConfigServiceImpl.add(request.getBody());
        if(result>0){
            return SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_COMB_INSERT_ERROR.getCode(),BusinessErrorCode.BASE_DATA_COMB_INSERT_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("删除单个配置")
    @DeleteMapping("/education/bes/v1/basedata/deleteCombExamConfig")
    public CommonResponse<CombExamConfigDataItemVO> doDeleteCombExamConfig(@Valid @RequestBody CommonRequest<DeleteCombExamConfigVO> request){
        int result = combExamConfigServiceImpl.delete(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_COMB_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_COMB_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("删除多个配置")
    @DeleteMapping("/education/bes/v1/basedata/deleteCombExamConfigs")
    public CommonResponse<CombExamConfigDataItemVO> doDeleteCombExamConfigs(@Valid @RequestBody CommonRequest<DeleteCombExamConfigsVO> request){
        int result = combExamConfigServiceImpl.batchRemove(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_COMB_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_COMB_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("修改配置")
    @PutMapping("/education/bes/v1/basedata/updateCombExamConfig")
    public CommonResponse<CombExamConfigDataItemVO> doUpdateCombExamConfig (@Valid @RequestBody CommonRequest<UpdateCombExamConfigVO>  request){
        UpdateCombExamConfigVO updateCombExamConfigVO = request.getBody();
        CombExamConfigDataItemVO combExamConfigDataItemVO = BeanUtil.copyProperties(combExamConfigServiceImpl.update(updateCombExamConfigVO),CombExamConfigDataItemVO.class);
        return SuccessResponse.success(combExamConfigDataItemVO);
    }

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("通过ID获取配置")
    @PostMapping("/education/bes/v1/basedata/getCombExamConfigById")
    public CommonResponse<CombExamConfigDataItemVO> getCombExamConfigById(@Valid  @RequestBody CommonRequest<DeleteCombExamConfigVO> request){
        CombExamConfigDataItemVO page = combExamConfigServiceImpl.queryById(request.getBody());
        return SuccessResponse.success(page);
    }

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("禁用配置")
    @PutMapping("/education/bes/v1/basedata/freezeCombExamConfig")
    public CommonResponse<CombExamConfigDataItemVO> doFreezeCombExamConfig (@Valid @RequestBody CommonRequest<UpdateCombExamConfigVO>  request){
        UpdateCombExamConfigVO updateCombExamConfigVO = request.getBody();
        updateCombExamConfigVO.setStatus(0);
        CombExamConfigDataItemVO combExamConfigDataItemVO = BeanUtil.copyProperties(combExamConfigServiceImpl.freeze(updateCombExamConfigVO),CombExamConfigDataItemVO.class);
        return SuccessResponse.success(combExamConfigDataItemVO);
    }

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("启用配置")
    @PutMapping("/education/bes/v1/basedata/unfreezeCombExamConfig")
    public CommonResponse<CombExamConfigDataItemVO> doUnfreezeCombExamConfig (@Valid @RequestBody CommonRequest<UpdateCombExamConfigVO>  request){
        UpdateCombExamConfigVO updateCombExamConfigVO = request.getBody();
        updateCombExamConfigVO.setStatus(1);
        CombExamConfigDataItemVO combExamConfigDataItemVO = BeanUtil.copyProperties(combExamConfigServiceImpl.freeze(updateCombExamConfigVO),CombExamConfigDataItemVO.class);
        return SuccessResponse.success(combExamConfigDataItemVO);
    }

    @PreAuthorize("@ss.hasPermi('combExamConfig')")
    @ApiOperation("保存组卷配置明细")
    @PostMapping("/education/bes/v1/basedata/saveCombExamConfig")
    public CommonResponse<Boolean> doAddConfigItem(@RequestBody @Validated CommonRequest<List<CombExamConfigItemDTO>> commonRequest){
        List<CombExamConfigItemDTO> itemList = commonRequest.getBody();
        combExamConfigItemServiceImpl.addConfigItem(itemList);
        return SuccessResponse.success(combExamConfigItemServiceImpl.addConfigItem(itemList));
    }
}