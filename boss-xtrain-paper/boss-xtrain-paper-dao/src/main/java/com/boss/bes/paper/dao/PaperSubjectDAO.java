package com.boss.bes.paper.dao;

import com.boss.bes.paper.pojo.entity.PaperSubject;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo
 * @Description: PaperSubjectDAO接口
 * @Date: 2020/7/8 9:45
 * @since: 1.0
 */
public interface PaperSubjectDAO {

    /**
     * 插入题目
     * @param subject 待插入的题目数据
     * @return 插入结果
     */
    int insertSubject(PaperSubject subject);

    /**
     * 批量插入题目
     * @param subjects 待插入的题目数据
     * @return 插入结果
     */
    int insertSubjects(List<PaperSubject> subjects);

    /**
     * 根据题目id查询题目
     * @param id 根据题目id查询题目数据
     * @return 对应的题目数据
     */
    PaperSubject selectOneSubject(Long id);

    /**
     * 题目修改
     * @param subject 待修改的题目数据
     * @return 修改结果
     */
    int modifySubject(PaperSubject subject);

    /**
     * 题目删除
     * @param id 题目id
     * @return 删除结果
     */
    int deleteOneSubject(Long id);



    /**
     * 根据试卷id查询试卷题目
     * @param paperId 试卷id
     * @return 指定试卷的所有的题目数据
     */
    List<PaperSubject> selectPaperSubjects(Long paperId);


    /**
     * 根据试卷id列表和题目类型查询试卷
     * @param paperIds 试卷id列表
     * @param subjectType 题目类型
     * @return 符合条件的试卷题目
     */
    List<PaperSubject> selectSubjectsByType(List<Long> paperList, String subjectType);


}
