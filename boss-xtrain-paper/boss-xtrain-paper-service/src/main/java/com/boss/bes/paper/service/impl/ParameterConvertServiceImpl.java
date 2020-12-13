package com.boss.bes.paper.service.impl;

import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;


import com.boss.bes.paper.service.ParameterConvertService;
import com.boss.bes.paper.service.UnitNameService;
import com.boss.bes.paper.utils.exception.PaperExceptionCode;
import com.boss.bes.paper.utils.exception.PaperException;

import com.boss.bes.paper.service.BaseDataService;
import com.boss.bes.paper.service.util.ConvertUtil;
import com.boss.bes.paper.service.util.FillPublicField;

import com.boss.bes.paper.pojo.vo.managepaper.subject.SubjectTypeListConditionVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.impl
 * @Description:
 * @Date: 2020/7/11 20:43
 * @since: 1.0
 */
@Service
@SuppressWarnings("unchecked")
public class ParameterConvertServiceImpl implements ParameterConvertService {

    @Autowired
    private BaseDataService getBaseDataService;

    @Autowired
    private UnitNameService getNameOfUnit;

    private static final int PAPER_TYPE_VALUE=1;
    private static final int DIFFICULTY_VALUE=2;
    private static final int STATUS_VALUE=3;
    private static final int CATEGORY_VALUE=4;
    private static final int SUBJECT_TYPE_VALUE=5;

    @Override
    @Cacheable(cacheNames = {"StringOfProperties"},key = "#value+#propertyName")
    public String getStringOfPropertiesByValue(String value,int propertyName) {
        CommonRequest<String> request1=new CommonRequest<>();
        CommonRequest<SubjectTypeListConditionVO> request=new CommonRequest<>();
        SubjectTypeListConditionVO subjectTypeListConditionVO=new SubjectTypeListConditionVO();
        subjectTypeListConditionVO.setOrgId(1L);
        request.setBody(subjectTypeListConditionVO);
        switch (propertyName) {
            case PAPER_TYPE_VALUE:
            case DIFFICULTY_VALUE:
            case STATUS_VALUE:
                return getThreeStr(propertyName,request1,value);
            case SUBJECT_TYPE_VALUE:
                return getFiveStr(propertyName,request,value);
            case CATEGORY_VALUE:
                return getFourStr(propertyName,request,value);
            default:
                throw new PaperException(PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_NOT_MATCH_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_NOT_MATCH_ERROR.getMessage());
        }
    }

    @Override
    public String getLongOfPropertiesByValue(String value) {
        CommonRequest<SubjectTypeListConditionVO> request=new CommonRequest<>();
        SubjectTypeListConditionVO subjectTypeListConditionVO=new SubjectTypeListConditionVO();
        subjectTypeListConditionVO.setOrgId(1L);
        request.setBody(subjectTypeListConditionVO);
        CommonResponse response = getBaseDataService.getSubjectTypes(request);
        for (Map<Object, Object> item : (List<Map<Object, Object>>) Objects.requireNonNull(response).getBody()) {
            if (item.get("name").equals(value)) {
                return (String) item.get("id");
            }
        }
        throw new PaperException(PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_NOT_MATCH_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_NOT_MATCH_ERROR.getMessage());
    }

    private List<Map<Object,Object>> getPaperTypeList(CommonRequest<String> request1){
        request1.setBody("试卷类型");
        CommonResponse response = getBaseDataService.getDictionaryByCategory(request1);
        return (List<Map<Object, Object>>) Objects.requireNonNull(response).getBody();
    }

    private List<Map<Object,Object>> getDifficultyList(CommonRequest<String> request1){
        request1.setBody("难度");
        CommonResponse response = getBaseDataService.getDictionaryByCategory(request1);
        return (List<Map<Object, Object>>) Objects.requireNonNull(response).getBody();
    }

    private List<Map<Object,Object>> getStatusList(CommonRequest<String> request1){
        request1.setBody("状态");
        CommonResponse response = getBaseDataService.getDictionaryByCategory(request1);
        return (List<Map<Object, Object>>) Objects.requireNonNull(response).getBody();
    }

    private List<Map<Object,Object>> getSubjectTypeList(CommonRequest<SubjectTypeListConditionVO> request){
        CommonResponse response = getBaseDataService.getSubjectTypes(request);
        return (List<Map<Object, Object>>) Objects.requireNonNull(response).getBody();
    }

    private List<Map<Object,Object>> getCategoryList(CommonRequest<SubjectTypeListConditionVO> request){
        CommonResponse response = getBaseDataService.getCategorys(request);
        return (List<Map<Object, Object>>) Objects.requireNonNull(response).getBody();
    }

    private String getThreeStr(int propertyName, CommonRequest<String> request1, String value){
        List<Map<Object, Object>> propertyList;
        if(propertyName==PAPER_TYPE_VALUE){
            propertyList=getPaperTypeList(request1);
        }else if(propertyName==DIFFICULTY_VALUE){
            propertyList=getDifficultyList(request1);
        }else{
            propertyList=getStatusList(request1);
        }
        return dealWithResponse(propertyList,propertyName,value);
    }

    private String getFiveStr(int propertyName, CommonRequest<SubjectTypeListConditionVO> request, String value){
        List<Map<Object,Object>> propertyList=getSubjectTypeList(request);
        return dealWithResponse(propertyList,propertyName,value);
    }

