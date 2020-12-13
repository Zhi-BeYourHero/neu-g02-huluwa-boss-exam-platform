package com.boss.bes.basedata.pojo.dto.category;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryListDTO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long categoryId;
    private String name;
}
