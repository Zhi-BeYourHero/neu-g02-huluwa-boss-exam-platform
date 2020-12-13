package com.boss.bes.exam.api;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.exam.model.dto.ExamAddDTO;
import com.boss.bes.exam.model.dto.ExamPublishRecordDeleteDTO;
import com.boss.bes.exam.model.dto.ExamUpdateDTO;
import com.boss.bes.exam.model.dto.PublishExamDTO;
import com.boss.bes.exam.model.query.ExamPublishRecordQuery;
import com.boss.bes.exam.model.vo.ExamPublishRecordResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 发布考试相关业务接口
 * @create 2020-07-19 14:57
 * @since 1.0
 */

@RequestMapping("education/bes/v1/exam")
public interface PublishExamApi {

    /**
     * 查询试卷详细信息接口
     * @param request 查询请求参数
     * */
    @PostMapping("/getExamPublishRecord")
    public CommonResponse<CommonPage<ExamPublishRecordResultVO>> getExamRecord(
            @RequestBody @Validated CommonRequest<ExamPublishRecordQuery> request);

    /**
     * 添加考试记录
     * @param request 添加考试的参数
     * */
    @PostMapping("/addExam")
    public CommonResponse<Boolean> addExam(
            @RequestBody @Validated CommonRequest<ExamAddDTO> request);

    /**
     * 删除考试接口
     * @param request 删除考试记录信息的DTO列表
     * */
    @PostMapping("/deleteExamPublishRecord")
    public CommonResponse<Boolean> deleteExam(
            @RequestBody @Validated CommonRequest<List<ExamPublishRecordDeleteDTO>> request);

    /**
     * 发布考试接口
     * @param request 发布考试的请求DTO 发布考试列表
     * */
    @PostMapping("/publishExam")
    public CommonResponse<Boolean> publishExam(
            @RequestBody @Validated CommonRequest<List<PublishExamDTO>> request);

    /**
     * 更新考试接口
     * @param request 更新考试接口的相关信息
     * */
    @PostMapping("/updateExam")
    public CommonResponse<Boolean> updateExam(@RequestBody @Validated CommonRequest<ExamUpdateDTO> request);

    /**
     * 获取所有阅卷官接口
     * */
    @PostMapping("/getAllReviewer")
    public CommonResponse<Map<Long,String>> getAllReviewer();

    /**
     * 获取所有试卷接口
     * @param request 试卷Id
     * */
    @PostMapping("/getAllPaper")
    public CommonResponse<Map<Long,String>> getAllPaper(@RequestBody CommonRequest<Long> request);
}
