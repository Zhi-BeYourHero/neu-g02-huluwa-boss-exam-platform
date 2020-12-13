package com.boss.bes.system.dao;


import com.boss.bes.system.entity.Position;
import com.boss.bes.system.query.PositionQuery;

import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 定义非通用的Mapper方法
 * @create 2020-07-10 20:03
 * @since 1.0
 */

public interface PositionDao{

    /**
     * 保存新建的职位信息
     * @param position 职位的对象
     * @return 修改的行数
     */
    Integer save(Position position);

    /**
     * 删除职位信息
     * @param id 职位的id
     * @return 修改的行数
     */
    Integer remove(Long id);

    /**
     * 更新职位信息
     * @param position 职位的对象
     * @return 修改的行数
     */
    Integer update(Position position);

    /**
     * 使用Example查询
     * @param positionQuery 职位的Query对象
     * @return 查询结果
     */
    List<Position> queryByCondition(PositionQuery positionQuery);

    /**
     * 根据主键获取职位信息
     * @param id 职位id
     * @return 职位对象
     */
    Position findPositionById(Long id);

    /**
     * 批量删除职位
     * @param ids 要删除的职位id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
