package com.boss.bes.exam.service.api;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.exam.service.api.fallback.PaperFeignServiceFallback;
import com.boss.bes.exam.service.api.model.dto.PaperCompanyListDTO;
import com.boss.bes.exam.service.api.model.dto.PaperSubjectDTO;
import com.boss.bes.exam.service.api.model.vo.PaperContentExamVO;
import com.boss.bes.exam.service.api.model.vo.SubjectSearchVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-12 14:31
 * @since 1.0
 */
@Service
@FeignClient(name = "paper-center",fallback = PaperFeignServiceFallback.class)
public interface PaperFeignService {
    /**
     * 获取所有试卷id及对应公司id和试卷名称
     * @return Map
     */
    @ApiOperation("获取所有试卷id及对应公司id和试卷名称")
    @PostMapping(value = "/for-exam-center/getPaperAllIds")
    public CommonResponse<Map<Long, PaperCompanyListDTO>> doGetPaperAllIds();

    /**
     *  试卷发布次数增加
     * @param request 带有试卷id的请求参数
     * @return 增加结果
     */
    @PostMapping(value="/for-exam-center/publishTimesAdd")
    public CommonResponse<Boolean> doExamPublishTimesAdd(@Valid @RequestBody CommonRequest<Long> request);

    /**
     *   获取指定试卷的指定题目类型的题目
     * @param request 带有试卷id列表和题目类型的请求参数
     * @return 题目列表
     */
    @PostMapping(value = "/for-exam-center/getPapersSubjects")
    public CommonResponse<Map<Long, PaperSubjectDTO>> doGetPapersSubjects(@Valid @RequestBody CommonRequest<SubjectSearchVO> request);

    /**
     *  获取试卷内容
     * @param request 带有试卷id的请求参数
     * @return 试卷内容
     */
    @PostMapping(value="/for-exam-center/getPaperInfo")
    public CommonResponse<PaperContentExamVO> doExamGetPaperInfo(@Valid @RequestBody CommonRequest<Long> request);
}
