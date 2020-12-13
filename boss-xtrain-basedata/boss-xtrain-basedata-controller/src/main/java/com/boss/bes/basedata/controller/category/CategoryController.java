package com.boss.bes.basedata.controller.category;
import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.basedata.BusinessErrorCode;
import com.boss.bes.basedata.SuccessResponse;
import com.boss.bes.basedata.dao.categorytree.CategoryTreeList;
import com.boss.bes.basedata.pojo.entity.Category;
import com.boss.bes.basedata.service.CategoryService;
import com.boss.bes.basedata.pojo.vo.category.*;
import boss.xtrain.core.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api("题目类别Controller")
@RestController
@CrossOrigin
public class CategoryController extends AbstractController {
    @Autowired
    private CategoryService categoryServiceImpl;

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("增加题目类别")
    @PostMapping("/education/bes/v1/basedata/addCategory")
    public CommonResponse<Integer> doAddCategory(@Valid @RequestBody CommonRequest<InsertCategoryVO> request){
        int result = categoryServiceImpl.add(request.getBody());
        if(result>0){
            return SuccessResponse.success(result);
        }
        else{
            throw new BusinessException(BusinessErrorCode.BASE_DATA_CATEGORY_INSERT_ERROR.getCode(),BusinessErrorCode.BASE_DATA_CATEGORY_INSERT_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("修改题目类别")
    @PutMapping("/education/bes/v1/basedata/updateCategory")
    public CommonResponse<CategoryVO> doUpdateCategory (@Valid @RequestBody CommonRequest<UpdateCategoryVO>  request){
        UpdateCategoryVO updateCategoryVO = request.getBody();
        CategoryVO categoryVO = BeanUtil.copyProperties(categoryServiceImpl.update(updateCategoryVO),CategoryVO.class);
        assert categoryVO != null;
        if(categoryVO.getCategoryId() == null){
            throw new BusinessException(BusinessErrorCode.BASE_DATA_CATEGORY_UPDATE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_CATEGORY_UPDATE_ERROR.getMessage());
        }else {
            return SuccessResponse.success(categoryVO);
        }
    }

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("删除单个题目类别")
    @DeleteMapping("/education/bes/v1/basedata/deleteCategory")
    public CommonResponse<CategoryVO> doDeleteCategory(@Valid @RequestBody CommonRequest<DeleteCategoryVO> request){
        int result = categoryServiceImpl.delete(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            throw new BusinessException(BusinessErrorCode.BASE_DATA_CATEGORY_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_CATEGORY_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("删除多个题目类别")
    @DeleteMapping("/education/bes/v1/basedata/deleteCategorys")
    public CommonResponse<CategoryVO> doDeleteCategorys(@Valid @RequestBody CommonRequest<DeleteCategorysVO> request){
        int result = categoryServiceImpl.batchRemove(request.getBody());
        if(result>0){
            return (CommonResponse)SuccessResponse.success(result);
        }
        else{
            return CommonResponseUtil.error(BusinessErrorCode.BASE_DATA_CATEGORY_DELETE_ERROR.getCode(),BusinessErrorCode.BASE_DATA_CATEGORY_DELETE_ERROR.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("按题目类别获取列表")
    @PostMapping("/education/bes/v1/basedata/queryCategorys")
    public CommonResponse<CommonPage<CategoryVO>> doQueryCategory (@Valid @RequestBody CommonRequest<CategoryQueryConditionVO>  request){
        CategoryQueryConditionVO categoryQueryConditionVO = request.getBody();
        PageHelper.startPage(categoryQueryConditionVO.getStart(),categoryQueryConditionVO.getSize());
        CommonPage<CategoryVO> page = categoryServiceImpl.queryByCondition(categoryQueryConditionVO);
        return SuccessResponse.success(page);
    }

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("获取题目类别树")
    @PostMapping("/education/bes/v1/basedata/getCategoryTreeByOrgId")
    public CommonResponse<CategoryTreeList> doGetCategoryTree(@ApiParam @Valid @RequestBody CommonRequest<Integer> request){
        return SuccessResponse.success(categoryServiceImpl.getCategoryTree(Long.valueOf(request.getBody())));
    }

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("禁用题目类别")
    @PutMapping("/education/bes/v1/basedata/freezeCategory")
    public CommonResponse<CategoryVO> doFreezeCategory (@Valid @RequestBody CommonRequest<UpdateCategoryVO>  request){
        UpdateCategoryVO updateCategoryVO = request.getBody();
        updateCategoryVO.setStatus(0);
        CategoryVO categoryVO = BeanUtil.copyProperties(categoryServiceImpl.update(updateCategoryVO),CategoryVO.class);
        return SuccessResponse.success(categoryVO);
    }

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("启用题目类别")
    @PutMapping("/education/bes/v1/basedata/unfreezeCategory")
    public CommonResponse<CategoryVO> doUnfreezeCategory (@Valid @RequestBody CommonRequest<UpdateCategoryVO>  request){
        UpdateCategoryVO updateCategoryVO = request.getBody();
        updateCategoryVO.setStatus(1);
        CategoryVO categoryVO = BeanUtil.copyProperties(categoryServiceImpl.update(updateCategoryVO),CategoryVO.class);
        return SuccessResponse.success(categoryVO);
    }

    @PreAuthorize("@ss.hasPermi('category')")
    @ApiOperation("根据id获得题目类别名字")
    @PostMapping("/education/bes/v1/basedata/getCategoryName")
    public CommonResponse<List<String>> doGetCategoryName (@Valid @RequestBody CommonRequest<Long> request){
        List<Category> categorys = categoryServiceImpl.getCategoryName(request.getBody());
        List<String> ret = new ArrayList<>();
        for (Category category : categorys) {
            ret.add(String.valueOf(category.getName()));
        }
        return SuccessResponse.success(ret);
    }
}
