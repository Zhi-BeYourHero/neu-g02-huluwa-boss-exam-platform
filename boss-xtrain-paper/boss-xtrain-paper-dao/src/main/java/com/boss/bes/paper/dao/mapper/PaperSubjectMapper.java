package com.boss.bes.paper.dao.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-dao
 * @Description: 试卷试题mapper
 * @Date: 2020/7/7 14:25
 * @since: 1.0
 */
@Repository
public interface PaperSubjectMapper extends CommonMapper<PaperSubject> {
    int insertSubjects(List<PaperSubject> subjects);
    List<PaperSubject> selectSubjectsByType(@Param("paperList")List<Long> paperList, @Param("subjectType") String subjectType);
}
