package com.boss.bes.system.service;

import com.boss.bes.system.dto.CompanyDto;
import com.boss.bes.system.entity.Company;

import com.boss.bes.system.query.CompanyQuery;

import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 公司的服务接口
 * @create 2020-07-10
 * @since 1.0
 */
public interface CompanyService{

    /**
     * 增加一个公司
     * @param companyDto
     * @return
     */
    Integer save(CompanyDto companyDto);

    /**
     * 删除一个公司
     * @param companyDto
     * @return
     */
    Integer remove(CompanyDto companyDto);

    /**
     * 更新一个公司
     * @param companyDto
     * @return
     */
    Integer update(CompanyDto companyDto);

    /**
     * 按条件查询公司
     * @param companyQuery
     * @return 查询结果
     */
    List<Company> query(CompanyQuery companyQuery);
    /**
     * 按照主键id查询公司的名字
     * @param id
     * @return 查询结果
     */
    String selectNameByPrimaryKey(Long id);

    /**
     * 通过组织机构id查询公司列表
     * @param id 组织机构id
     * @return
     */
    List<Company> selectByOrganizationId(Long id);

    /**
     * 返回所有的公司
     * @return
     */
    List<Company> listAll();
    /**
     * 批量删除公司
     * @param ids 选中的id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}