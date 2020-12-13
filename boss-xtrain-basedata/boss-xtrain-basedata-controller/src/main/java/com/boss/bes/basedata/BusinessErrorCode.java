package com.boss.bes.basedata;

import lombok.Getter;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 异常码
 * @create 2020-07-09 00:20
 * @since
 */
@Getter
public enum BusinessErrorCode {
    /**
     * 基础数据服务异常
     */
    BASE_DATA_ERROR("200100", "基础数据服务加载失败！"),
    BASE_DATA_DICTIONARY_CODE_REPET("200101","数据字典已存在"),
    BASE_DATA_DICTIONARY_INSERT_ERROR("200102","添加数据字典失败"),
    BASE_DATA_DICTIONARY_DELETE_ERROR("200103","删除数据字典失败"),
    BASE_DATA_DICTIONARY_UPDATE_ERROR("200104","修改数据字典失败"),
    BASE_DATA_DICTIONARY_QUERY_ERROR("200105","查询数据字典失败"),
    BASE_DATA_DICTIONARY_UNFREEZE_ERROR("200106","启用数据字典失败"),
    BASE_DATA_DICTIONARY_FREEZE_ERROR("200107","停用数据字典失败"),

    BASE_DATA_CATEGORY_CODE_REPET("200201","题目类别已存在"),
    BASE_DATA_CATEGORY_INSERT_ERROR("200202","添加题目类别失败"),
    BASE_DATA_CATEGORY_DELETE_ERROR("200203","删除题目类别失败"),
    BASE_DATA_CATEGORY_UPDATE_ERROR("200204","修改题目类别失败"),
    BASE_DATA_CATEGORY_QUERY_ERROR("200205","查询题目类别失败"),
    BASE_DATA_CATEGORY_UNFREEZE_ERROR("200206","启用题目类别失败"),
    BASE_DATA_CATEGORY_FREEZE_ERROR("200207","停用题目类别失败"),

    BASE_DATA_SUBJECTTYPE_CODE_REPET("200301","题型已存在"),
    BASE_DATA_SUBJECTTYPE_INSERT_ERROR("200302","添加题型失败"),
    BASE_DATA_SUBJECTTYPE_DELETE_ERROR("200303","删除题型失败"),
    BASE_DATA_SUBJECTTYPE_UPDATE_ERROR("200304","修改题型失败"),
    BASE_DATA_SUBJECTTYPE_QUERY_ERROR("200305","查询题型失败"),
    BASE_DATA_SUBJECTTYPE_UNFREEZE_ERROR("200306","启用题型失败"),
    BASE_DATA_SUBJECTTYPE_FREEZE_ERROR("200307","停用题型失败"),

    BASE_DATA_SUBJECT_CODE_REPET("200401","题目已存在"),
    BASE_DATA_SUBJECT_INSERT_ERROR("200402","添加题目失败"),
    BASE_DATA_SUBJECT_DELETE_ERROR("200403","删除题目失败"),
    BASE_DATA_SUBJECT_UPDATE_ERROR("200404","修改题目失败"),
    BASE_DATA_SUBJECT_QUERY_ERROR("200405","查询题目失败"),
    BASE_DATA_SUBJECT_UNFREEZE_ERROR("200406","启用题目失败"),
    BASE_DATA_SUBJECT_FREEZE_ERROR("200407","停用题目失败"),

    BASE_DATA_COMB_CODE_REPET("200501","组卷配置已存在"),
    BASE_DATA_COMB_INSERT_ERROR("200502","添加组卷配置失败"),
    BASE_DATA_COMB_DELETE_ERROR("200503","删除组卷配置失败"),
    BASE_DATA_COMB_UPDATE_ERROR("200504","修改组卷配置失败"),
    BASE_DATA_COMB_QUERY_ERROR("200505","查询组卷配置失败"),
    BASE_DATA_COMB_UNFREEZE_ERROR("200506","启用组卷配置失败"),
    BASE_DATA_COMB_FREEZE_ERROR("200507","停用组卷配置失败");
    /**
     * 异常码
     */
    private final String code;
    /**
     * 异常信息
     */
    private final String message;

    BusinessErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
