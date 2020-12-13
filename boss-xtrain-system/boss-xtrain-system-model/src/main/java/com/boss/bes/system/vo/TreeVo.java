package com.boss.bes.system.vo;

import com.boss.bes.system.DataTree;
import com.boss.bes.system.entity.ResourceMeta;
import lombok.Data;

import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-18
 * @since
 */
@Data
public class TreeVo implements DataTree {

    private String id;
    private String name;
    private String parentId;
    private Boolean isLeaf;
    private Boolean disabled;

    private List children;

    private String path;
    private String component;
    private ResourceMeta meta;
    private Boolean hidden ;
    private Integer orderIndex;
}
