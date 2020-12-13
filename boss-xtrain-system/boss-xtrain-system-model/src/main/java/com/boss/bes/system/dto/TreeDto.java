package com.boss.bes.system.dto;

import com.boss.bes.system.entity.ResourceMeta;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-18
 * @since
 */
@Data
public class TreeDto implements Serializable {

    /**
     * 参数ID
     */
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isLeaf;
    private Boolean disabled;

    private List children;

    private String path;
    private String component;
    private ResourceMeta meta;
    private Boolean hidden;
    private Integer orderIndex;
}
