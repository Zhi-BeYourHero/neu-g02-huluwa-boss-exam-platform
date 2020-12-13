package com.boss.bes.system.service;

import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.SystemSpringApplication;
import com.boss.bes.system.dto.ResourceDto;
import com.boss.bes.system.entity.Resource;
import com.boss.bes.system.query.ResourceQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-13
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemSpringApplication.class)
@Slf4j
public class ResourceServiceTest {

    @Autowired
    ResourceService resourceService;
    @Test
    public void save() {
        Resource resource = new Resource();
        resource.setId(3L);
        resource.setTenantName("组织机构管理");
        resource.setCode("003");
        resource.setOrderIndex(3);
        resource.setParentId(0L);
        resource.setUrl("/organization");
        ResourceDto resourceDto = BeanUtil.copyProperties(resource,ResourceDto.class);
        Integer result = resourceService.save(resourceDto);
        log.info(result+"");
    }

    @Test
    public void remove() {
        Resource resource = new Resource();
        resource.setId(3L);
        ResourceDto resourceDto = BeanUtil.copyProperties(resource,ResourceDto.class);
        Integer result = resourceService.remove(resourceDto);
        log.info(result+"");
    }

    @Test
    public void update() {
        Resource resource = new Resource();
        resource.setId(1L);
        resource.setRemark("用户管理");
        ResourceDto resourceDto = BeanUtil.copyProperties(resource,ResourceDto.class);
        Integer result = resourceService.update(resourceDto);
        log.info(result+"");
    }

    @Test
    public void query() {
        ResourceQuery resourceQuery = new ResourceQuery();
        List<Resource>resources = resourceService.query(resourceQuery);
        for (Resource resource:resources){
            log.info(resource.toString());
        }
    }
}