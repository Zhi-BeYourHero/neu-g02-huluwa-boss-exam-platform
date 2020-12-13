package com.boss.bes.paper.pojo.vo.managepaper.subject;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 添加试题VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class SubjectInsertVO extends BaseSubjectVO{

    /**
     * 公司ID
     */
    private Long companyId;
}
