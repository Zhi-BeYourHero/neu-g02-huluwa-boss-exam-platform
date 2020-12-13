package com.boss.bes.paper.pojo.dto.managepaper.crud;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.dto.managepaper.crud
 * @Description:
 * @Date: 2020/7/9 10:53
 * @since: 1.0
 */
@Data
public class PaperContentDTO {

    /**
     * 试卷id
     * */
    private Long id;
    /**
     * 试卷名
     */
    private String name;
    /**
     * 试卷类型
     */
    private Long paperType;
    /**
     * 试卷难度
     */
    private Long difficulty;
    /**
     * 试卷总分
     */
    private BigDecimal score;
    /**
     * 试卷描述
     */
    private String discript;
    /**
     * 状态
     * */
    private Integer status;
    /**
     *版本
     * */
    private Long version;
    /**
     * 试题列表
     * */
    private List<PaperSubjectDTO> subjects;
}
