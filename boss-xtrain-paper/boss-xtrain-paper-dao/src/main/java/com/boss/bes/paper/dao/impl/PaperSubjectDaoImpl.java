package com.boss.bes.paper.dao.impl;

import com.boss.bes.paper.dao.PaperSubjectDAO;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.dao.mapper.PaperSubjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.dao.impl
 * @Description:
 * @Date: 2020/7/8 9:24
 * @since: 1.0
 */
@Repository
public class PaperSubjectDaoImpl implements PaperSubjectDAO {

    @Autowired
    private PaperSubjectMapper paperSubjectMapper;

    private static final String PAPER_ID="paperId";



    /**
     * 插入题目
     * @param paperSubject
     * @return
     */
    public int insertSubject(PaperSubject paperSubject){
        return paperSubjectMapper.insertSelective(paperSubject);
    }

    /**
     * 批量插入题目
     * @param subjects 待插入的题目数据
     * @return 插入结果
     */
    @Override
    public int insertSubjects(List<PaperSubject> subjects) {
        return paperSubjectMapper.insertSubjects(subjects);
    }

    /**
     * 根据题目id查询题目
     * @param id 根据题目id查询题目数据
     * @return 对应的题目数据
     */
    @Override
    public PaperSubject selectOneSubject(Long id) {
        return paperSubjectMapper.selectByPrimaryKey(id);
    }

    /**
     * 题目修改
     * @param subject 待修改的题目数据
     * @return 修改结果
     */
    @Override
    public int modifySubject(PaperSubject subject) {
        Example example=new Example(PaperSubject.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("id",subject.getId());
        return paperSubjectMapper.updateByExampleSelective(subject,example);
    }

    /**
     * 题目删除
     * @param id 题目id
     * @return 删除结果
     */
    @Override
    public int deleteOneSubject(Long id) {
        return paperSubjectMapper.deleteByPrimaryKey(id);
    }



    /**
     * 根据试卷id查询试卷题目
     * @param paperId 试卷id
     * @return 指定试卷的所有的题目数据
     */
    @Override
    public List<PaperSubject> selectPaperSubjects(Long paperId) {
        Example example=new Example(PaperSubject.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo(PAPER_ID,paperId);
        return paperSubjectMapper.selectByExample(example);
    }

    /**
     * 根据试卷id列表和题目类型查询试卷
     * @param paperList 试卷id列表
     * @param subjectType 题目类型
     * @return 符合条件的试卷题目
     */
    @Override
    public List<PaperSubject> selectSubjectsByType(List<Long> paperList, String subjectType) {
        return paperSubjectMapper.selectSubjectsByType(paperList,subjectType);
    }


}
