package com.boss.bes.system.service.impl;

import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.system.SystemException;
import com.boss.bes.system.TypeConverterUtil;
import com.boss.bes.system.constant.CommonField;
import com.boss.bes.system.dao.ResourceDao;
import com.boss.bes.system.dao.RoleResourceDao;
import com.boss.bes.system.dto.ResourceDto;
import com.boss.bes.system.dto.TreeDto;
import com.boss.bes.system.entity.ResourceMeta;
import com.boss.bes.system.mapper.ResourceMapper;
import com.boss.bes.system.query.ResourceQuery;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.boss.bes.system.service.ResourceService;
import boss.xtrain.security.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;


import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService{

    private static final String MODULE_NAME = "RESOURCE";
    private SnowflakeWorker snowflakeWorker = new SnowflakeWorker(1L,1L,0L);
    @Resource
    private ResourceDao resourceDao;

    @Resource
    private ResourceMapper resourceMapper;

    @Resource
    private RoleResourceDao roleResourceDao;

    @Override
    public Set<String> selectResourcePermsByUserId(Long userId) {
        List<String> perms = resourceMapper.selectResourcePermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public Integer save(ResourceDto resourceDto) throws SystemException{
        try{
            //将dto转换成entity
            com.boss.bes.system.entity.Resource resource = new com.boss.bes.system.entity.Resource(resourceDto);
            Long id = this.snowflakeWorker.nextId();
            resource.setId(id);
            resource.setCreatedTime(new Date(System.currentTimeMillis()));
            resource.setVersion(1L);
            return resourceDao.save(resource);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210601","RESOURCE_INSERT_FAILURE");
        }
    }

    @Override
    public Integer remove(ResourceDto resourceDto) throws SystemException{
        try{
            //将dto转换成entity
            com.boss.bes.system.entity.Resource resource = new com.boss.bes.system.entity.Resource(resourceDto);
            return resourceDao.remove(resource);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210602","RESOURCE_DELETE_FAILURE");
        }
    }

    @Override
    public Integer update(ResourceDto resourceDto) throws SystemException{
        try{
            //将dto转换成entity
            com.boss.bes.system.entity.Resource resource = new com.boss.bes.system.entity.Resource(resourceDto);
            resource.setUpdatedTime(new Date(System.currentTimeMillis()));
            Long newVersion = resource.getVersion() + 1;
            resource.setVersion(newVersion);
            return resourceDao.update(resource);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210604","RESOURCE_UPDATE_FAILURE");
        }
    }

    @Override
    public List<com.boss.bes.system.entity.Resource> query(ResourceQuery resourceQuery) throws SystemException{
        try{
            return resourceDao.queryByCondition(resourceQuery);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210603","RESOURCE_QUERY_FAILURE");
        }
    }

    @Override
    public List<com.boss.bes.system.entity.Resource> listAll() throws SystemException{
        try{
            ResourceQuery resourceQuery = new ResourceQuery();
            return resourceDao.queryByCondition(resourceQuery);
        }catch (Exception e){
            throw new SystemException(MODULE_NAME,"210603","RESOURCE_QUERY_FAILURE");
        }
    }

    @Override
    public List<TreeDto> queryRoutes() {
        WeekendSqls<com.boss.bes.system.entity.Resource> sqls = WeekendSqls.custom();
        sqls = sqls.andEqualTo(com.boss.bes.system.entity.Resource::getResourceType, 1);

        Example example = Example.builder(com.boss.bes.system.entity.Resource.class)
                .where(sqls)
                .orderByAsc(CommonField.ORDER_BY_INDEX)
                .build();
        List<ResourceDto> resourceDtos = resourceDao.queryResource(example);
        return fillTree(resourceDtos);
    }

    @Override
    public Integer batchRemove(Long[] ids) {
        return resourceDao.batchRemove(ids);
    }

    private List<TreeDto> fillTree(List<ResourceDto> resourceDtos){
        List<TreeDto> treeDtos = new ArrayList<>();

        for(ResourceDto resourceDto : resourceDtos){
            TreeDto treeDto = new TreeDto();
            treeDto.setPath(resourceDto.getUrl());
            treeDto.setComponent(resourceDto.getUrl());
            treeDto.setOrderIndex(resourceDto.getOrderIndex());
            if(resourceDto.getUrl().substring(0,1).equals("/")) {
                treeDto.setName(resourceDto.getUrl().substring(1,2).toUpperCase() + resourceDto.getUrl().substring(2));
            }else{
                treeDto.setName(resourceDto.getUrl().substring(0,1).toUpperCase() + resourceDto.getUrl().substring(1));
            }

            ResourceMeta resourceMeta = new ResourceMeta();
            resourceMeta.setTitle(resourceDto.getTenantName());
            resourceMeta.setIcon(resourceDto.getCloseImg());
            resourceMeta.setCloseImg(resourceDto.getCloseImg());
            resourceMeta.setOpenImg(resourceDto.getOpenImg());
            //通过资源id查询对应的角色id
            List<Long> roles = roleResourceDao.queryRoleIdsByResourceId(resourceDto.getId());
            resourceMeta.setRoles(TypeConverterUtil.listLongToListString(roles));
            treeDto.setMeta(resourceMeta);

            treeDto.setId(resourceDto.getId());
            if(StringUtils.isNotEmpty(String.valueOf(resourceDto.getParentId()))){
                treeDto.setParentId(resourceDto.getParentId());
            }
            treeDtos.add(treeDto);
        }
        return treeDtos;
    }

}
