package com.boss.bes.paper.pojo.vo.managetemplate.template;

import java.util.List;

import lombok.Data;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 模板返回数据VO
 * @Date: 2020/7/7 8:25
 * @since: 1.0
 */
@Data
public class TemplateVO {
    /**
     * 模板总数
     * */
    private Integer totalPaper;
    /**
     * 模板列表
     * */
    private List<TemplateListVO> templates;
}
