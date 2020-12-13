package com.boss.bes.basedata.pojo.vo.combexamconfig;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CombExamConfigItemListVO {
    private String category;
    private String subjectType;
    private Long difficult;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 分值
     */
    private BigDecimal score;
}