    private String getFourStr(int propertyName, CommonRequest<SubjectTypeListConditionVO> request, String value){
        List<Map<Object,Object>> propertyList=getCategoryList(request);
        return dealWithResponse(propertyList,propertyName,value);
    }

    private String dealWithResponse(List<Map<Object, Object>> propertyList, int propertyName, String value){
        for (Map<Object, Object> item : propertyList) {
            if(propertyName==DIFFICULTY_VALUE||propertyName==PAPER_TYPE_VALUE||propertyName==STATUS_VALUE){
                if (item.get("value").equals(value)) {
                    return item.get("name").toString();
                }
            }else{
                if (item.get("id").equals(value)) {
                    return item.get("name").toString();
                }
            }
        }
        return "";
    }

    /**
     *  匹配类字段
     * @param fields 对象所有字段
     * @param fieldName 要匹配的字段名
     * @return 匹配上的字段
     */
    public  Field match(List<Field> fields, String fieldName) {
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }

    /**
     * 转换填充试卷类型、难度、状态等参数
     * @param srcObj 转换源对象
     * @param targetObj 转换目标对象
     */
    public <T> void fill(Object srcObj, T targetObj) {
        Class<?> srcClass = srcObj.getClass();
        List<Field> srcFields = FillPublicField.getAllField(srcClass);
        String [] field={"paperType","difficulty","categoryId","subjectTypeId"};
        //"status"
        List<Field> targetFields = FillPublicField.getAllField(targetObj.getClass());
        for (Field f : targetFields) {
            for(int i=0;i<field.length;i++){
                getString(field[i],srcFields,f,srcObj,targetObj,i);
            }
        }
    }

    /**
     * 创建对象并且填充字段
     * @param srcObj 待转换的源对象
     * @param target 将生成并返回的目标对象
     * @return 创建并转换完成的对象
     */
    public <T> T createAndFill(Object srcObj, Class<T> target) {
        T targetObj;
        try {
            targetObj = target.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_ERROR.getMessage());
        }
        ConvertUtil.fill(srcObj,targetObj);
        fill(srcObj,targetObj);
        fillName(srcObj,targetObj);
        return targetObj;
    }

    /**
     * 集合类型对象转换
     * @param srcList 集合类型带转换对象
     * @param target 转换后的集合对象
     * @return 转换完成的集合对象
     */
    public <T> List<T> convert(List srcList, Class<T> target){
        List<T> retList = new ArrayList<>();
        srcList.forEach(t -> retList.add(createAndFill(t, target)));
        return retList;
    }


    /**
     * 获取字段对应的字符串数值
     * @param field 字段名
     * @param targetObj 最终转换成的对象目标
     * @param srcFields 对象所有字段
     * @param f 字段
     */
    public <T> void getString(String field,List<Field> srcFields,Field f,Object srcObj, T targetObj,int i){
        int [] array={1,2,3,4,5};
        if(field.equals(f.getName())){
            Field match = match(srcFields, f.getName());
            if (match != null && !Modifier.isFinal(f.getModifiers())) {
                f.setAccessible(true);
                match.setAccessible(true);
                try {
                    String value= getStringOfPropertiesByValue(match.get(srcObj).toString(),array[i]);
                    f.set(targetObj,value);
                } catch (IllegalAccessException e) {
                    throw new PaperException(PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_ERROR.getMessage());
                }
            }
        }
    }

    /**
     * 转换填充对象中关于创建人、更新人等字段的字符串数值
     * @param srcObj 待转换源对象
     * @param targetObj 目标对象
     */
    public <T> void fillName(Object srcObj, T targetObj) {
        Class<?> srcClass = srcObj.getClass();
        List<Field> srcFields = FillPublicField.getAllField(srcClass);
        String [] field={"createdBy","updatedBy","companyId","orgId"};
        List<Field> targetFields = FillPublicField.getAllField(targetObj.getClass());
        for (Field f : targetFields) {
            for(int i=0;i<field.length;i++){
                if(field[i].equals(f.getName())){
                    Field match = match(srcFields, f.getName());
                    if (match != null && !Modifier.isFinal(f.getModifiers())) {
                        f.setAccessible(true);
                        match.setAccessible(true);
                        fillField(f,i,match,targetObj,srcObj);
                    }
                }
            }
        }
    }


    /**
     * 字段查询填充方法
     */
    private <T> void fillField(Field f, int i, Field match, T targetObj, Object srcObj){
        try {
            switch (i){
                case 0:
                case 1:
                    //设置用户名字
                    f.set(targetObj, getNameOfUnit.getNameOfUser((Long) match.get(srcObj)));
                    break;
                case 2:
                    //设置公司名字
                    f.set(targetObj, getNameOfUnit.getNameOfCompany((Long) match.get(srcObj)));
                    break;
                case 3:
                    //设置组织机构名字
                    f.set(targetObj, getNameOfUnit.getNameOfOrg((Long) match.get(srcObj)));
                    break;
                default:
                    break;
            }
        } catch (IllegalAccessException e) {
            throw new PaperException(PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_ERROR.getCode(),PaperExceptionCode.PAPER_CENTER_PROPERTY_TRANSFER_ERROR.getMessage());
        }
    }



}
