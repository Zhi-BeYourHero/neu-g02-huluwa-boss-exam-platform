package com.boss.bes.system.dao.impl;

import com.boss.bes.system.dao.PositionDao;
import com.boss.bes.system.entity.Position;
import com.boss.bes.system.mapper.PositionMapper;
import com.boss.bes.system.query.PositionQuery;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 职位的Dao实现类
 * @create 2020-07-10 20:11
 * @since 1.0
 */
@Repository
public class PositionDaoImpl implements PositionDao {

    @Resource
    private PositionMapper positionMapper;


    /**
     * 保存新建的职位信息
     *
     * @param position 职位的对象
     * @return 修改的行数
     */
    @Override
    public Integer save(Position position) {
        return positionMapper.insert(position);
    }

    /**
     * 删除职位信息
     *
     * @param id 职位的id
     * @return 修改的行数
     */
    @Override
    public Integer remove(Long id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新职位信息
     *
     * @param position 职位的对象
     * @return 修改的行数
     */
    @Override
    public Integer update(Position position) {
        Example example = new Example(Position.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",position.getId());
        return positionMapper.updateByExample(position,example);
    }

    /**
     * 使用Example查询
     *
     * @param positionQuery 职位的Query对象
     * @return 查询结果
     */
    @Override
    public List<Position> queryByCondition(PositionQuery positionQuery) {
        Example example = new Example(Position.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId",positionQuery.getCompanyId());
        criteria.andEqualTo("name",positionQuery.getName());
        criteria.andEqualTo("createdBy",positionQuery.getCreatedBy());
        criteria.andEqualTo("updatedBy",positionQuery.getUpdatedBy());
        criteria.andEqualTo("status",positionQuery.getStatus());
        criteria.andEqualTo("version",positionQuery.getVersion());
        return positionMapper.selectByExample(example);
    }

    /**
     * 根据主键获取职位信息
     *
     * @param id 职位id
     * @return 职位对象
     */
    @Override
    public Position findPositionById(Long id) {
        return positionMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除职位
     *
     * @param ids 要删除的职位id
     * @return 影响的行数
     */
    @Override
    public Integer batchRemove(Long[] ids) {
        int count = 0;
        for(Long id: ids){
            count += this.positionMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}