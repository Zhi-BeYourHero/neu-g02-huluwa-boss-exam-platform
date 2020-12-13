package com.boss.bes.basedata.controller.subjecttype;
import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.basedata.BusinessErrorCode;
import com.boss.bes.basedata.SuccessResponse;
import com.boss.bes.basedata.pojo.entity.SubjectType;
import com.boss.bes.basedata.service.SubjectTypeService;
import com.boss.bes.basedata.pojo.vo.subjecttype.*;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApiLog
@Api("题目类型Controller")
@RestController
@CrossOrigin
public class SubjectTypeController extends AbstractController {
    @Autowired
    private SubjectTypeService subjectTypeServiceImpl;

    @ApiOperation("获得所有题目类型")
    @PostMapping("/education/bes/v1/basedata/getAllSubjectType")
    public CommonResponse<List<String>> doGetAllSubjectType (){
        List<SubjectType> subjectTypes = subjectTypeServiceImpl.getAllSubjectTypes();
        List<String> ret = new ArrayList<>();
        for (SubjectType subjectType : subjectTypes) {
            ret.add(subjectType.getSubjectTypeName());
        }
        log.info("{}",ret.getClass());
        return CommonResponseUtil.success(ret);
    }

    @ApiOperation("获得所有题目类型名字")
    @PostMapping("/education/bes/v1/basedata/getAllSubjectTypeName")
    public CommonResponse<List<String>> doGetAllSubjectTypeName (){
        List<SubjectType> subjectTypes = subjectTypeServiceImpl.getAllSubjectTypeNames();
        List<String> ret = new ArrayList<>();
        for (SubjectType subjectType : subjectTypes) {
            ret.add(String.valueOf(subjectType.getSubjectTypeName()));
        }
        return SuccessResponse.success(ret);
    }

    @PreAuthorize("@ss.hasPermi('subjectType')")
    @ApiOperation("增加题目类型")
    @PostMapping("/education/bes/v1/basedata/addSubjectType")
    public CommonResponse<Integer> doAddSubjectType(@RequestBody CommonRequest<InsertSubjectTypeVO> request){
        int result = subjectTypeServiceImpl.add(request.getBody());
        if(result>0){
            return SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_SUBJECTTYPE_INSERT_ERROR.getCode(),BusinessErrorCode.BASE_DATA_SUBJECTTYPE_INSERT_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('subjectType')")
    @ApiOperation("导入题目类型")
    @PostMapping("/education/bes/v1/basedata/apiImportSubjectTypes")
    public CommonResponse<Integer> doAddSubjectTypes(@Valid @RequestBody CommonRequest<List<InsertSubjectTypeVO>> request){
        int result = subjectTypeServiceImpl.batchAdd(request.getBody());
        if(result>0){
            return SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_SUBJECTTYPE_INSERT_ERROR.getCode(),BusinessErrorCode.BASE_DATA_SUBJECTTYPE_INSERT_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('subjectType')")
    @ApiOperation("修改题目类型")
    @PutMapping("/education/bes/v1/basedata/updateSubjectType")
    public CommonResponse<SubjectTypeDataItemVO> doUpdateSubjectType (@Valid @RequestBody CommonRequest<UpdateSubjectTypeVO>  request){
        UpdateSubjectTypeVO updateSubjectTypeVO = request.getBody();
        SubjectTypeDataItemVO subjectTypeDataItemVO = BeanUtil.copyProperties(subjectTypeServiceImpl.update(updateSubjectTypeVO), SubjectTypeDataItemVO.class);
        return SuccessResponse.success(subjectTypeDataItemVO);
    }

    @PreAuthorize("@ss.hasPermi('subjectType')")
    @ApiOperation("删除单个题目类型")
    @DeleteMapping("/education/bes/v1/basedata/deleteSubjectType")
    public CommonResponse<SubjectTypeDataItemVO> doDeleteSubjectType(@Valid @RequestBody CommonRequest<DeleteSubjectTypeVO> request){
        int result = subjectTypeServiceImpl.delete(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_SUBJECTTYPE_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_SUBJECTTYPE_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('subjectType')")
    @ApiOperation("删除多个题目类别")
    @DeleteMapping("/education/bes/v1/basedata/deleteSubjectTypes")
    public CommonResponse<SubjectTypeDataItemVO> doDeleteSubjectTypes(@Valid @RequestBody CommonRequest<DeleteSubjectTypesVO> request){
        int result = subjectTypeServiceImpl.batchRemove(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_SUBJECTTYPE_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_SUBJECTTYPE_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('subjectType')")
    @ApiOperation("按题目类型获取列表")
    @PostMapping("/education/bes/v1/basedata/subjectTypes")
    public CommonResponse<CommonPage<SubjectTypeDataItemVO>> doQuerySubjectType (@Valid @RequestBody CommonRequest<SubjectTypeQueryConditionVO>  request){
        SubjectTypeQueryConditionVO subjectTypeQueryConditionVO = request.getBody();
        PageHelper.startPage(subjectTypeQueryConditionVO.getStart(),subjectTypeQueryConditionVO.getSize());
        CommonPage<SubjectTypeDataItemVO> page = subjectTypeServiceImpl.queryByCondition(subjectTypeQueryConditionVO);
        return SuccessResponse.success(page);
    }

    @PreAuthorize("@ss.hasPermi('subjectType')")
    @ApiOperation("禁用题目类型")
    @PutMapping("/education/bes/v1/basedata/freezeSubjectType")
    public CommonResponse<SubjectTypeDataItemVO> doFreezeSubjectType (@Valid @RequestBody CommonRequest<UpdateSubjectTypeVO>  request){
        UpdateSubjectTypeVO updateSubjectTypeVO = request.getBody();
        updateSubjectTypeVO.setStatus(0);
        SubjectTypeDataItemVO subjectTypeDataItemVO = BeanUtil.copyProperties(subjectTypeServiceImpl.update(updateSubjectTypeVO), SubjectTypeDataItemVO.class);
        return SuccessResponse.success(subjectTypeDataItemVO);
    }

    @PreAuthorize("@ss.hasPermi('subjectType')")
    @ApiOperation("启用题目类型")
    @PutMapping("/education/bes/v1/basedata/unfreezeSubjectType")
    public CommonResponse<SubjectTypeDataItemVO> doUnfreezeSubjectType (@Valid @RequestBody CommonRequest<UpdateSubjectTypeVO>  request){
        UpdateSubjectTypeVO updateSubjectTypeVO = request.getBody();
        updateSubjectTypeVO.setStatus(1);
        SubjectTypeDataItemVO subjectTypeDataItemVO = BeanUtil.copyProperties(subjectTypeServiceImpl.update(updateSubjectTypeVO), SubjectTypeDataItemVO.class);
        return SuccessResponse.success(subjectTypeDataItemVO);
    }
}
