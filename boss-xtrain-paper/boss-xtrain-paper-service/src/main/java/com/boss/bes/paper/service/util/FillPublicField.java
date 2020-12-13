package com.boss.bes.paper.service.util;

import com.boss.bes.paper.service.exception.PaperServiceException;
import boss.xtrain.util.id.SnowFlakeUtil;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/9 9:21
 * @since: 1.0
 */
@Slf4j
public class FillPublicField {

    private FillPublicField(){
    }

    /**
     *  匹配属性
     * @param fields 对象所有字段
     * @param fieldName 字段名
     * @return 返回匹配字段
     */
    private static Field match(List<Field> fields, String fieldName) {
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }


    /**
     * 获取所有字段
     * @param cls 对象
     * @return 所有字段
     */
    public static List<Field> getAllField(Class cls) {
        List<Field> list = new ArrayList<>();
        while (!cls.equals(Object.class)) {
            Field[] declaredFields = cls.getDeclaredFields();
            list.addAll(Arrays.asList(declaredFields));
            cls = cls.getSuperclass();
        }
        return list;
    }

    /**
     * 填充基本的字段
     * @param srcObj 待转换对象
     */
    public static void fill(Object srcObj) {
        BaseUser baseUser = new BaseUser();
        try{
            String token = ControllerAspect.threadLocal.get();
            ConvertUtil.fill(JwtUtil.getJwtModel(token),baseUser);
        }catch(Exception e){
            throw new PaperServiceException("PAPER_CENTER_PUBLIC_FIELD_FILL_ERROR");
        }
        Class<?> srcClass = srcObj.getClass();
        List<Field> srcFields = getAllField(srcClass);
        String [] field={"id","version","orgId","companyId","createdBy","updatedBy",
                "updatedTime","createdTime","combExamTime"};
        for (Field f : srcFields) {
            for (String s : field) {
                if (s.equals(f.getName())) {
                    Field match = match(srcFields, f.getName());
                    if (match != null && !Modifier.isFinal(f.getModifiers())) {
                        f.setAccessible(true);
                        match.setAccessible(true);
                        fillField(f,s,baseUser,srcObj);
                    }
                }
            }
        }
    }


    /**
     *  字段识别并进行填充
     */
    private static void fillField(Field f, String str, BaseUser baseUser, Object srcObj){
        try {
            switch (str) {
                case "id":
                case "version":
                    SnowFlakeUtil snowFlakeUtil= new SnowFlakeUtil();
                    Long nid = snowFlakeUtil.nextId();
                    f.set(srcObj, nid);
                    break;
                case "orgId":
                    f.set(srcObj,baseUser.getOrgId());
                    break;
                case "companyId":
                    if(baseUser.getCompanyId()!=null){
                        f.set(srcObj,baseUser.getCompanyId());
                    }
                    break;
                case "createdBy":
                case "updatedBy":
                    f.set(srcObj,baseUser.getUserId());
                    break;
                default:
                    f.set(srcObj,null);
                    break;
            }
        } catch (Exception e) {
            throw new PaperServiceException("PAPER_CENTER_PUBLIC_FIELD_FILL_ERROR");
        }
    }

}
