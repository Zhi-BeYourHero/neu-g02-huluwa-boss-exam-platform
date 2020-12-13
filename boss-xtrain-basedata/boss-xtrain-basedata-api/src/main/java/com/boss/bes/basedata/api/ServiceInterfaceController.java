package com.boss.bes.basedata.api;
import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.*;
import com.boss.bes.basedata.pojo.vo.dictionary.DictionaryListConditionVO;
import com.boss.bes.basedata.pojo.vo.dictionary.DictionaryVO;
import com.boss.bes.basedata.service.*;
import com.boss.bes.basedata.pojo.vo.category.CategoryListConditionVO;
import com.boss.bes.basedata.pojo.vo.category.CategoryListVO;
import com.boss.bes.basedata.pojo.vo.combexamconfig.*;
import com.boss.bes.basedata.pojo.vo.subject.SubjectDataItemVO;
import com.boss.bes.basedata.pojo.vo.subject.SubjectIdConditionVO;
import com.boss.bes.basedata.pojo.vo.subjecttype.SubjectTypeListConditionVO;
import com.boss.bes.basedata.pojo.vo.subjecttype.SubjectTypeListVO;

import com.boss.bes.basedata.service.impl.DictionaryServiceImpl;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import boss.xtrain.core.data.convention.common.CommonPage;

@Api("服务接口Controller")
@RestController
@Slf4j
@CrossOrigin
public class ServiceInterfaceController extends AbstractController {
    @Autowired
    private DictionaryService dictionaryServiceImpl;
    @Autowired
    private CategoryService categoryServiceImpl;
    @Autowired
    private SubjectTypeService subjectTypeServiceImpl;
    @Autowired
    private CombExamConfigService combExamConfigServiceImpl;
    @Autowired
    private SubjectService subjectServiceImpl;
    @ApiOperation("获取数据字典列表")
    @PostMapping("/education/bes/v1/basedata/api/getDictionarys")
    public CommonResponse<List<DictionaryVO>> getDictionarys (@Valid  @RequestBody CommonRequest<DictionaryListConditionVO> request){
        DictionaryListConditionVO dictionaryListConditionVO = request.getBody();
        List<DictionaryVO> dictionaryVos = dictionaryServiceImpl.getDictionarys(dictionaryListConditionVO);
        return success(dictionaryVos);
    }
    @ApiOperation("获取题目类别列表")
    @PostMapping("/education/bes/v1/basedata/api/getCategorys")
    public CommonResponse<List<CategoryListVO>> getCategorys (@Valid  @RequestBody CommonRequest<CategoryListConditionVO> request){
        CategoryListConditionVO categoryListConditionVO = request.getBody();
        List<CategoryListVO> categoryListVos = categoryServiceImpl.getCategorys(categoryListConditionVO);
        return success(categoryListVos);
    }
    @ApiOperation("获取题目类型列表")
    @PostMapping(value="/education/bes/v1/basedata/api/getSubjectTypes")
    public CommonResponse<List<SubjectTypeListVO>> getSubjectTypes (@Valid @RequestBody CommonRequest<SubjectTypeListConditionVO> request){
        SubjectTypeListConditionVO subjectTypeListConditionVO = request.getBody();
        List<SubjectTypeListVO> subjectTypeListVos = subjectTypeServiceImpl.getSubjectTypes(subjectTypeListConditionVO);
        return success(subjectTypeListVos);
    }
    @ApiOperation("发送请求给基础数据获取配置列表")
    @PostMapping("/education/bes/v1/basedata/api/getConfigList")
    public CommonResponse<CommonPage<CombExamConfigListVO>> getConfigList (@Valid @RequestBody CommonRequest<CombExamConfigListConditionVO> request){
        CombExamConfigListConditionVO combExamConfigListConditionVO = request.getBody();
        PageHelper.startPage(combExamConfigListConditionVO.getPageIndex(),combExamConfigListConditionVO.getPageSize());
        CommonPage<CombExamConfigListVO> page = combExamConfigServiceImpl.getConfigList(combExamConfigListConditionVO);
        return success(page);
    }
    @ApiOperation("发送请求给基础数据获取详细配置列表")
    @PostMapping("/education/bes/v1/basedata/api/getConfigItem")
    public CommonResponse<List<CombExamConfigItemListVO>> getConfigItem (@Valid @RequestBody CommonRequest<CombExamConfigItemListConditionVO> request){
        CombExamConfigItemListConditionVO combExamConfigItemListConditionVO = request.getBody();
        List<CombExamConfigItemListVO> combExamConfigItemListVos = combExamConfigServiceImpl.getConfigItem(combExamConfigItemListConditionVO);
        return success(combExamConfigItemListVos);
    }
    @ApiOperation("快速组卷 获取题目列表（不保存修改的配置）")
    @PostMapping("/education/bes/v1/basedata/api/quickMakePaper")
    public CommonResponse<List<SubjectDataItemVO>> quickMakePaper (@Valid @RequestBody CommonRequest<CombExamConfigDataItemVO> request){
        CombExamConfigDataItemVO combExamConfigDataItemVO= request.getBody();
        List<SubjectDataItemVO> subjectDataItemVos = subjectServiceImpl.quickMakePaper(combExamConfigDataItemVO);
        return success(subjectDataItemVos);
    }
    @ApiOperation("标准组卷 获取题目列表（保存自己添加的配置）")
    @PostMapping("/education/bes/v1/basedata/api/standardMakePaper")
    public CommonResponse<List<SubjectDataItemVO>> standardMakePaper (@Valid @RequestBody CommonRequest<CombExamConfigDataItemVO> request){
        CombExamConfigDataItemVO combExamConfigDataItemVO= request.getBody();
        List<SubjectDataItemVO> subjectDataItemVos = subjectServiceImpl.standardMakePaper(combExamConfigDataItemVO);
        return success(subjectDataItemVos);
    }
    @ApiOperation("考试服务 通过题目ID列表获取题目描述，标准答案，分值，类型")
    @PostMapping("/education/bes/v1/basedata/api/getSubjectDtoByIds")
    public CommonResponse<List<SubjectDataItemVO>> getSubjectDtoByIds (@Valid @RequestBody CommonRequest<SubjectIdConditionVO> request){
        SubjectIdConditionVO subjectIdConditionVO= request.getBody();
        List<SubjectDataItemVO> subjectDataItemVos = subjectServiceImpl.getSubjectDtoByIds(subjectIdConditionVO);
        return success(subjectDataItemVos);
    }
    public static <T> CommonResponse<T> success(T body){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setHeader(new ResponseHeader());
        commonResponse.getHeader().setAnswerBackCode("1");
        commonResponse.setBody(body);
        commonResponse.getHeader().setEncryptFlag(0);
        return commonResponse;
    }
}
