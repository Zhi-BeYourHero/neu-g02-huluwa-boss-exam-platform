package com.boss.bes.basedata.pojo.dto.combexamconfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CombExamConfigListDTO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long combExamId;
    /**
     * 配置名
     */
    private String name;
    /**
     * 修改人
     */
    private Long updatedBy;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 难度ID
     */
    private Long difficulty;
}
