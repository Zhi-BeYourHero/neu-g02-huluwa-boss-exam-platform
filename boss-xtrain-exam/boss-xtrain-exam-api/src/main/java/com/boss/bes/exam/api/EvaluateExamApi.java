package com.boss.bes.exam.api;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.exam.model.dto.EvaluateDTO;
import com.boss.bes.exam.model.dto.EvaluateSubmitDTO;
import com.boss.bes.exam.model.dto.SubjectTypeEvaluateDTO;
import com.boss.bes.exam.model.query.ExamRecordQuery;
import com.boss.bes.exam.model.query.PaperQuery;
import com.boss.bes.exam.model.vo.ExamRecordQueryResultVO;
import com.boss.bes.exam.model.vo.PaperSubjectVO;
import com.boss.bes.exam.model.vo.PaperToEvaluateVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-19 14:57
 * @since 1.0
 */
@RequestMapping("education/bes/v1/exam")
public interface EvaluateExamApi {
    /**
     * 通过考试查询条件获得答卷记录
     * @param request 查询考试记录参数
     * */
    @PostMapping("/queryExamRecord")
    public CommonResponse<CommonPage<ExamRecordQueryResultVO>> getAllExamRecord(
            @RequestBody @Validated CommonRequest<ExamRecordQuery> request);

    /**
     * 获取评分试卷
     * @param request 查询试卷参数
     * */
    @PostMapping("/getEvaluatePaper")
    public CommonResponse<PaperToEvaluateVO> getEvaluatePaper(
            @RequestBody @Validated CommonRequest<PaperQuery> request);

    /**
     * 定时缓存评卷信息
     * @param request 评价信息列表
     * */
    @PostMapping("/updateEvaluate")
    public CommonResponse<Boolean> updateEvaluate(
            @RequestBody @Validated CommonRequest<List<EvaluateDTO>> request);

    /**
     * 按照试题方式进行评卷获取
     * @param request 提交的题型评卷查询信息
     * */
    @PostMapping("/evaluatePaperByType")
    public CommonResponse<List<PaperSubjectVO>> evaluateByType(
            @RequestBody @Validated CommonRequest<SubjectTypeEvaluateDTO> request);

    /**
     * 按照试题方式评卷提交
     * @param request 评价信息列表
     * */
    @PostMapping("/submitEvaluateByType")
    public CommonResponse<Boolean> submitEvaluateByType(
            @RequestBody @Validated CommonRequest<List<EvaluateDTO>> request);

    /**
     * 提交评卷结果
     * @param request 评卷结果数据
     * */
    @PostMapping("/submitEvaluation")
    public CommonResponse<Boolean> submitEvaluation(
            @RequestBody @Validated CommonRequest<EvaluateSubmitDTO> request);

    /**
     * 获取所有的题目类型
     * */
    @GetMapping("/getAllSubjectType")
    public CommonResponse<List<String>> getAllSubjectType();
}
