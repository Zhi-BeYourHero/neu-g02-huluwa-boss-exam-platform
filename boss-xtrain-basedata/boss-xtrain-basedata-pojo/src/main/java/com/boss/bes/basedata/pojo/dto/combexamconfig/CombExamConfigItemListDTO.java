package com.boss.bes.basedata.pojo.dto.combexamconfig;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CombExamConfigItemListDTO {
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
