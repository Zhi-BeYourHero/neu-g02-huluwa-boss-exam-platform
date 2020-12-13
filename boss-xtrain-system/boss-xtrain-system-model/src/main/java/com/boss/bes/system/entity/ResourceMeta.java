package com.boss.bes.system.entity;

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
public class ResourceMeta implements Serializable {

    private String title;
    private String icon;
    private String openImg;
    private String closeImg;
    private List<String> roles;
}
