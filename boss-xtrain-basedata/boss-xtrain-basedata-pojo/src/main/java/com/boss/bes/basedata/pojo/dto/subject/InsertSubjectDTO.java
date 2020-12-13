package com.boss.bes.basedata.pojo.dto.subject;
import com.boss.bes.basedata.pojo.entity.SubjectAnswer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class InsertSubjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目ID
     */
    private Long subjectId;
    @NotNull
    private String subjectTypeName;
    @NotNull
    private String categoryName;
    /**
     * 题目
     */
    @NotNull
    private String name;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 难度ID
     */
    @NotNull
    private Long difficulty;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态位
     */
    @NotNull
    private Integer status;
    /**
     * 组织机构ID
     */
    private Long orgId;
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 版本
     */
    private Long version;
    private List<SubjectAnswer> answers;
}
