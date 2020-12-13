package com.boss.bes.system.service;

import com.boss.bes.system.dto.PositionDto;
import com.boss.bes.system.entity.Position;
import com.boss.bes.system.query.PositionQuery;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 职位的服务接口
 * @create 2020-07-07
 * @since 1.0
 */

public interface PositionService{
    /**
     * 保存一个新建的职位对象
     * @param positionDto 职位的DTO对象
     * @return 影响的行数
     */
    Integer save(PositionDto positionDto);

    /**
     * 删除一个指定的职位对象
     * @param id 职位的id
     * @return 影响的行数
     */
    Integer remove(Long id);

    /**
     * 更新一个指定的角色对象
     * @param positionDto 职位的Dto对象
     * @return 影响的行数
     */
    Integer update(PositionDto positionDto);

    /**
     * 按条件进行查询
     * @param positionQuery 封装的查询条件
     * @return 查询的返回值
     */
    List<Position> query(PositionQuery positionQuery);

    /**
     * 显示所有职位信息
     * @return 所有职位信息
     */
    List<Position> listAll();

    /**
     * 根据职位id获取职位信息
     * @param id 职位id
     * @return 职位对象
     */
    Position findPositionById(Long id);

    /**
     * 批量删除职位
     * @param ids 选中的id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);

    /**
     * 列出一个公司的所有职位
     * @param companyId 公司id
     * @return 该公司的所有职位
     */
    List<Position> listAllByCompanyId(Long companyId);
}
