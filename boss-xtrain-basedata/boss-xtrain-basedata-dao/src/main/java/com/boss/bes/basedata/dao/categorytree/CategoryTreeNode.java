package com.boss.bes.basedata.dao.categorytree;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
/**
 * 题目类别树节点
 **/
@Data
public class CategoryTreeNode {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long categoryId;
    private String name;
    private List<CategoryTreeNode> children = new ArrayList<>();
    public void addChildren(CategoryTreeNode  node) {
        children.add(node);
    }
}
