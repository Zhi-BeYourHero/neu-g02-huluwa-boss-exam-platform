package com.boss.bes.paper.api;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.data.convention.common.CommonRequest;
import com.boss.bes.paper.pojo.vo.managepaper.answer.AnswerAddVO;
import com.boss.bes.paper.pojo.vo.managepaper.answer.AnswerModifyVO;
import com.boss.bes.paper.pojo.vo.managepaper.crud.*;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectInsertVO;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectModifyVO;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.api
 * @Description:
 * @Date: 2020/7/12 12:33
 * @since: 1.0
 */
@RequestMapping(value="/paper-center/paper-manage")
public interface PaperManageApi {

    /**
     *   根据条件获取试卷列表
     * @param request 带有试卷查询条件的请求参数
     * @return 试卷列表
     */
    @PostMapping(value="/getPapers")
    CommonResponse<PapersVO> doGetPapers(@Valid  @RequestBody CommonRequest<PaperSearchVO> request);

    /**
     *   根据试卷id获取试卷内容
     * @param request 带有试卷id的请求参数
     * @return 试卷内容
     */
    @PostMapping(value="/paperInfo")
    CommonResponse<PaperDetailVO> doGetPaperInfo(@Valid  @RequestBody CommonRequest<Long> request);

    /**
     *   获取试卷详细描述信息
     * @param request 带有试卷id的请求参数
     * @return 试卷详细信息
     */
    @PostMapping(value="paperDescription")
    CommonResponse<PaperDescriptionVO> doGetPaperInfor(@Valid  @RequestBody CommonRequest<Long> request);

    /**
     *  试卷删除
     * @param request 带有试卷id和version的请求参数
     * @return 删除结果
     */
    @DeleteMapping(value="/deletePaper")
    CommonResponse<Boolean> doDeletePaper(@Valid  @RequestBody CommonRequest<PaperDeleteVO> request);

    /**
     *  试卷批量删除
     * @param request 带有id列表和对应的version列表的请求参数
     * @return 删除结果
     */
    @DeleteMapping(value="/deletePapers")
    CommonResponse<Boolean> doDeletePapers(@Valid  @RequestBody CommonRequest<PaperDeleteVO> request);

    /**
     *  修改试卷信息
     * @param request 带有试卷修改信息的请求参数
     * @return 修改结果
     */
    @PutMapping(value="/modifyPaper")
    CommonResponse<Boolean> doModifyPaper(@Valid  @RequestBody CommonRequest<PaperModifyVO> request);

    /**
     *  修改题目内容
     * @param request 带有题目修改信息的请求参数
     * @return 修改结果
     */
    @PutMapping(value="/modifySubject")
    CommonResponse<Boolean> doModifySubject(@Valid  @RequestBody CommonRequest<SubjectModifyVO> request);

    /**
     *  修改答案信息
     * @param request 带有答案修改信息的请求参数
     * @return 修改结果
     */
    @PutMapping(value="/modifyAnswer")
    CommonResponse<Boolean> doModifyAnswer(@Valid  @RequestBody CommonRequest<AnswerModifyVO> request);

    /**
     *  删除答案
     * @param request 带有答案id的请求参数
     * @return 删除结果
     */
    @DeleteMapping(value="/deleteAnswer")
    CommonResponse<Boolean> doDeleteOneAnswer(@Valid  @RequestBody CommonRequest<Long> request);

    /**
     *  删除题目
     * @param request 带有试题id的请求参数
     * @return 删除结果
     */
    @DeleteMapping(value = "/deleteSubject")
    CommonResponse<Boolean> deleteOneSubject(@Valid  @RequestBody CommonRequest<Long> request);

    /**
     *  添加题目
     * @param request 带有添加的题目信息请求参数
     * @return 添加结果
     */
    @PostMapping(value = "/addSubject")
    CommonResponse<Boolean> addOneSubject(@Valid @RequestBody CommonRequest<SubjectInsertVO> request);

    /**
     *  添加答案
     * @param request 带有添加的答案信息的请求参数
     * @return 添加结果
     */
    @PostMapping(value = "/addAnswer")
    CommonResponse<Boolean> addOneAnswer(@Valid @RequestBody CommonRequest<AnswerAddVO> request);

    /**
     *  修改试卷状态
     * @param request 带有试卷id和修改状态的请求参数
     * @return 修改结果
     */
    @PutMapping(value="/modifyPaperStatusEnable")
    CommonResponse<Boolean> doModifyPaperStatusEnable(@Valid @RequestBody CommonRequest<PaperStatusModifyVO> request);
}
