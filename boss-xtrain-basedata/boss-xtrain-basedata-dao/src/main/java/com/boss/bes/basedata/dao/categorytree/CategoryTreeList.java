package com.boss.bes.basedata.dao.categorytree;
import java.util.ArrayList;
import java.util.List;
/**
 * 题目类别树
 **/
public class CategoryTreeList {
    private final List<CategoryTreeNode > treeList = new ArrayList<>();
    public void addTreeNode(CategoryTreeNode  node){
        this.treeList.add(node);
    }
    public List<CategoryTreeNode> getTreeList() {
        return treeList;
    }
}
