package com.boss.bes.paper.pojo.vo.managetemplate.crud;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.boss.bes.paper.pojo.vo.managepaper.crud.PaperModifyVO;
import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 模板修改VO
 * @Date: 2020/7/7 8:25
 * @since: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateModifyVO extends PaperModifyVO {
    /**
     * 题目列表
     * */
    @NotEmpty
    private List<TemplateSubjectModifyVO> subject;
}