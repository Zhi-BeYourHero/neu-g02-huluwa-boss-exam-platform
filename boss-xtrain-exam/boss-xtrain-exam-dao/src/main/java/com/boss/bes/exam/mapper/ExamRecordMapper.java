package com.boss.bes.exam.mapper;

import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;
import com.boss.bes.exam.model.po.ExamRecord;
import com.boss.bes.exam.model.po.ExamRecordQueryResult;
import com.boss.bes.exam.model.query.ExamRecordQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 16:18
 * @since 1.0
 */
@Mapper
public interface ExamRecordMapper extends CommonMapper<ExamRecord> {

    List<ExamRecord> getById(@Param("ids")List<Long> ids);

    List<ExamRecordQueryResult> getExamRecordByCondition(ExamRecordQuery query);

    int getExamPeopleNum(long id);

    int updateTag(@Param("id") Long id, @Param("tag") String tag);
}