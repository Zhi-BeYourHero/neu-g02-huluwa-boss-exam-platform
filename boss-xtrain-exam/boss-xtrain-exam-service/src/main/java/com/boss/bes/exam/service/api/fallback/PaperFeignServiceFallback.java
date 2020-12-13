package com.boss.bes.exam.service.api.fallback;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.exam.service.api.PaperFeignService;
import com.boss.bes.exam.service.api.model.dto.PaperCompanyListDTO;
import com.boss.bes.exam.service.api.model.dto.PaperSubjectDTO;
import com.boss.bes.exam.service.api.model.vo.PaperContentExamVO;
import com.boss.bes.exam.service.api.model.vo.SubjectSearchVO;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-12 14:41
 * @since
 */
@Component
public class PaperFeignServiceFallback implements PaperFeignService {
    /**
     * 获取所有试卷id及对应公司id和试卷名称
     *
     * @return Map
     */
    @Override
    public CommonResponse<Map<Long, PaperCompanyListDTO>> doGetPaperAllIds() {
        return null;
    }

    /**
     * 试卷发布次数增加
     *
     * @param request 带有试卷id的请求参数
     * @return 增加结果
     */
    @Override
    public CommonResponse<Boolean> doExamPublishTimesAdd(@Valid CommonRequest<Long> request) {
        return null;
    }

    /**
     * 获取指定试卷的指定题目类型的题目
     *
     * @param request 带有试卷id列表和题目类型的请求参数
     * @return 题目列表
     */
    @Override
    public CommonResponse<Map<Long, PaperSubjectDTO>> doGetPapersSubjects(@Valid CommonRequest<SubjectSearchVO> request) {
        return null;
    }

    /**
     * 获取试卷内容
     *
     * @param request 带有试卷id的请求参数
     * @return 试卷内容
     */
    @Override
    public CommonResponse<PaperContentExamVO> doExamGetPaperInfo(@Valid CommonRequest<Long> request) {
        return null;
    }

}
