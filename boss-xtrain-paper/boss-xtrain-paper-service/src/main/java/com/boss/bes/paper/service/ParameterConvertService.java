package com.boss.bes.paper.service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service
 * @Description:
 * @Date: 2020/7/11 20:42
 * @since: 1.0
 */
public interface ParameterConvertService {

    /**
     *  参数装换 获取参数字符串值
     * @param value 参数数字形式的数值
     * @param propertyName 参数名
     * @return 字符串值
     */
    String getStringOfPropertiesByValue(String value, int propertyName);

    /**
     *  参数装换 获取参数数字形式的数值
     * @param value 参数字符串值
     * @return 参数对应数值
     */
    String getLongOfPropertiesByValue(String value);

    /**
     *  匹配类字段
     * @param fields 对象所有字段
     * @param fieldName 要匹配的字段名
     * @return 匹配上的字段
     */
    Field match(List<Field> fields, String fieldName);

    /**
     * 转换填充试卷类型、难度、状态等参数
     * @param srcObj 转换源对象
     * @param targetObj 转换目标对象
     */
    <T> void fill(Object srcObj, T targetObj);



    /**
     * 创建对象并且填充字段
     * @param srcObj 待转换的源对象
     * @param target 将生成并返回的目标对象
     * @return 创建并转换完成的对象
     */
    <T> T createAndFill(Object srcObj, Class<T> target);

    /**
     * 集合类型对象转换
     * @param srcList 集合类型带转换对象
     * @param target 转换后的集合对象
     * @return 转换完成的集合对象
     */
    <T> List<T> convert(List srcList, Class<T> target);

    /**
     * 获取字段对应的字符串数值
     * @param field 字段名
     * @param targetObj 最终转换成的对象目标
     * @param srcFields 对象所有字段
     * @param f 字段
     */
    <T> void getString(String field,List<Field> srcFields,Field f,Object srcObj, T targetObj,int i);

    /**
     * 转换填充对象中关于创建人、更新人等字段的字符串数值
     * @param srcObj 待转换源对象
     * @param targetObj 目标对象
     */
    <T> void fillName(Object srcObj, T targetObj);
}
