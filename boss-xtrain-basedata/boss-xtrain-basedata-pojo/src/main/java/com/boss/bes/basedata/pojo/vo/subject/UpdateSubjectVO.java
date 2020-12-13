package com.boss.bes.basedata.pojo.vo.subject;
import com.boss.bes.basedata.pojo.entity.SubjectAnswer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Data
public class UpdateSubjectVO {
    /**
     * 题目ID
     */
    @NotNull
    private Long subjectId;
    private String subjectTypeName;
    private String categoryName;
    /**
     * 题目
     */
    private String name;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 难度ID
     */
    private Long difficulty;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态位
     */
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
     * 修改人
     */
    private Long updatedBy;
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
     * 版本
     */
    @NotNull
    private Long version;
    private List<SubjectAnswer> answers;
}
