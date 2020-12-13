package com.boss.bes.paper.pojo.vo.managepaper.crud;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷状态修改VO
 * @Date: 2020/7/6 8:25
 * @since: 1.0
 */
@Data
public class PaperStatusModifyVO {
    /**
     * 试卷id
     * */
    @NotNull
    private Long id;
    /**
     * 是否编辑
     * */
    @NotNull
    private Boolean status;
}
