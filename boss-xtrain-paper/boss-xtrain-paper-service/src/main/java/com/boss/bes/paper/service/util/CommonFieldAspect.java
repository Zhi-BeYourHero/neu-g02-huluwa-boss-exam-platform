package com.boss.bes.paper.service.util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/9 10:30
 * @since: 1.0
 */
@Slf4j
@Aspect
@Component
public class CommonFieldAspect {


    /**
     * 注解所需参数值，表示类型为查询方法
     */
    public static final String TYPE_SELECT = "select";
    /**
     * 注解所需参数值，表示类型为删除方法
     */
    public static final String TYPE_DELETE = "delete";
    /**
     * 注解所需参数值，表示类型为插入方法
     */
    public static final String TYPE_INSERT = "insert";
    /**
     * 注解所需参数值，表示类型为更新方法
     */
    public static final String TYPE_UPDATE = "update";
    /**
     * 默认方法类型
     */
    private static final String DEFAULT_METHOD_TYPE = TYPE_UPDATE;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(SetCommonField)")
    public void commonFieldPoint() {
    }

    @Before("commonFieldPoint()")
    public void setCommonField(JoinPoint joinPoint) throws Exception {

        //从threadLocal中获取token
        String token = ControllerAspect.threadLocal.get();
        if(null == token){
            throw new Exception("用户Id不存在");
        }
        //获取methodType
        String methodType = getMethodType(joinPoint);
        //通过userId获取相应字段值
        BaseEntity baseEntity = getCommonField(token);
        //根据方法类型从commonField对象中抽取不同字段设置进入切入点方法参数中
        int result = setFields(baseEntity, joinPoint, methodType);
    }

    /**
     * 根据methodType抽取不同的commonField中字段设置进切入点方法参数
     *
     * @param baseEntity 取到的公共字段值
     * @param joinPoint   连接点
     * @param methodType  所切方法类型
     * @return int 执行情况 0表示正常完成
     */
    private int setFields(BaseEntity baseEntity, JoinPoint joinPoint, String methodType) throws Exception {
        if (!TYPE_INSERT.equals(methodType)) {
            baseEntity.setCreatedBy(null);
            baseEntity.setCreatedTime(null);
            if (TYPE_SELECT.equals(methodType)) {
                baseEntity.setUpdatedBy(null);
                baseEntity.setUpdatedTime(null);
                baseEntity.setOrgId(null);
                baseEntity.setCompanyId(null);
            }
            if (TYPE_UPDATE.equals(methodType)||TYPE_DELETE.equals(methodType)) {
                baseEntity.setOrgId(null);
                baseEntity.setCompanyId(null);
            }
        }

        //获取切点第一个参数
        Object[] args = joinPoint.getArgs();
        Object param = null;
        if (args != null) {
            param = args[0];
        }
        if (param == null) {

            //方法参数为空 直接return 1 代表设置公共字段结束
            return 1;
        }

        //把对象转化为数组
        Field[] fields = FieldUtils.getAllFields(baseEntity.getClass());
        Field[] fieldParams = FieldUtils.getAllFields(param.getClass());

        //遍历CommonField所有字段
        try {
            for (Field field : fields) {

                //设置字段可以访问
                field.setAccessible(true);
                Object fieldValue = field.get(baseEntity);

                //字段非null时将其设值给切点参数param
                if (null != fieldValue) {

                    //获取param对应的setter方法后执行
                    String fieldName = field.getName();

                    //循环遍历参数中是否存在需要被填充的字段
                    for (Field fieldParam : fieldParams) {
                        String fieldParamName = fieldParam.getName();
                        if (fieldName.equals(fieldParamName)) {
                            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                            Method fieldSetter = null;
                            if (fieldParam.getType().getSimpleName().equals("Date")) {
                                fieldSetter = param.getClass().getMethod("set" + fieldName, Date.class);
                                fieldValue = new Date();
                            } else {
                                fieldSetter = param.getClass().getMethod("set" + fieldName, field.getType());
                            }
                            fieldSetter.invoke(param, fieldValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("异常");
        }
        return 0;
    }

    private BaseEntity getCommonField(String token) throws Exception {
        BaseUser baseUser = new BaseUser();
        ConvertUtil.fill(JwtUtil.getJwtModel(token),baseUser);
        if (baseUser == null){
            log.info("无法获取用户信息");
        }
        BaseEntity baseEntity = new BaseEntity();
        BeanUtils.copyProperties(baseUser, baseEntity);
        baseEntity.setOrgId(baseUser.getOrgId());
        baseEntity.setCompanyId(baseUser.getCompanyId());
        baseEntity.setCreatedBy(baseUser.getUserId());
        baseEntity.setUpdatedBy(baseUser.getUserId());

        //填入值不为空 无实际意义
        baseEntity.setCreatedTime(new Date());
        baseEntity.setUpdatedTime(new Date());
        return baseEntity;
    }
    private String getMethodType(JoinPoint joinPoint){

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        SetCommonField setCommonFieldAnnotation  = method.getAnnotation(SetCommonField.class);
        String methodType = DEFAULT_METHOD_TYPE;
        if (null != setCommonFieldAnnotation) {
            if (TYPE_UPDATE.equalsIgnoreCase(setCommonFieldAnnotation.methodType())) {
                methodType = TYPE_UPDATE;
            } else if (TYPE_SELECT.equalsIgnoreCase(setCommonFieldAnnotation.methodType())) {
                methodType = TYPE_SELECT;
            } else if (TYPE_INSERT.equalsIgnoreCase(setCommonFieldAnnotation.methodType())) {
                methodType = TYPE_INSERT;
            } else if (TYPE_DELETE.equalsIgnoreCase(setCommonFieldAnnotation.methodType())) {
                methodType = TYPE_DELETE;
            }
        }
        return methodType;
    }
}
