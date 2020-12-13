package com.boss.bes.exam.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用来模拟调用系统服务部分的服务
 * @create 2020-07-08 15:22
 * @since 1.0
 */
@Service
public class FakeSystemService {
    /**
     * 模拟调用系统管理微服务获得模糊查询的用户ID
     * @param username 参数为 用户名 模糊查询
     * @return Map<Long,String> 返回值为用户Id和用户名的map
     * */
    public Map<Long,String> getAllPublisher(String username){
        Map<Long,String> result = new HashMap<>();
        //先写死
        if(username.equals("1")){
            result.put(2L,"张三");
            result.put(3L,"李四");
        }
        else{
            result.put(4L,"王五");
        }
        return result;
    }

    /**
     * 模拟调用系统微服务获取所有阅卷官
     * @return Map<Long,String> 返回值为所有阅卷官ID和阅卷官名称
     * */
    public Map<Long,String> getAllReviewer(){
        Map<Long,String> result = new HashMap<>();
        result.put(1L,"admin");
        result.put(2L,"张三");
        result.put(3L,"李四");
        result.put(4L,"王五");
        return result;
    }
}
