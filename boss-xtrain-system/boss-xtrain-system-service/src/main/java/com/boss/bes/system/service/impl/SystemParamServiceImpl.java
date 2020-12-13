package com.boss.bes.system.service.impl;

import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.dao.SystemParamDao;
import com.boss.bes.system.entity.SystemParam;
import com.boss.bes.system.dto.SystemParamDto;
import com.boss.bes.system.query.SystemParamQuery;
import com.boss.bes.system.service.SystemParamService;
import com.boss.bes.system.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 系统参数的服务实现类
 * @create 2020-07-07
 * @since 1.0
 */

@Service
@Slf4j
public class SystemParamServiceImpl implements SystemParamService {

    @Resource
    private SystemParamDao systemParamDao;

    private static final String MODULE_NAME = "SYSTEM_PARAM";
    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);

    /**
     * 保存一个新建的系统参数对象
     *
     * @param systemParamDto 系统参数的DTO对象
     * @return 影响的行数
     */
    @Override
    public Integer save(SystemParamDto systemParamDto){
        try{
            SystemParam entity = this.doDtoTransf2Entity(systemParamDto);
            Long id = this.snowflakeWorker.nextId();
            entity.setId(id);
            entity.setCreatedTime(new Date(System.currentTimeMillis()));
            entity.setVersion(1L);
            return systemParamDao.save(entity);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210801","SYSTEM_PARAM_INSERT_FAILURE");
        }
    }

    /**
     * 删除一个指定的系统参数对象
     *
     * @param systemParamDto 系统参数的Dto对象
     * @return 影响的行数
     */
    @Override
    public Integer remove(SystemParamDto systemParamDto){
        try{
            SystemParam entity = this.doDtoTransf2Entity(systemParamDto);
            return systemParamDao.remove(entity);
        }catch(Exception e){
            throw new SystemException(MODULE_NAME,"210802","SYSTEM_PARAM_DELETE_FAILURE");
        }
    }

    /**
     * 更新一个指定的系统参数对象
     *
     * @param systemParamDto 系统参数的Dto对象
     * @return 影响的行数
     */
    @Override
    public Integer update(SystemParamDto systemParamDto){
        try{
            SystemParam entity = this.doDtoTransf2Entity(systemParamDto);
            entity.setUpdatedTime(new Date(System.currentTimeMillis()));
            Long newVersion = entity.getVersion() + 1;
            entity.setVersion(newVersion);
            return systemParamDao.update(entity);
        }catch(Exception e){
            throw new SystemException(MODULE_NAME,"210803","SYSTEM_PARAM_UPDATE_FAILURE");
        }
    }

    /**
     * 子类决定如何查询以及返回查询对象
     * @param query 封装的查询条件
     * @return 查询的结果
     */
    @Override
    public List<SystemParam> query(SystemParamQuery query){
        try{
            return systemParamDao.queryByCondition(query);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210804","SYSTEM_PARAM_QUERY_FAILURE");
        }
    }

    @Override
    public List<SystemParam> listAll() {
        try{
            SystemParamQuery query = new SystemParamQuery();
            return systemParamDao.queryByCondition(query);
        }catch(Exception e){
            log.error(e.toString());
            throw new SystemException(MODULE_NAME,"210804","SYSTEM_PARAM_QUERY_FAILURE");
        }
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        return systemParamDao.batchRemove(ids);
    }

    /**
     * 子类决定如何做增删改过程中 dto到entity对象转化
     * @param dto DTO对象
     * @return 实体类对象
     */
    protected SystemParam doDtoTransf2Entity(SystemParamDto dto) {
        return new SystemParam(dto);
    }

}
