package com.boss.bes.system;

import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 树形菜单工具类
 * @create 2020-07-18
 * @since
 */
public interface DataTree<T> {

    /**
     * 获取ID
     * @return
     */
    String getId();

    /**
     * 获取父节点ID
     * @return
     */
    String getParentId();

    /**
     * 设置子节点集合
     * @param children
     */
    void setChildren(List<T> children);

    /**
     * 获取子节点集合
     * @return
     */
    List<T> getChildren();

    String getPath();

    String getComponent();

    void setComponent(String component);
}