package com.boss.bes.system.service;


import com.boss.bes.system.dto.SystemParamDto;
import com.boss.bes.system.entity.SystemParam;
import com.boss.bes.system.query.SystemParamQuery;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统参数的服务接口
 * @create 2020-07-07
 * @since 1.0
 */

public interface SystemParamService {

    /**
     * 保存一个新建的系统参数对象
     * @param systemParamDto 系统参数的DTO对象
     * @return 影响的行数
     */
    Integer save(SystemParamDto systemParamDto);

    /**
     * 删除一个指定的系统参数对象
     * @param systemParamDto 系统参数的Dto对象
     * @return 影响的行数
     */
    Integer remove(SystemParamDto systemParamDto);

    /**
     * 更新一个指定的系统参数对象
     * @param systemParamDto 系统参数的Dto对象
     * @return 影响的行数
     */
    Integer update(SystemParamDto systemParamDto);

    /**
     * 按条件进行查询
     * @param systemParamQuery 封装的查询条件
     * @return 查询的返回值
     */
    List<SystemParam> query(SystemParamQuery systemParamQuery);
    /**
     * 返回所有的系统参数
     * @return
     */
    List<SystemParam> listAll();
    /**
     * 批量删除系统参数
     * @param ids 选中的id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
