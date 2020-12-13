package com.boss.bes.basedata.pojo.vo.dictionary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 删除字典
 * @create 2020-07-06 22:55
 * @since
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteDictionaryVO {
    /**
     * 数据字典id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 版本
     */
    private Long version;
}
