package com.boss.bes.basedata.pojo.dto.dictionary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 字典查找
 * @create 2020-07-08 16:18
 * @since
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DictionaryQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 数据字典id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 组织机构id
     */
    private Long orgId;
    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典类型
     */
    private String category;
    /**
     * 参数值
     */
    private String value;
}
