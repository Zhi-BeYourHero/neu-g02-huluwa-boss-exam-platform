package com.boss.bes.system.service;



import com.boss.bes.system.dto.OrganizationDto;
import com.boss.bes.system.entity.Organization;
import com.boss.bes.system.query.OrganizationQuery;


import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 组织结构的服务接口
 * @create 2020-07-10
 * @since 1.0
 */
public interface OrganizationService{
    /**
     * 增加一个组织机构
     * @param organizationDto
     * @return
     */
    Integer save(OrganizationDto organizationDto);

    /**
     * 删除一个组织机构
     * @param organizationDto
     * @return
     */
    Integer remove(OrganizationDto organizationDto);

    /**
     * 更新一个组织机构
     * @param organizationDto
     * @return
     */
    Integer update(OrganizationDto organizationDto);

    /**
     * 按条件查询组织机构
     * @param organizationQuery
     * @return 查询结果
     */
    List<Organization> query(OrganizationQuery organizationQuery);

    /**
     * 按照主键id查询组织机构的名字
     * @param id
     * @return 查询结果
     */
    String selectNameByPrimaryKey(Long id);
    /**
     * 返回所有的组织机构
     * @return
     */
    List<Organization> listAll();
    /**
     * 批量删除组织
     * @param ids 选中的id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
