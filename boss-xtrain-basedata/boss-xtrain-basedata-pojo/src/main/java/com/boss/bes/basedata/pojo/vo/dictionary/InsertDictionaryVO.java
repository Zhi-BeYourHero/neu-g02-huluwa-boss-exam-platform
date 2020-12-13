package com.boss.bes.basedata.pojo.vo.dictionary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 增加字典
 * @create 2020-07-06 22:44
 * @since
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsertDictionaryVO {
    /**
     * 数据字典id
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态位
     */
    private Integer status;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    /**
     * 版本
     */
    private Long version;
}
