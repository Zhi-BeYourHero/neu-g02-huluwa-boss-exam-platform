package com.boss.bes.basedata.pojo.vo.dictionary;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author linzhiyun
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 修改字典
 * @create 2020-07-06 22:58
 * @since
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateDictionaryVO {
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
     * 修改人
     */
    private Long updatedBy;
    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Long updatedTime;
    /**
     * 版本
     */
    private Long version;
}
