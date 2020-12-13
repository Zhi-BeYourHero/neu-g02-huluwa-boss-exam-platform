package com.boss.bes.system.service.impl;

import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.SystemException;
import com.boss.bes.system.dao.PositionDao;
import com.boss.bes.system.dto.PositionDto;
import com.boss.bes.system.entity.Position;
import com.boss.bes.system.query.PositionQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.system.service.PositionService;

import java.util.Date;
import java.util.List;

import static boss.xtrain.util.convert.BeanUtil.*;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 职位的服务实现类
 * @create 2020-07-10 22:17
 * @since 1.0
 */

@Service
@Slf4j
public class PositionServiceImpl implements PositionService{

    @Resource
    private PositionDao positionDao;

    private static final String MODULE_NAME = "POSITION";

    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);

    /**
     * 保存一个新建的职位对象
     *
     * @param positionDto 职位的DTO对象
     * @return 影响的行数
     */
    @Override
    public Integer save(PositionDto positionDto) {
        try{
            Position position = this.doDtoTransf2Entity(positionDto);
            Long id = this.snowflakeWorker.nextId();
            position.setId(id);
            position.setCreatedTime(new Date(System.currentTimeMillis()));
            position.setVersion(1L);
            position.setUpdatedBy(null);
            position.setUpdatedTime(null);
            return positionDao.save(position);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new SystemException(MODULE_NAME,"210301","POSITION_INSERT_FAILURE");
        }
    }

    /**
     * 删除一个指定的职位对象
     *
     * @param id 职位的id
     * @return 影响的行数
     */
    @Override
    public Integer remove(Long id) {
        try{
            return positionDao.remove(id);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new SystemException(MODULE_NAME,"210302","POSITION_DELETE_FAILURE");
        }
    }

    /**
     * 更新一个指定的角色对象
     *
     * @param positionDto 职位的Dto对象
     * @return 影响的行数
     */
    @Override
    public Integer update(PositionDto positionDto) {
        try{
            Position position = this.doDtoTransf2Entity(positionDto);
            position.setUpdatedTime(new Date());
            Long newVersion = position.getVersion() + 1;
            position.setVersion(newVersion);
            return positionDao.update(position);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new SystemException(MODULE_NAME,"210303","POSITION_UPDATE_FAILURE");
        }
    }

    /**
     * 按条件进行查询
     *
     * @param positionQuery 封装的查询条件
     * @return 查询的返回值
     */
    @Override
    public List<Position> query(PositionQuery positionQuery) {
        try{
            return positionDao.queryByCondition(positionQuery);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210304","POSITION_QUERY_FAILURE");
        }
    }

    /**
     * 显示所有职位信息
     *
     * @return 所有职位信息
     */
    @Override
    public List<Position> listAll() {
        try{
            PositionQuery positionQuery = new PositionQuery();
            return positionDao.queryByCondition(positionQuery);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new SystemException(MODULE_NAME,"210304","POSITION_QUERY_FAILURE");
        }
    }

    @Override
    public Position findPositionById(Long id){
        try{
            return positionDao.findPositionById(id);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new SystemException(MODULE_NAME,"210304","POSITION_QUERY_FAILURE");
        }
    }

    /**
     * 批量删除职位
     *
     * @param ids 选中的id
     * @return 影响的行数
     */
    @Override
    public Integer batchRemove(Long[] ids) {
        return this.positionDao.batchRemove(ids);
    }

    /**
     * 列出一个公司的所有职位
     *
     * @param companyId 公司id
     * @return 该公司的所有职位
     */
    @Override
    public List<Position> listAllByCompanyId(Long companyId) {
        try{
            PositionQuery positionQuery = new PositionQuery();
            positionQuery.setCompanyId(companyId);
            return positionDao.queryByCondition(positionQuery);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new SystemException(MODULE_NAME,"210304","POSITION_QUERY_FAILURE");
        }
    }

    /**
     * 子类决定如何做增删改过程中 dto到entity对象转化
     * @param dto DTO对象
     * @return 实体类对象
     */
    protected Position doDtoTransf2Entity(PositionDto dto) {
        Position position = new Position();
        copyProperties(dto,position);
        return position;
    }
}
