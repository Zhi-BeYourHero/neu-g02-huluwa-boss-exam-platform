package com.boss.bes.basedata.pojo.vo.dictionary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DictionaryListConditionVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orgId;
    private String category;
}
