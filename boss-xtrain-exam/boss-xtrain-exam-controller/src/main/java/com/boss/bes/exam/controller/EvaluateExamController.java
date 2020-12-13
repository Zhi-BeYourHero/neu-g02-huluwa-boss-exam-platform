package com.boss.bes.exam.controller;
import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.exam.api.EvaluateExamApi;
import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.query.ExamRecordQuery;
import com.boss.bes.exam.model.query.PaperQuery;
import com.boss.bes.exam.model.vo.ExamRecordQueryResultVO;
import com.boss.bes.exam.model.vo.PaperSubjectVO;
import com.boss.bes.exam.model.vo.PaperToEvaluateVO;
import com.boss.bes.exam.service.ExamAnswerRecordService;
import com.boss.bes.exam.service.ExamRecordService;
import com.boss.bes.exam.service.api.SystemFeignService;
import com.boss.bes.exam.service.api.model.dto.PaperSubjectDTO;
import com.boss.bes.exam.service.api.model.vo.SubjectSearchVO;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fuhaifei
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-06 21:00
 * @since 1.0
 */
@Api(value = "评卷模块")
@ApiLog(msg = "考试评分模块")
@Slf4j
@CrossOrigin
@RestController
public class EvaluateExamController extends AbstractController implements EvaluateExamApi {
    @Resource
    ExamRecordService examRecordService;
    @Resource
    ExamAnswerRecordService examAnswerRecordService;
    @Resource
    SystemFeignService systemFeignService;
    /**
     * 通过考试查询条件获得答卷记录
     * @param request 查询考试记录参数
     * */
    @Override
    @ApiOperation("查询考试记录")
    @PreAuthorize("@ss.hasAnyPermi('evaluateManagement')")
    public CommonResponse<CommonPage<ExamRecordQueryResultVO>> getAllExamRecord(
            @RequestBody @Validated CommonRequest<ExamRecordQuery> request){
        //首先获取请求体内容
        ExamRecordQuery examRecordQuery = request.getBody();
        //设置分页插件
        Page<Object> page = PageHelper.startPage(examRecordQuery.getPageIndex(), examRecordQuery.getPageSize());
        //调用service查询结果
        List<ExamRecordQueryResultDTO> examRecordByCondition =  examRecordService.getExamRecordByCondition(examRecordQuery);
        List<ExamRecordQueryResultVO> result = new ArrayList<>();
        ExamRecordQueryResultVO temp;
        for(ExamRecordQueryResultDTO examRecordQueryResultDTO:examRecordByCondition){
            temp = BeanUtil.copyProperties(examRecordQueryResultDTO,ExamRecordQueryResultVO.class);
            if(temp == null)
                throw new ExamException(ExamExceptionCode.EXAM_CONVERT_DTO_TO_VO_FAILED.getCode(),
                        ExamExceptionCode.EXAM_CONVERT_DTO_TO_VO_FAILED.getCode());
            result.add(temp);
        }
       return constructResponseWithPage(page,result);
    }
    /**
     * 获取评分试卷
     * @param request 查询试卷参数
     * */
    @Override
    @ApiOperation("获取评卷试卷接口")
    @PreAuthorize("@ss.hasAnyPermi('evaluateManagement')")
    public CommonResponse<PaperToEvaluateVO> getEvaluatePaper(
            @RequestBody @Validated CommonRequest<PaperQuery> request){
        PaperQuery paperQuery = request.getBody();
        PaperToEvaluateDTO paper = examRecordService.getPaper(paperQuery);
        PaperToEvaluateVO paperToEvaluateVO = BeanUtil.copyProperties(paper, PaperToEvaluateVO.class);
        return CommonResponseUtil.success(paperToEvaluateVO);
    }
    /**
     * 定时缓存评卷信息
     * @param request 评价信息列表
     * */
    @Override
    @ApiOperation("定时回传评价信息")
    @PreAuthorize("@ss.hasPermiOr('evaluateManagement','evaluateByType')")
    public CommonResponse<Boolean> updateEvaluate(
            @RequestBody @Validated CommonRequest<List<EvaluateDTO>> request){
        //首先获取请求体
        List<EvaluateDTO> evaluateDTOS = request.getBody();
        examRecordService.updateExamEvaluation(evaluateDTOS);
        return CommonResponseUtil.success();
    }
    /**
     * 按照试题方式进行评卷获取
     * @param request 提交的题型评卷查询信息
     * */
    @Override
    @ApiOperation("通过题型评卷接口")
    @PreAuthorize("@ss.hasAnyPermi('evaluateByType')")
    public CommonResponse<List<PaperSubjectVO>> evaluateByType(
            @RequestBody @Validated CommonRequest<SubjectTypeEvaluateDTO> request) {
        SubjectTypeEvaluateDTO subjectTypeEvaluateDTO = request.getBody();
        List<PaperSubjectToEvaluateDTO> paperSubjects = examRecordService.getPaperSubject(subjectTypeEvaluateDTO);
        //转换为PaperSubjectVo
        List<PaperSubjectVO> result = new ArrayList<>();
        PaperSubjectVO temp;
        for (PaperSubjectToEvaluateDTO paperSubjectToEvaluateDTO : paperSubjects) {
            temp = BeanUtil.copyProperties(paperSubjectToEvaluateDTO, PaperSubjectVO.class);
            if (temp == null) {
                throw new ExamException(ExamExceptionCode.EXAM_CONVERT_DTO_TO_VO_FAILED.getCode(),
                        ExamExceptionCode.EXAM_CONVERT_DTO_TO_VO_FAILED.getCode());
            }
            result.add(temp);
        }
        return CommonResponseUtil.success(result);
    }

