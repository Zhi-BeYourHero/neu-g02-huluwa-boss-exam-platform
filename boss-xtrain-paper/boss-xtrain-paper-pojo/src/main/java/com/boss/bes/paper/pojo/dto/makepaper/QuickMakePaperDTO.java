package com.boss.bes.paper.pojo.dto.makepaper;

import boss.xtrain.core.data.convention.common.RequestHeader;
import java.math.BigDecimal;

import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigMakeVO;
import lombok.Data;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 快速组卷DTO
 * @Date: 2020/7/8 8:30
 * @since: 1.0
 */
@Data
public class QuickMakePaperDTO {
    /**
     * 试卷名
     * */
    private String name;
    /**
     * 试卷类型
     * */
    private Long paperType;
    /**
     * 试卷难度
     * */
    private Long difficulty;
    /**
     * 备注
     * */
    private String discript;
    /**
     * 分数
     * */
    private BigDecimal score;
    /**
     * 公司id
     * */
    private Long companyId;

    /**
     * 请求头部信息
     * */
    private RequestHeader header;

    /**
     * 组卷所使用的配置数据
     * */
    private ConfigMakeVO config;
}