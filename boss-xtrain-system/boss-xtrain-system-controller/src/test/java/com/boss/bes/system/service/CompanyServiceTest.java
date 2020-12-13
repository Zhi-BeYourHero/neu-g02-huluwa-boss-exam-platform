package com.boss.bes.system.service;

import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.SystemSpringApplication;
import com.boss.bes.system.dto.CompanyDto;
import com.boss.bes.system.entity.Company;
import com.boss.bes.system.query.CompanyQuery;
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
public class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Test
    public void save() {
        Company company = new Company();
        company.setId(3L);
        company.setOrganizationId(1L);
        company.setName("公司C");
        company.setCode("company_C");
        company.setMnemonicCode("C");
        company.setMaster("hong");
        company.setTax("123546978");
        company.setFax("852147963");
        company.setTel("123456789");
        company.setAddress("沈阳市建设大路15号");
        company.setEmail("213@qq.com");
        company.setWebsite("www.test2.com");
        CompanyDto companyDto = BeanUtil.copyProperties(company,CompanyDto.class);
        Integer result = companyService.save(companyDto);
        log.info(result+"");
    }

    @Test
    public void remove() {
        Company company = new Company();
        company.setId(1284427220416335872L);
        CompanyDto companyDto = BeanUtil.copyProperties(company,CompanyDto.class);
        Integer result = companyService.remove(companyDto);
        log.info(result+"");
    }

    @Test
    public void update() {
        Company company = new Company();
        company.setId(2L);
        company.setAddress("沈阳市建设大路云峰街15号");
        CompanyDto companyDto = BeanUtil.copyProperties(company,CompanyDto.class);
        Integer result = companyService.update(companyDto);
        log.info(result+"");
    }

    @Test
    public void query() {
        CompanyQuery companyQuery = new CompanyQuery();
        companyQuery.setName("陈俞好");
        List<Company>companies = companyService.query(companyQuery);
        for (Company company:companies){
            log.info(company.toString());
        }
    }

    @Test
    public void selectNameByPrimaryKey() {
        String name = companyService.selectNameByPrimaryKey(1L);
        log.info(name);
    }

    @Test
    public void selectByOrganizationId() {
        List<Company>companies = companyService.selectByOrganizationId(1L);
        for (Company company:companies){
            log.info(company.toString());
        }
    }
    @Test
    public void listAll() {
        CompanyQuery companyQuery = new CompanyQuery();
        List<Company>companies = companyService.listAll();
        for (Company company:companies){
            log.info(company.toString());
        }
    }
}