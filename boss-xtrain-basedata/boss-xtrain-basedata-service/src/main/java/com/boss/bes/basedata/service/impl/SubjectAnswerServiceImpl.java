package com.boss.bes.basedata.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.basedata.mapper.SubjectAnswerMapper;
import com.boss.bes.basedata.service.SubjectAnswerService;
@Service
public class SubjectAnswerServiceImpl implements SubjectAnswerService{

    @Resource
    private SubjectAnswerMapper subjectAnswerMapper;

}
