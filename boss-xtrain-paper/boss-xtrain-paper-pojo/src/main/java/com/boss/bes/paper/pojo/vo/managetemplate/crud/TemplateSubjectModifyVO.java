package com.boss.bes.paper.pojo.vo.managetemplate.crud;

import com.boss.bes.paper.pojo.vo.managepaper.answer.AnswerModifyVO;
import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectModifyVO;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 题目修改参数VO
 * @Date: 2020/7/7 8:25
 * @since: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateSubjectModifyVO extends SubjectModifyVO {

    /**
     * 答案列表
     * */
    private List<AnswerModifyVO> answers;
}