package com.boss.bes.basedata.dao.impl;
import boss.xtrain.core.exception.BusinessException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FillPublicField {
    private FillPublicField() {
    }
    private static Field match(List<Field> fields, String fieldName) {
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }
    private static List<Field> getAllField(Class cls) {
        List<Field> list = new ArrayList<>();
        while (!cls.equals(Object.class)) {
            Field[] declaredFields = cls.getDeclaredFields();
            list.addAll(Arrays.asList(declaredFields));
            cls = cls.getSuperclass();
        }
        return list;
    }
    /** 
    * @Description: 填充基本的字段 
    * @Param: [srcObj] 
    * @return: void
    */ 
    public static void fillPublicField(Object srcObj) {
        Class<?> srcClass = srcObj.getClass();
        List<Field> srcFields = getAllField(srcClass);
        String[] field={"version","status","orgId","companyId","createdBy"};
        for (Field f : srcFields) {
            for (String s : field) {
                Field match = match(srcFields, f.getName());
                if (s.equals(f.getName())&& match != null && !Modifier.isFinal(f.getModifiers())) {
                    try {
                        f.setAccessible(true);
                        match.setAccessible(true);
                        switch (s) {
                            case "status": f.set(srcObj, 1);break;
                            case "version": f.set(srcObj, 0L);break;
                            default: f.set(srcObj, 1L);break;
                        }
                    } catch (Exception e) {
                        throw new BusinessException("200100","基础数据服务异常！");
                    }
                }
            }
        }
    }
}
