package com.boss.bes.system.dao;

import com.boss.bes.system.entity.SystemParam;
import com.boss.bes.system.query.SystemParamQuery;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 定义非通用的Mapper方法
 * @create 2020-07-09
 * @since 1.0
 */

public interface SystemParamDao{

    /**
     * 保存新建的系统参数
     * @param systemParam 系统参数的对象
     * @return 修改的行数
     */
    Integer save(SystemParam systemParam);

    /**
     * 删除系统参数
     * @param systemParam 系统参数的对象
     * @return 修改的行数
     */
    Integer remove(SystemParam systemParam);

    /**
     * 更新系统参数
     * @param systemParam 系统参数的对象
     * @return 修改的行数
     */
    Integer update(SystemParam systemParam);

    /**
     * 使用Example查询
     * @param systemParamQuery 系统参数的Query对象
     * @return 查询结果
     */
    List<SystemParam> queryByCondition(SystemParamQuery systemParamQuery);
    /**
     * 批量删除系统参数
     * @param ids 要删除的系统参数id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
