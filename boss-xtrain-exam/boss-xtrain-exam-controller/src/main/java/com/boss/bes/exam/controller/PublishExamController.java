package com.boss.bes.exam.controller;
import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.log.api.annotation.ApiLog;
import com.boss.bes.exam.api.PublishExamApi;
import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.query.ExamPublishRecordQuery;
import com.boss.bes.exam.model.vo.ExamPublishRecordResultVO;
import com.boss.bes.exam.service.ExamPublishRecordService;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.exam.service.RabbitQueueService;
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
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-06 20:59
 * @since 1.0
 */
@Api(value = "发布考试模块")
@ApiLog(msg = "发布考试模块")
@Slf4j
@CrossOrigin
@RestController
public class PublishExamController extends AbstractController implements PublishExamApi {
    @Resource
    ExamPublishRecordService examPublishRecordService;
    @Resource
    RabbitQueueService rabbitQueueService;

    /**
     * 查询试卷详细信息接口
     * @param request 查询请求参数
     * */
    @Override
    @PreAuthorize("@ss.hasAnyPermi('publishExam')")
    @ApiOperation("获取考试发布记录接口")
    public CommonResponse<CommonPage<ExamPublishRecordResultVO>> getExamRecord(
            @RequestBody @Validated CommonRequest<ExamPublishRecordQuery> request){
        //首先获取DTO
        ExamPublishRecordQuery examQuery = request.getBody();
        //设置分页插件zW
        Page<Object> page = PageHelper.startPage(examQuery.getPageIndex(), examQuery.getPageSize());
        //获取查询结果
        List<ExamPublishRecordResultDTO> publishRecord = examPublishRecordService.getPublishRecord(examQuery);
        List<ExamPublishRecordResultVO> result = new ArrayList<>();
        ExamPublishRecordResultVO examPublishRecordResultVO;
        for(ExamPublishRecordResultDTO examPublishRecordResultDTO:publishRecord){
            examPublishRecordResultVO = BeanUtil.copyProperties(
                    examPublishRecordResultDTO, ExamPublishRecordResultVO.class);
            if(examPublishRecordResultVO == null){
                throw new ExamException(ExamExceptionCode.EXAM_CONVERT_DTO_TO_VO_FAILED.getCode(),
                        ExamExceptionCode.EXAM_CONVERT_DTO_TO_VO_FAILED.getCode());
            }
            result.add(examPublishRecordResultVO);
        }
        return constructResponseWithPage(page,result);
    }

    /**
     * 添加考试记录
     * @param request 添加考试的参数
     * */
    @Override
    @ApiOperation("添加考试接口")
    @PreAuthorize("@ss.hasAnyPermi('publishExam')")
    public CommonResponse<Boolean> addExam(
            @RequestBody @Validated CommonRequest<ExamAddDTO> request){
        //首先获取DTO
        ExamAddDTO examAddDTO = request.getBody();
        //校验请求体内容符合要求
        //首先校验 考试开始时间和考试结束时间
        if(Duration.between(examAddDTO.getStartTime(),examAddDTO.getEndTime()).toMinutes()
                < examAddDTO.getLimitTime() || examAddDTO.getStartTime().isAfter(examAddDTO.getEndTime())){
            throw new ExamException(ExamExceptionCode.EXAM_PARAMETER_VALID_FAILED.getCode(),
                    ExamExceptionCode.EXAM_PARAMETER_VALID_FAILED.getMessage()
                            + "：考试开始时间应小于结束时间，间隔大于考试时间");
        }
        //判断考试结束时间是否效于阅卷截至时间
        if(examAddDTO.getEndTime().isAfter(examAddDTO.getMarkingStopTime())){
            throw new ExamException(ExamExceptionCode.EXAM_PARAMETER_VALID_FAILED.getCode(),
                    ExamExceptionCode.EXAM_PARAMETER_VALID_FAILED.getMessage()
                            + "：考试结束时间应小于阅卷结束时间");
        }
        //调用接口进行插入
        return CommonResponseUtil.success(examPublishRecordService.addExam(examAddDTO) == 1);
    }

