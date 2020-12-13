package com.boss.bes.paper.pojo.vo.managepaper.crud;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷修改VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class PaperModifyVO {
    /**
     * 试卷id
     * */
    @NotNull
    private Long id;
    /**
     * 试卷名字
     * */
    @NotEmpty(message = "试卷名不能为空")
    private String name;
    /**
     * 试卷类型
     * */
    @NotNull(message = "试卷类型不能为空")
    private Long paperType;
    /**
     * 试卷难度
     * */
    @NotNull(message = "试卷难度不能为空")
    private Long difficulty;
    /**
     * 试卷备注
     * */
    private String discript;
    /**
     * 试卷总分
     */
    private BigDecimal score;

    /**
     * 试卷状态
     * */
    private Integer status;
    /**
     * 版本
     * */
    @NotNull
    private Long version;

}
