package com.boss.bes.paper.pojo.vo.makepaper.make;

import lombok.Data;
import com.boss.bes.paper.pojo.vo.makepaper.config.ConfigMakeVO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.pojo.vo.makepaper.make
 * @Description:
 * @Date: 2020/7/11 20:53
 * @since: 1.0
 */
@Data
public class MakePaperVO {

    /**
     * 试卷名
     * */
    @NotEmpty(message = "试卷名不能为空")
    private String name;
    /**
     * 试卷类型
     * */
    @NotNull(message = "试卷类型不能为空")
    private Long paperType;
    /**
     * 试卷难度
     * */
    @NotNull(message = "试卷难度不能为空")
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
     * 配置
     * */
    @NotNull(message = "配置信息不能为空")
    private ConfigMakeVO config;
}
