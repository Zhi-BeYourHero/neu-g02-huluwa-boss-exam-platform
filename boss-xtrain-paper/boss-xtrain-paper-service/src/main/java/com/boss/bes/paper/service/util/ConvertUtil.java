package com.boss.bes.paper.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/8 22:02
 * @since: 1.0
 */
public class ConvertUtil {

    private static Field match(List<Field> fields, String fieldName) {
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }

    /**
     * 创建一个target类型的对象，并将srcObj对象中属性名称与
     * target类的属性名称相同的字段 填充到该target类型的对象中去
     * 方便Entity VO DTO之间的转化
     * @param srcObj 原对象
     * @param target 目标类型
     * @param <T> 目标类型
     * @return target类型对象
     */
    public static <T> T createAndFill(Object srcObj, Class<T> target) {
        T targetObj = null;
        try {
            targetObj = target.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        fill(srcObj,targetObj);
        return targetObj;
    }

    /**
     * 将list的所有对象转换成target类型
     * @param srcList
     * @param target
     * @param <T>
     * @return
     */
    public static <T> List<T> convert(List srcList, Class<T> target){
        List<T> retList = new ArrayList<>();
        srcList.forEach(t -> retList.add(createAndFill(t, target)));
        return retList;
    }

    /**
     *  将srcObj对象中属性名称与target类的属性名称相同的字段
     *  的值填充到该target对象中去
     *  方便Entity VO DTO之间的转化
     * @param srcObj
     * @param targetObj
     * @param <T>
     */
    public static <T> void fill(Object srcObj, T targetObj) {
        Class<?> srcClass = srcObj.getClass();
        List<Field> srcFields = getAllField(srcClass);
        List<Field> targetFields = getAllField(targetObj.getClass());
        for (Field f : targetFields) {
            Field match = match(srcFields, f.getName());
            if (match != null && f.getType().equals(match.getType())&& !Modifier.isFinal(f.getModifiers())) {
                f.setAccessible(true);
                match.setAccessible(true);
                try {
                    f.set(targetObj,match.get(srcObj));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }


    private static List<Field> getAllField(Class cls) {
        List<Field> list = new ArrayList<>();
        while (!cls.equals(Object.class)) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field f : declaredFields) {
                list.add(f);
            }
            cls = cls.getSuperclass();
        }
        return list;
    }

    public static List mapTransitionList(Map map) {
        List list = new ArrayList();
        Iterator iter = map.entrySet().iterator(); //获得map的Iterator
        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            list.add(entry.getValue());
        }
        return list;
    }
}
