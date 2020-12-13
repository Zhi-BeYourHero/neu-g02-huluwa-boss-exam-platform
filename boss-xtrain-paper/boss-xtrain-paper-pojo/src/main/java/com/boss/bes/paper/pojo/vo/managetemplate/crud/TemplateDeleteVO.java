package com.boss.bes.paper.pojo.vo.managetemplate.crud;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 模板删除VO
 * @Date: 2020/7/7 8:25
 * @since: 1.0
 */
@Data
public class TemplateDeleteVO {

    /**
     * id
     * */
    @NotEmpty
    private List<Long> id;
    /**
     * version
     * */
    @NotEmpty
    private List<Long> version;
}
