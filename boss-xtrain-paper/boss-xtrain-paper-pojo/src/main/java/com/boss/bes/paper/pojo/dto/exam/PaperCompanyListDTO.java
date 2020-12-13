package com.boss.bes.paper.pojo.dto.exam;

import lombok.Data;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.dto.exam
 * @Description:
 * @Date: 2020/7/13 7:53
 * @since: 1.0
 */
@Data
public class PaperCompanyListDTO {

    /**
     * 试卷id
     */
    private Long id;

    /**
     * 试卷名
     */
    private String name;

    /**
     * 公司id
     */
    private Long companyId;
}
