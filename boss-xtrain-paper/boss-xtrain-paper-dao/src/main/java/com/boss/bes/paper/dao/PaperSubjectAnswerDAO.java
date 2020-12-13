package com.boss.bes.paper.dao;

import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper
 * @Description: PaperSubjectAnswerDAO接口
 * @Date: 2020/7/8 9:45
 * @since: 1.0
 */
public interface PaperSubjectAnswerDAO {



    /**
     * 插入答案
     * @param answer 待插入的答案数据
     * @return 插入结果
     */
    int insertOneAnswer(PaperSubjectAnswer answer);

    /**
     * 批量插入答案
     * @param answers 待插入的答案数据
     * @return 插入结果
     */
    int insertAnswers(List<PaperSubjectAnswer> answers);

    /**
     * 根据答案id删除答案
     * @param id 答案id
     * @return 删除结果
     */
    int deleteAnswer(Long id);

    /**
     *  修改答案
     * @param answer 修改答案
     * @return 修改结果
     */
    int modifyAnswer(PaperSubjectAnswer answer);

    /**
     * 根据答案id查询答案
     * @param id 答案id
     * @return 对应的答案数据
     */
    PaperSubjectAnswer selectOneAnswer(Long id);

    /**
     * 根据题目id列表查询答案
     * @param subjectIds 题目id列表
     * @return 题目答案
     */
    List<PaperSubjectAnswer> selectSubjectAnswer(List<Long> subjectIds);
}
