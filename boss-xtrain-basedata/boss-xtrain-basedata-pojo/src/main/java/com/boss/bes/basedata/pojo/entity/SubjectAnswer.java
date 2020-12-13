package com.boss.bes.basedata.pojo.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * 题目答案
 */
@Data
@Table(name = "t_subject_answer")
public class SubjectAnswer implements Serializable {
    /**
     * 答案ID
     */
    @Id
    @Column(name = "subject_answer_id")
    private Long subjectAnswerId;

    /**
     * 题目ID
     */
    @Column(name = "subject_id")
    private Long subjectId;

    /**
     * 答案
     */
    @Column(name = "answer")
    private String answer;

    /**
     * 正确
     */
    @Column(name = "right_answer")
    private Integer rightAnswer;

    /**
     * 图片路径
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 保留字段1
     */
    @Column(name = "field1")
    private String field1;

    /**
     * 保留字段2
     */
    @Column(name = "field2")
    private String field2;

    /**
     * 保留字段3
     */
    @Column(name = "field3")
    private String field3;

    private static final long serialVersionUID = 1L;
}