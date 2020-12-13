package com.boss.bes.paper.dao.mapper;

import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-dao
 * @Description: 试卷试题答案mapper
 * @Date: 2020/7/7 14:25
 * @since: 1.0
 */
@Repository
public interface PaperSubjectAnswerMapper extends MySqlMapper<PaperSubjectAnswer>, Mapper<PaperSubjectAnswer> {

    int insertAnswers(List<PaperSubjectAnswer> answers);
    List<PaperSubjectAnswer> selectSubjectAnswer(@Param("subjectIds") List<Long> subjectIds);
}
