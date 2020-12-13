package com.boss.bes.paper.pojo.vo.managepaper.crud;

import java.util.List;
import lombok.Data;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷总数和试卷列表
 * @Date: 2020/7/2 8:25
 * @since: 1.0
 */
@Data
public class PapersVO {
    /**
     * 试卷总数
     * */
    private Integer totalPaper;
    /**
     * 试卷列表
     * */
    private List<PaperListVO> paperListVos;
}
