package com.boss.bes.system.service.impl;

import com.boss.bes.system.mapper.DictionaryMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.system.service.DictionaryService;
@Service
public class DictionaryServiceImpl implements DictionaryService{

    @Resource
    private DictionaryMapper dictionaryMapper;

}
