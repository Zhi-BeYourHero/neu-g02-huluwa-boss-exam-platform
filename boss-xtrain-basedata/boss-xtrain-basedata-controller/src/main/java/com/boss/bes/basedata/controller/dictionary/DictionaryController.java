package com.boss.bes.basedata.controller.dictionary;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.exception.BusinessException;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.basedata.BusinessErrorCode;
import com.boss.bes.basedata.SuccessResponse;
import com.boss.bes.basedata.pojo.entity.Dictionary;
import com.boss.bes.basedata.pojo.vo.dictionary.*;
import com.boss.bes.basedata.service.DictionaryService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 字典
 * @create 2020-07-12 00:21
 * @since
 */
@CrossOrigin
@Api("字典Controller")
@RestController
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryServiceImpl;

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("增加数据字典")
    @PostMapping("/education/bes/v1/basedata/addDictionary")
    public CommonResponse<Integer> doAddDictionary(@Valid @RequestBody CommonRequest<InsertDictionaryVO> request){
        int result = dictionaryServiceImpl.add(request.getBody());
        if(result>0){
            return SuccessResponse.success(result);
        }
        else{
            throw new BusinessException(BusinessErrorCode.BASE_DATA_DICTIONARY_INSERT_ERROR.getCode(),BusinessErrorCode.BASE_DATA_DICTIONARY_INSERT_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("导入数据字典")
    @PostMapping("/education/bes/v1/basedata/apiImportDictionarys")
    public CommonResponse<Integer> doAddDictionarys(@Valid @RequestBody CommonRequest<List<InsertDictionaryVO>> request){
        int result = dictionaryServiceImpl.batchAdd(request.getBody());
        if(result>0){
            return SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_DICTIONARY_INSERT_ERROR.getCode(),BusinessErrorCode.BASE_DATA_DICTIONARY_INSERT_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("修改数据字典")
    @PutMapping("/education/bes/v1/basedata/updateDictionary")
    public CommonResponse<DictionaryVO> doUpdateDictionary (@Valid @RequestBody CommonRequest<UpdateDictionaryVO>  request){
        UpdateDictionaryVO updateDictionaryVO = request.getBody();
        DictionaryVO dictionaryVO = BeanUtil.copyProperties(dictionaryServiceImpl.update(updateDictionaryVO),DictionaryVO.class);
        assert dictionaryVO != null;
        if(dictionaryVO.getId() == null){
            throw new BusinessException(BusinessErrorCode.BASE_DATA_DICTIONARY_UPDATE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_DICTIONARY_UPDATE_ERROR.getMessage());
        }else {
            return SuccessResponse.success(dictionaryVO);
        }
    }

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("删除单个数据字典")
    @DeleteMapping("/education/bes/v1/basedata/deleteDictionary")
    public CommonResponse<DictionaryVO> doDeleteDictionary(@Valid @RequestBody CommonRequest<DeleteDictionaryVO> request){
        int result = dictionaryServiceImpl.delete(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            throw new BusinessException(BusinessErrorCode.BASE_DATA_DICTIONARY_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_DICTIONARY_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("删除多个数据字典")
    @DeleteMapping("/education/bes/v1/basedata/deleteDictionarys")
    public CommonResponse<DictionaryVO> doDeleteDictionarys(@Valid @RequestBody CommonRequest<DeleteDictionarysVO> request){
        int result = dictionaryServiceImpl.batchRemove(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_DICTIONARY_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_DICTIONARY_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("查找数据字典列表")
    @PostMapping("/education/bes/v1/basedata/queryDictionarys")
    public CommonResponse<CommonPage<DictionaryVO>> doQueryDictionary (@Valid @RequestBody CommonRequest<QueryDictionaryVO>  request){
        QueryDictionaryVO queryDictionaryVO = request.getBody();
        PageHelper.startPage(queryDictionaryVO.getStart(),queryDictionaryVO.getSize());
        CommonPage<DictionaryVO> page = dictionaryServiceImpl.queryByCondition(queryDictionaryVO);
        return SuccessResponse.success(page);
    }

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("按类别查询数据字典列表")
    @PostMapping("/education/bes/v1/basedata/queryDictionaryByCategory")
    public CommonResponse<List<Map<String, String>>> doQueryDictionaryByCategory (@Valid @RequestBody CommonRequest<String>  request){
        String category = request.getBody();
        List<Dictionary> dictionaries = dictionaryServiceImpl.queryDictionaryByCategory(category);
        List<Map<String, String>> ret = new ArrayList<>();
        for (Dictionary dictionary : dictionaries) {
            Map<String, String> m = new HashMap<>();
            m.put("name", dictionary.getName());
            m.put("value", dictionary.getValue());
            m.put("category", dictionary.getCategory());
            ret.add(m);
        }
        return SuccessResponse.success(ret);
    }

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("禁用数据字典")
    @PutMapping("/education/bes/v1/basedata/freezeDictionary")
    public CommonResponse<DictionaryVO> doFreezeDictionary (@Valid @RequestBody CommonRequest<UpdateDictionaryVO>  request){
        UpdateDictionaryVO updateDictionaryVO = request.getBody();
        updateDictionaryVO.setStatus(0);
        DictionaryVO dictionaryVO = BeanUtil.copyProperties(dictionaryServiceImpl.update(updateDictionaryVO),DictionaryVO.class);
        return SuccessResponse.success(dictionaryVO);
    }

    @PreAuthorize("@ss.hasPermi('dictionary')")
    @ApiOperation("启用数据字典")
    @PutMapping("/education/bes/v1/basedata/unfreezeDictionary")
    public CommonResponse<DictionaryVO> doUnfreezeDictionary (@Valid @RequestBody CommonRequest<UpdateDictionaryVO>  request){
        UpdateDictionaryVO updateDictionaryVO = request.getBody();
        updateDictionaryVO.setStatus(1);
        DictionaryVO dictionaryVO = BeanUtil.copyProperties(dictionaryServiceImpl.update(updateDictionaryVO),DictionaryVO.class);
        return SuccessResponse.success(dictionaryVO);
    }
}
