package com.boss.bes.exam.model.po;


import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-07 16:18
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_exam_user")
public class ExamUser extends BaseEntity {
    /**
     * 考试人员ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 姓名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 性别（0代表女，1代表男）
     */
    @Column(name = "sex")
    private Boolean sex;

    /**
     * 毕业院校
     */
    @Column(name = "university")
    private String university;

    /**
     * 学历
     */
    @Column(name = "educational")
    private String educational;

    /**
     * 英语水平
     */
    @Column(name = "english_level")
    private String englishLevel;

    /**
     * 专业
     */
    @Column(name = "major")
    private String major;

    /**
     * 毕业时间
     */
    @Column(name = "graduate_time")
    private LocalDate graduateTime;

    /**
     * 在校职务说明
     */
    @Column(name = "school_position")
    private String schoolPosition;

    /**
     * 实习工作经历
     */
    @Column(name = "internship_info")
    private String internshipInfo;

    /**
     * 获奖情况
     */
    @Column(name = "prize_info")
    private String prizeInfo;

    /**
     * 照片URL
     */
    @Column(name = "photo_url")
    private String photoUrl;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}