    /**
     * 删除考试接口
     * @param request 删除考试记录信息的DTO列表
     * */
    @Override
    @ApiOperation("删除考试接口")
    @PreAuthorize("@ss.hasAnyPermi('publishExam')")
    public CommonResponse<Boolean> deleteExam(
            @RequestBody @Validated CommonRequest<List<ExamPublishRecordDeleteDTO>> request){
        //获取请求体
        List<ExamPublishRecordDeleteDTO> examPublishRecordDeleteDTOS = request.getBody();
        //若请求体为空或者列表为空直接抛出异常
        if(examPublishRecordDeleteDTOS == null || examPublishRecordDeleteDTOS.isEmpty()){
            throw new ExamException(ExamExceptionCode.EXAM_PUBLISH_DELETE_NO_ID.getCode(),
                        ExamExceptionCode.EXAM_PUBLISH_DELETE_NO_ID.getMessage());
        }

        //调用service接口进行删除
        return CommonResponseUtil.success(
                examPublishRecordService.deletePublishRecord(examPublishRecordDeleteDTOS) == 1);
    }

    /**
     * 发布考试接口
     * @param request 发布考试的请求DTO 发布考试列表
     * */
    @Override
    @ApiOperation("发布考试接口")
    @PreAuthorize("@ss.hasAnyPermi('publishExam')")
    public CommonResponse<Boolean> publishExam(
            @RequestBody @Validated CommonRequest<List<PublishExamDTO>> request){
        //获取请求体
        List<PublishExamDTO> publishExamDTOS = request.getBody();
        if(publishExamDTOS == null || publishExamDTOS.isEmpty()){
            throw new ExamException(ExamExceptionCode.EXAM_PUBLISH_EMPTY.getCode(),
                    ExamExceptionCode.EXAM_PUBLISH_EMPTY.getMessage());
        }
        //调用service
        return CommonResponseUtil.success(
                examPublishRecordService.publishExam(publishExamDTOS) == 1);
    }

    /**
     * 更新考试接口
     * */
    @Override
    @ApiOperation("更新考试接口")
    @PreAuthorize("@ss.hasAnyPermi('publishExam')")
    public CommonResponse<Boolean> updateExam(@RequestBody @Validated CommonRequest<ExamUpdateDTO> request){
        //获取请求体
        ExamUpdateDTO examUpdateDTO = request.getBody();
        //首先校验 考试开始时间和考试结束时间
        //判断考试结束时间是否效于阅卷截至时间
        if(examUpdateDTO.getEndTime().isAfter(examUpdateDTO.getMarkingStopTime())){
            throw new ExamException(ExamExceptionCode.EXAM_PARAMETER_VALID_FAILED.getCode(),
                    ExamExceptionCode.EXAM_PARAMETER_VALID_FAILED.getMessage()
                            + "：考试结束时间应小于阅卷结束时间");
        }
        if(Duration.between(examUpdateDTO.getStartTime(),examUpdateDTO.getEndTime()).toMinutes()
                < examUpdateDTO.getLimitTime() || examUpdateDTO.getStartTime().isAfter(examUpdateDTO.getEndTime())){
            throw new ExamException(ExamExceptionCode.EXAM_PARAMETER_VALID_FAILED.getCode(),
                    ExamExceptionCode.EXAM_PARAMETER_VALID_FAILED.getMessage()
                            + "：考试开始时间应小于结束时间，间隔大于考试时间");
        }
        //调用service
        return CommonResponseUtil.success(
                examPublishRecordService.updateExam(examUpdateDTO) == 1);
    }

    /**
     * 获取所有阅卷官接
     * */
    @Override
    @ApiOperation("获取所有阅卷官接口")
    @PreAuthorize("@ss.hasAnyPermi('publishExam')")
    public CommonResponse<Map<Long,String>> getAllReviewer(){
        //调用service接口获取阅卷官Map
        return CommonResponseUtil.success(
                examPublishRecordService.getAllReviewer());
    }

    /**
     * 获取所有试卷接口
     * @param request 试卷Id
     * */
    @Override
    @ApiOperation("获取所有试卷接口")
    @PreAuthorize("@ss.hasAnyPermi('publishExam')")
    public CommonResponse<Map<Long,String>> getAllPaper(@RequestBody CommonRequest<Long> request){
        Long companyId = request.getBody();
        //调用service接口获取阅卷官Map
        return CommonResponseUtil.success(
                examPublishRecordService.getAllPaper(companyId));
    }
}
