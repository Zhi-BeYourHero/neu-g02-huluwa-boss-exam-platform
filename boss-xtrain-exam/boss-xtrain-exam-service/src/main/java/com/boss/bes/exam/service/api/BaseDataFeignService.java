package com.boss.bes.exam.service.api;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import com.boss.bes.exam.service.api.fallback.BaseDataFeignServiceFallback;
import com.boss.bes.exam.service.api.fallback.PaperFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-13 15:39
 * @since 1.0
 */
@Service
@FeignClient(name = "basedata",fallback = BaseDataFeignServiceFallback.class)
public interface BaseDataFeignService {

    @PostMapping("/education/bes/v1/basedata/getAllSubjectType")
    CommonResponse<List<String>> doGetAllSubjectType ();

    @PostMapping("/education/bes/v1/basedata/getCategoryName")
    CommonResponse<List<String>> doGetCategoryName(CommonRequest<Long> request);
}
