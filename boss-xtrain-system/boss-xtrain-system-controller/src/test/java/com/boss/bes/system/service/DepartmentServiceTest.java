package com.boss.bes.system.service;

import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.SystemSpringApplication;
import com.boss.bes.system.dto.DepartmentDto;
import com.boss.bes.system.entity.Department;
import com.boss.bes.system.query.DepartmentQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
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
public class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void save() {
        Department department = new Department();
        department.setId(2L);
        department.setCompanyId(1L);
        department.setName("部门B");
        department.setMnemonicCode("B");
        department.setCode("department_B");
        department.setLevel("AAA");
        department.setParentId(1L);
        department.setMaster("小明");
        department.setDescript("软件");
        DepartmentDto departmentDto = BeanUtil.copyProperties(department,DepartmentDto.class);
        Integer result = departmentService.save(departmentDto);
        log.info(result+"");
    }

    @Test
    public void remove() {
        Department department = new Department();
        department.setId(1L);
        DepartmentDto departmentDto = BeanUtil.copyProperties(department,DepartmentDto.class);
        Integer result = departmentService.remove(departmentDto);
        log.info(result+"");
    }

    @Test
    public void update() {
        Department department = new Department();
        department.setId(1L);
        department.setDescript("机械");
        DepartmentDto departmentDto = BeanUtil.copyProperties(department,DepartmentDto.class);
        Integer result = departmentService.update(departmentDto);
        log.info(result+"");
    }

    @Test
    public void query() {
        DepartmentQuery departmentQuery = new DepartmentQuery();
        List<Department>departments = departmentService.query(departmentQuery);
        for (Department department:departments){
            log.info(department.toString());
        }
    }
}