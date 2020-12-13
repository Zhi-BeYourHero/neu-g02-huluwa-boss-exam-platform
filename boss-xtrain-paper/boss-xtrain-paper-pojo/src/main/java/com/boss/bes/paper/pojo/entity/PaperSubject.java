package com.boss.bes.paper.pojo.entity;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author: wangziyiPaperSubject
 * Paper
 * PaperSubjectAnswer
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷试题实体类
 * @Date: 2020/7/2 8:25
 * @since: 1.0
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="t_paper_subject")
public class PaperSubject extends BaseEntity {

    /**
     * 考试题目id
     */
    @Id
    private Long id;

    /**
     * 试卷id
     */
    private Long paperId;

    /**
     * 题目
     */
    private String subject;

    /**
     * 题型
     * */
    private String subjectType;

    /**
     * 题目类别
     */
    private Long categoryId;

    /**
     * 难度
     */
    private Long difficulty;


    /**
     * 分数
     */
    private BigDecimal score;

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
