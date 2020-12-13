package com.boss.bes.basedata.pojo.vo.subject;
import com.boss.bes.basedata.pojo.entity.SubjectAnswer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDataItemVO {
    /**
     * 题目ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;
    /**
     * 题型_题型ID
     */
    private Long subjectTypeId;
    /**
     * 题目类_题目类别ID
     */
    private Long categoryId;
    /**
     * 题目
     */
    private String name;
    /**
     * 题型名称
     */
    private String subjectTypeName;
    /**
     * 题类别
     */
    private String categoryName;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 难度ID
     */
    private Long difficulty;
    /**
     * 组织机构ID
     */
    private Long orgId;
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 版本
     */
    private Long version;
    private List<SubjectAnswer> answers;
    private Integer start;
    private Integer size;
}
