package com.boss.bes.exam.dao.impl;

import com.boss.bes.exam.dao.ExamUserDao;
import com.boss.bes.exam.mapper.ExamUserMapper;
import com.boss.bes.exam.model.po.ExamUser;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08 14:04
 * @since 1.0
 */
@Repository
public class ExamUserDaoImpl implements ExamUserDao {

    @Resource
    private ExamUserMapper examUserMapper;

    /**
     * 查询数据
     *
     * @param examUser 实体对象
     * @return 查询结果
     */
    @Override
    public List<ExamUser> query(ExamUser examUser) {
        return examUserMapper.select(examUser);
    }

    /**
     * 通过id查询数据
     *
     * @param id id
     * @return 查询结果
     */
    @Override
    public ExamUser selectById(long id) {
        return examUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 插入数据
     *
     * @param examUser 实体对象
     * @return 插入结果
     */
    @Override
    public int insert(ExamUser examUser) {
        return examUserMapper.insert(examUser);
    }
}
