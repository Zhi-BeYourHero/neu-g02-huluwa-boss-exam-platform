package com.boss.bes.system.service;

import boss.xtrain.util.convert.BeanUtil;

import com.boss.bes.system.SystemSpringApplication;

import com.boss.bes.system.dto.OrganizationDto;

import com.boss.bes.system.entity.Organization;
import com.boss.bes.system.query.OrganizationQuery;
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
public class OrganizationServiceTest {
    @Autowired
    OrganizationService organizationService;

    @Test
    public void save() {
        Organization organization = new Organization();
        organization.setId(3L);
        organization.setName("C组织");
        organization.setCode("org_C");
        organization.setMaster("王五");
        organization.setTel("147852963");
        organization.setAddress("沈阳市建设大路15号");
        OrganizationDto organizationDto = BeanUtil.copyProperties(organization,OrganizationDto.class);
        Integer result = organizationService.save(organizationDto);
        log.info(result+"");
    }

    @Test
    public void remove() {
        Organization organization = new Organization();
        organization.setId(3L);
        OrganizationDto organizationDto = BeanUtil.copyProperties(organization,OrganizationDto.class);
        Integer result = organizationService.remove(organizationDto);
        log.info(result+"");
    }

    @Test
    public void update() {
        Organization organization = new Organization();
        organization.setId(2L);
        organization.setAddress("沈阳市建设大路云峰街15号");
        OrganizationDto organizationDto = BeanUtil.copyProperties(organization,OrganizationDto.class);
        Integer result = organizationService.update(organizationDto);
        log.info(result+"");
    }

    @Test
    public void query() {
        OrganizationQuery organizationQuery = new OrganizationQuery();
        organizationQuery.setId(1L);
        List<Organization> organizations= organizationService.query(organizationQuery);
        for (Organization organization: organizations){
            log.info(organization.toString());
        }
    }

}
