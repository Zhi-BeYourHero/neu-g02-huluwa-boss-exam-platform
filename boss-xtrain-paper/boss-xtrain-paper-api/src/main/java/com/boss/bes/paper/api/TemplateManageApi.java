package com.boss.bes.paper.api;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.paper.pojo.vo.managetemplate.crud.TemplateDeleteVO;
import com.boss.bes.paper.pojo.vo.managetemplate.crud.TemplateModifyVO;
import com.boss.bes.paper.pojo.vo.managetemplate.crud.TemplateSearchVO;
import com.boss.bes.paper.pojo.vo.managetemplate.template.TemplateDetailVO;
import com.boss.bes.paper.pojo.vo.managetemplate.template.TemplateVO;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.api
 * @Description:
 * @Date: 2020/7/12 12:37
 * @since: 1.0
 */
@RequestMapping("/paper-center/template-manage/")
public interface TemplateManageApi {

    /**
     *   查询获取模板列表
     * @param request 带模板查询条件（名字、组卷时间等）的请求参数
     * @return 模板列表
     */
    @PostMapping(value = "/getPaperTemplateList")
    CommonResponse<TemplateVO> searchTemplateService(@Valid @RequestBody CommonRequest<TemplateSearchVO> request);

    /**
     *   根据模板id获取模板内容
     * @param request 带有模板id的请求参数
     * @return 模板内容
     */
    @PostMapping(value="/getTemplateInfo")
    CommonResponse<TemplateDetailVO> doBaseDataGetPaperInfo(@Valid @RequestBody CommonRequest<Long> request);

    /**
     *   根据模板id和版本删除模板
     * @param request 带有模板id以及version的请求参数
     * @return 删除结果
     */
    @DeleteMapping(value="/deleteTemplate")
    CommonResponse<Boolean> doBaseDataDeleteTemplateService(@Valid @RequestBody CommonRequest<TemplateDeleteVO> request);

    /**
     *   根据模板id和版本批量删除模板
     * @param request 带有id和version集合的请求参数
     * @return 删除结果
     */
    @DeleteMapping(value="/deleteTemplates")
    CommonResponse<Boolean> doBaseDataDeletePapers(@Valid @RequestBody CommonRequest<TemplateDeleteVO> request);


    /**
     *   修改模板内容
     * @param request 带有模板修改数据的请求参数
     * @return 修改结果
     */
    @PutMapping(value="/modifyTemplate")
    CommonResponse<Boolean> doBaseDataModifyTemplate(@Valid @RequestBody CommonRequest<TemplateModifyVO> request);
}
