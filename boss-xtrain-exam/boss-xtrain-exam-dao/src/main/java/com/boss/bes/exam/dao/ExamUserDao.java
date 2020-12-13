package com.boss.bes.exam.dao;

import com.boss.bes.exam.model.po.ExamUser;

import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 14:04
 * @since 1.0
 */
public interface ExamUserDao {

    /**
     * 查询数据
     *
     * @param examUser 实体对象
     * @return 查询结果
     */
    List<ExamUser> query(ExamUser examUser);

    /**
     * 通过id查询数据
     *
     * @param id id
     * @return 查询结果
     */
    ExamUser selectById(long id);

    /**
     * 插入数据
     *
     * @param examUser 实体对象
     * @return 插入结果
     */
    int insert(ExamUser examUser);
}
