package com.boss.bes.exam.controller;

import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.exam.api.AnswerQueryApi;
import com.boss.bes.exam.model.dto.AnswerPaperDTO;
import com.boss.bes.exam.model.query.AnswerPaperQuery;
import com.boss.bes.exam.model.vo.AnswerPaperVO;
import com.boss.bes.exam.service.AnswerPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 答卷查询控制器
 * @create 2020-07-10 17:24
 * @since 1.0
 */
@Api(value = "答卷查询模块")
@ApiLog(msg = "答卷查询模块日志")
@CrossOrigin
@RestController
public class AnswerQueryController implements AnswerQueryApi {

    @Resource
    private AnswerPaperService answerPaperService;

    /**
     * 答卷查询
     *
     * @param request 通用请求
     * @return 答卷查询结果
     */
    @Override
    @ApiOperation("答卷查询")
    @PreAuthorize("@ss.hasAnyPermi('answerQuery')")
    public CommonPage<AnswerPaperVO> answerQuery(CommonRequest<AnswerPaperQuery> request) {
        CommonPage<AnswerPaperDTO> dtos = answerPaperService.answerQuery(request.getBody());
        CommonPage<AnswerPaperVO> vos = new CommonPage<>();
        List<AnswerPaperVO> paperVos = new ArrayList<>();
        for (AnswerPaperDTO answerPaperDTO : dtos.getRows()) {
            paperVos.add(BeanUtil.copyProperties(answerPaperDTO, AnswerPaperVO.class));
        }
        vos.setPageIndex(dtos.getPageIndex());
        vos.setPageSize(dtos.getPageSize());
        vos.setPages(dtos.getPages());
        vos.setTotal(dtos.getTotal());
        vos.setRows(paperVos);
        return vos;
    }
}
