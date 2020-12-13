package com.boss.bes.system.dto;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @description 资源的dto类
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceDto extends BaseDTO implements Serializable {
    /**
     * 资源id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 姓名
     */
    private String tenantName;

    /**
     * 编号
     */
    private String code;

    /**
     * 顺序号
     */
    private Integer orderIndex;

    /**
     * 父亲节点
     */
    private Long parentId;

    /**
     * URL
     */
    private String url;

    /**
     * 打开图标
     */
    private String openImg;

    /**
     * 关闭图标
     */
    private String closeImg;

    /**
     * 资源类型
     */
    private Integer resourceType;

    /**
     * 叶子节点
     */
    private Integer leaf;

    /**
     * 动作
     */
    private Integer action;

    /**
     * 备注
     */
    private String remark;
    private static final long serialVersionUID = 1L;
}
