package com.boss.bes.exam.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 模拟考试微服务的微服务
 * @create 2020-07-10 16:32
 * @since 1.0
 */
@Service
public class FakePaperService {

    /**
     * 模拟调用考试服务获得 试卷详细
     * @return Map<Long,String> 返回值为试卷Id和试卷名的map
     * 查不到就返回空Map
     * */
    public Map<Long,String> getAllPaper(){
        return null;
    }

}
