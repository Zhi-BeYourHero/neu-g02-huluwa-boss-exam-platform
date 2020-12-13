package com.boss.bes.basedata.controller.subject;
import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.basedata.BusinessErrorCode;
import com.boss.bes.basedata.SuccessResponse;
import com.boss.bes.basedata.service.SubjectService;
import com.boss.bes.basedata.pojo.vo.subject.*;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api("题目Controller")
@RestController
@CrossOrigin
public class SubjectController extends AbstractController {
    @Autowired
    private SubjectService subjectServiceImpl;

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("按题目获取列表")
    @PostMapping("/education/bes/v1/basedata/subjects")
    public CommonResponse<CommonPage<SubjectDataItemVO>> doQuerySubject (@Valid  @RequestBody CommonRequest<SubjectQueryConditionVO> request){
        SubjectQueryConditionVO subjectQueryConditionVO = request.getBody();
        PageHelper.startPage(subjectQueryConditionVO.getStart(),subjectQueryConditionVO.getSize());
        CommonPage<SubjectDataItemVO> page = subjectServiceImpl.queryByCondition(subjectQueryConditionVO);
        return SuccessResponse.success(page);
    }

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("增加题目")
    @PostMapping("/education/bes/v1/basedata/addSubject")
    public CommonResponse<Integer> doAddSubject(@Valid @RequestBody CommonRequest<InsertSubjectVO> request){
        int result = subjectServiceImpl.add(request.getBody());
        if(result>0){
            return SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_SUBJECT_INSERT_ERROR.getCode(),BusinessErrorCode.BASE_DATA_SUBJECT_INSERT_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("导入题目")
    @PostMapping("/education/bes/v1/basedata/apiImportSubjects")
    public CommonResponse<Integer> doAddSubjectTypes(@Valid @RequestBody CommonRequest<List<InsertSubjectVO>> request){
        int result = subjectServiceImpl.batchAdd(request.getBody());
        if(result>0){
            return SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_SUBJECTTYPE_INSERT_ERROR.getCode(),BusinessErrorCode.BASE_DATA_SUBJECTTYPE_INSERT_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("删除单个题目")
    @DeleteMapping("/education/bes/v1/basedata/deleteSubject")
    public CommonResponse<SubjectDataItemVO> doDeleteSubject(@Valid @RequestBody CommonRequest<DeleteSubjectVO> request){
        int result = subjectServiceImpl.delete(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_SUBJECT_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_SUBJECT_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("删除多个题目")
    @DeleteMapping("/education/bes/v1/basedata/deleteSubjects")
    public CommonResponse<SubjectDataItemVO> doDeleteSubjects(@Valid @RequestBody CommonRequest<DeleteSubjectsVO> request){
        int result = subjectServiceImpl.batchRemove(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_SUBJECT_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_SUBJECT_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("修改题目")
    @PutMapping("/education/bes/v1/basedata/updateSubject")
    public CommonResponse<SubjectDataItemVO> doUpdateSubjectType (@Valid @RequestBody CommonRequest<UpdateSubjectVO>  request){
        UpdateSubjectVO updateSubjectVO = request.getBody();
        SubjectDataItemVO subjectDataItemVO = BeanUtil.copyProperties(subjectServiceImpl.update(updateSubjectVO),SubjectDataItemVO.class);
        return SuccessResponse.success(subjectDataItemVO);
    }

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("通过题目ID列表获取题目")
    @PostMapping("/education/bes/v1/basedata/getSubjectById")
    public CommonResponse<SubjectDataItemVO> getSubjectById(@Valid  @RequestBody CommonRequest<DeleteSubjectVO> request){
        SubjectDataItemVO page = subjectServiceImpl.queryById(request.getBody());
        return SuccessResponse.success(page);
    }

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("禁用题目")
    @PutMapping("/education/bes/v1/basedata/freezeSubject")
    public CommonResponse<SubjectDataItemVO> doFreezeSubject (@Valid @RequestBody CommonRequest<UpdateSubjectVO>  request){
        UpdateSubjectVO updateSubjectVO = request.getBody();
        updateSubjectVO.setStatus(0);
        SubjectDataItemVO subjectDataItemVO = BeanUtil.copyProperties(subjectServiceImpl.freeze(updateSubjectVO),SubjectDataItemVO.class);
        return SuccessResponse.success(subjectDataItemVO);
    }

    @PreAuthorize("@ss.hasPermi('subject')")
    @ApiOperation("启用题目")
    @PutMapping("/education/bes/v1/basedata/unfreezeSubject")
    public CommonResponse<SubjectDataItemVO> doUnfreezeSubject (@Valid @RequestBody CommonRequest<UpdateSubjectVO>  request){
        UpdateSubjectVO updateSubjectVO = request.getBody();
        updateSubjectVO.setStatus(1);
        SubjectDataItemVO subjectDataItemVO = BeanUtil.copyProperties(subjectServiceImpl.freeze(updateSubjectVO),SubjectDataItemVO.class);
        return SuccessResponse.success(subjectDataItemVO);
    }
}
