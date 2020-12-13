package com.boss.bes.exam.util;
/**
 * @author fhf
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-06 21:00
 * @since 1.0
 */
public enum ExamExceptionCode {
    /**
     * 考试发布
     */
    EXAM_PUBLISHED("240101", "考试已发布,无法重复发布"),
    EXAM_PUBLISHED_UPDATE("240102","已经发布考试无法修改"),
    EXAM_PUBLISHED_DELETE("240103","目标删除考试记录已被更新或删除,请更新数据后重试"),
    EXAM_PUBLISH_ID_GENERATE_FAILED("240104","添加考试记录过程中ID生成失败"),
    EXAM_PUBLISH_REVIEWER_RELATION_GENERATE_FAILED("240105","添加考试记录过程中,阅卷官中间表插入失败"),
    EXAM_QR_CODE_GENERATE_FAILED("240106","发布过程中生成二维码失败"),
    EXAM_PUBLISH_CALL_PAPER_FAILED("240107","考试发布，调用试卷微服务失败"),
    EXAM_PUBLISH_CALL_SYSTEM_FAILED("240108", "考试发布，调用系统管理服务失败"),
    EXAM_CONVERT_DTO_TO_VO_FAILED("240109", "考试发布，调用工具类复制属性DTO->VO失败"),
    EXAM_CONVERT_DTO_TO_ENTITY_FAILED("2401010", "考试发布，调用工具类复制属性DTO->ENTITY失败"),
    EXAM_CONVERT_ENTITY_TO_DTO_FAILED("240111", "考试发布，调用工具类复制属性ENTITY->DTO失败"),
    EXAM_INSERT_FAILED("240112", "添加考试,插入数据库失败"),
    EXAM_PARAMETER_VALID_FAILED("240113", "参数校验异常"),
    EXAM_PUBLISH_DELETE_NO_ID("240114", "删除发布记录ID列表为空"),
    EXAM_PUBLISHED_DELETE_FAILED("240115","目标发布记录已经发布或更新，请更新数据后重新尝试"),
    EXAM_PUBLISH_EMPTY("240116","空的发布考试参数"),
    EXAM_PUBLISH_FAILED("240117","目标发布记录已经发布或更新，请更新数据后充实"),
    EXAM_UPLOAD_IMAGE("240118","调用cdnUtil工具类失败"),
    EXAM_PUBLISH_EXAM_FAILED("240119","调用cdnUtil工具类失败"),
    EXAM_UPDATE_LATE("240120","目标更新考试记录已被更新或发布无法修改,请更新数据后重试"),
    EXAM_EXAM_GET_EVALUATE_PAPER_EMPTY_PARAM("240121","查询考试记录异常"),
    EXAM_EXAM_GET_ANSWER_RECORD_ERROR("240122","通过使用考试记录ID和试题ID查询异常"),
    EXAM_EXAM_EVALUATE_ANSWER_RECORD_ERROR("240123","评价答题详细记录更新失败"),
    EXAM_EXAM_GET_PAPER_FAILED("240124","查询考试试卷详细失败"),
    EXAM_EXAM_PAPER_PUBLISH_FAILED("240125","调用试卷微服务发布试卷失败"),

    /**

    /**
     * 考试评卷
     */
    EXAM_EVALUATE_CALL_PAPER_FAILED("240109","评卷调用试卷微服务失败"),

    /**
     * 手机答卷
     */
    REGISTER_ERROR("240201", "考试人员注册失败，请重新注册"),
    PASSWORD_ERROR("240202", "手机号或密码错误，请重新登录"),
    CODE_ERROR("240203", "验证码错误或过期，请重新登录"),
    FACE_ERROR("240204", "人脸识别失败，请重新登录"),
    VERIFACE_ERROR("240205", "人脸识别失败"),
    GENERATE_CODE_ERROR("240206", "验证码生成失败"),
    SUBMIT_ERROR("240207", "提交答卷失败"),
    IMG_UPLOAD_ERROR("240208", "图片上传失败"),
    /**
     * 答卷查询
     */
    ANSWER_PAPER_ERROR("240301", "答卷查询失败，请稍后重试"),

    /**
     * 考试报表
     */
    EXAM_REPORT_ERROR("240401", "考试报表生成失败，请稍后重试"),
    EXPORT_ERROR("240402", "导出报表失败，请稍后重试"),
    /**
     * 远程调用错误
     * */
    CALL_PAPER_SERVICE_FAILED("240501","调用考试服务失败"),
    CALL_SYSTEM_SERVICE_FAILED("240601","调用系统服务失败"),
    CALL_BASE_DATA_SERVICE_FAILED("240602","调用基础数据服务失败");
    /**
     * 异常码
     */
    private final String code;
    /**
     * 异常信息
     */
    private final String message;

    ExamExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
