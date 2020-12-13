package com.boss.bes.paper.pojo.dto.makepaper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 组卷参数的题目数据类DTO
 * @Date: 2020/7/8 8:30
 * @since: 1.0
 */
@ToString
public class SubjectsDTO {
    /**
     * 题目ID
     */
    @Getter
    @Setter
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 题型_题型ID
     */
    @Getter
    @Setter
    private Long subjectTypeId;

    /**
     * 题目类_题目类别ID
     */
    @Getter
    @Setter
    private Long categoryId;

    /**
     * 题目
     */
    @Getter
    @Setter
    private String name;

    /**
     * 题型名称
     */
    @Getter
    @Setter
    private String subjectTypeName;

    /**
     * 题类别
     */
    @Getter
    @Setter
    private String categoryName;

    /**
     * 状态位
     */
    @Getter
    @Setter
    private Integer status;

    /**
     * 图片路径
     */
    @Getter
    @Setter
    private String imageUrl;

    /**
     * 难度ID
     */
    @Getter
    @Setter
    private Long difficulty;

    /**
     * 组织机构ID
     */
    @Getter
    @Setter
    private Long orgId;

    /**
     * 公司ID
     */
    @Getter
    @Setter
    private Long companyId;

    /**
     * 修改时间
     */
    private Date updatedTime;

    @Getter
    @Setter
    private BigDecimal score;

    /**
     * 版本
     */
    @Getter
    @Setter
    private Long version;

    /**
     * 答案列表
     * */
    @Getter
    @Setter
    private List<PaperSubjectAnswer> answers;

    public Date getUpdateTime(){
        return (updatedTime!=null)?(Date) updatedTime.clone():null;
    }

    public void setUpdatedTime(Date updatedTime){
        this.updatedTime=(updatedTime!=null)?(Date)updatedTime.clone():null;
    }

}
