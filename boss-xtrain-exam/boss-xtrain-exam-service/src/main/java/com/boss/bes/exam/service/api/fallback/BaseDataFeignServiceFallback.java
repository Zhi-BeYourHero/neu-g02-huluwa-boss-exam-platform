package com.boss.bes.exam.service.api.fallback;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import com.boss.bes.exam.service.api.BaseDataFeignService;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-13 15:51
 * @since
 */
@Component
public class BaseDataFeignServiceFallback implements BaseDataFeignService {
    /**
     * 查询所有主观题题目类型的降级方法
     * */
    @Override
    public CommonResponse<List<String>> doGetAllSubjectType() {
        throw new ExamException(ExamExceptionCode.CALL_BASE_DATA_SERVICE_FAILED.getCode(),
                ExamExceptionCode.CALL_BASE_DATA_SERVICE_FAILED.getCode());
    }

    @Override
    public CommonResponse<List<String>> doGetCategoryName(CommonRequest<Long> request) {
        throw new ExamException(ExamExceptionCode.CALL_BASE_DATA_SERVICE_FAILED.getCode(),
                ExamExceptionCode.CALL_BASE_DATA_SERVICE_FAILED.getCode());
    }
}
