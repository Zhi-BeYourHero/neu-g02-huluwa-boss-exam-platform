package com.boss.bes.paper.pojo.vo.updownpaper;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 下载试卷VO
 * @Date: 2020/7/7 8:25
 * @since: 1.0
 */
@Data
public class PaperDownloadVO {
    /**
     * 模板id
     * */
    @NotNull
    private Long id;
    /**
     * 试卷名
     * */
    @NotEmpty(message = "试卷名不能为空")
    private String name;

    /**
     * 公司id
     * */
    private Long companyId;
}