    /**
     * 按照试题方式评卷提交
     * @param request 评价信息列表
     * */
    @Override
    @ApiOperation("题型方式提交评卷结果接口")
    @PreAuthorize("@ss.hasAnyPermi('evaluateByType')")
    public CommonResponse<Boolean> submitEvaluateByType(
            @RequestBody @Validated CommonRequest<List<EvaluateDTO>> request){
        //首先获取数据对象
        List<EvaluateDTO> evaluateDTOS = request.getBody();
        //调用service进行更新状态
        examRecordService.submitEvaluateByType(evaluateDTOS);
        return CommonResponseUtil.success(true);
    }

    /**
     * 提交评卷结果
     * @param request 评卷结果数据
     * */
    @Override
    @ApiOperation("试卷评卷提交评卷结果接口")
    @PreAuthorize("@ss.hasAnyPermi('evaluateManagement')")
    public CommonResponse<Boolean> submitEvaluation(
            @RequestBody @Validated CommonRequest<EvaluateSubmitDTO> request){
        //首先获取请求体内容
        EvaluateSubmitDTO evaluateSubmitDTO = request.getBody();
        //调用service进行提交
        examRecordService.submitEvaluation(evaluateSubmitDTO);
        return CommonResponseUtil.success();
    }

    /**
     * 获取所有的题目类型
     * */
    @Override
    @ApiOperation("获取所有题目类型接口")
    @PreAuthorize("@ss.hasAnyPermi('evaluateByType')")
    public CommonResponse<List<String>> getAllSubjectType(){
        //调用请求请求方法
        List<String> allSubjectType = examRecordService.getAllSubjectType();
        return CommonResponseUtil.success(allSubjectType);
    }
    /**
     * 测试查询查询考试答案接口
     * */
    @GetMapping("/testGetExamAnswer/{id}")
    public CommonResponse<Map<Long,ExamAnswerRecordDTO>> test(@PathVariable Long id){
        return CommonResponseUtil.success(examRecordService.getAllExamAnswerRecord(id));
    }
    /**
     * 测试查询通过题目类型查询考试接口
     * */
    @PostMapping("/getsGetSubject")
    public CommonResponse<Map<Long, PaperSubjectDTO>> testGetSubjectByType(
            @RequestBody @Validated CommonRequest<SubjectSearchVO> request){
        Map<Long, PaperSubjectDTO> allPaperSubjectByType =
                examRecordService.getAllPaperSubjectByType
                        (request.getBody().getPaperList(), request.getBody().getSubjectType());
        return CommonResponseUtil.success(allPaperSubjectByType);
    }
}
