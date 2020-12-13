package com.boss.bes.paper.dao.impl;

import com.boss.bes.paper.dao.PaperSubjectAnswerDAO;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import com.boss.bes.paper.dao.mapper.PaperSubjectAnswerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.dao.impl
 * @Description:
 * @Date: 2020/7/8 9:23
 * @since: 1.0
 */
@Repository
public class PaperSubjectAnswerDaoImpl implements PaperSubjectAnswerDAO {


    @Autowired
    private PaperSubjectAnswerMapper paperSubjectAnswerMapper;


    /**
     * 插入答案
     * @param answer 待插入的答案数据
     * @return 插入结果
     */
    @Override
    public int insertOneAnswer(PaperSubjectAnswer answer) {
        return paperSubjectAnswerMapper.insertSelective(answer);
    }

    /**
     * 批量插入答案
     * @param answers 待插入的答案数据
     * @return 插入结果
     */
    @Override
    public int insertAnswers(List<PaperSubjectAnswer> answers) {
        return paperSubjectAnswerMapper.insertAnswers(answers);
    }

    /**
     * 根据答案id删除答案
     * @param id 答案id
     * @return 删除结果
     */
    @Override
    public int deleteAnswer(Long id) {
        return paperSubjectAnswerMapper.deleteByPrimaryKey(id);
    }

    /**
     *  修改答案
     * @param answer 修改答案
     * @return 修改结果
     */
    @Override
    public int modifyAnswer(PaperSubjectAnswer answer) {
        Example example=new Example(PaperSubjectAnswer.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("id",answer.getId());
        return paperSubjectAnswerMapper.updateByExampleSelective(answer,example);
    }

    /**
     * 根据答案id查询答案
     * @param id 答案id
     * @return 对应的答案数据
     */
    @Override
    public PaperSubjectAnswer selectOneAnswer(Long id) {
        return paperSubjectAnswerMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据题目id列表查询答案
     * @param subjectIds 题目id列表
     * @return 题目答案
     */
    @Override
    public List<PaperSubjectAnswer> selectSubjectAnswer(List<Long> subjectIds) {
        return paperSubjectAnswerMapper.selectSubjectAnswer(subjectIds);
    }

}
