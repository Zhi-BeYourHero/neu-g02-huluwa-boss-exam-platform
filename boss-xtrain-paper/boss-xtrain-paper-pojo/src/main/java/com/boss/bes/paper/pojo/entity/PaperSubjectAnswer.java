package com.boss.bes.paper.pojo.entity;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷答案实体类
 * @Date: 2020/7/2 8:25
 * @since: 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="t_paper_subject_answer")
public class PaperSubjectAnswer extends BaseEntity {

    /**
     * 试卷试题答案id
     */
    @Id
    private Long id;

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 答案
     */
    private String answer;

    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 正确答案
     */
    private Boolean rightAnswer;

    /**
     * 保留字段1
     */
    private String field1;

    /**
     * 保留字段2
     */
    private String field2;

    /**
     * 保留字段3
     */
    private String field3;

    /**
     * 组织机构ID
     */
    private Long orgId;

    /**
     * 公司id
     */
    private Long companyId;

    private static final long serialVersionUID = 1L;


}