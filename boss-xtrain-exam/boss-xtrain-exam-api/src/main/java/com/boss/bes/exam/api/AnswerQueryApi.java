package com.boss.bes.exam.api;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.exam.model.query.AnswerPaperQuery;
import com.boss.bes.exam.model.vo.AnswerPaperVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 答卷查询接口
 * @create 2020-07-10 17:11
 * @since 1.0
 */
@RequestMapping("education/bes/v1/exam")
public interface AnswerQueryApi {

    /**
     * 答卷查询
     *
     * @param request 通用请求
     * @return 答卷查询结果
     */
    @PostMapping("/answer/query")
    CommonPage<AnswerPaperVO> answerQuery(@RequestBody @Validated CommonRequest<AnswerPaperQuery> request);

}
