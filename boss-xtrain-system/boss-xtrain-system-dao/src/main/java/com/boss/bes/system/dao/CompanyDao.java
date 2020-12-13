package com.boss.bes.system.dao;


import com.boss.bes.system.entity.Company;
import com.boss.bes.system.query.CompanyQuery;


import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @since 1.0
 */
public interface CompanyDao {
    /**
     * 通过id查询公司名字
     * @param id
     * @return
     */
    String selectNameByPrimaryKey(Long id);
    /**
     * 保存新建的公司信息
     *
     * @param company 公司的对象
     * @return 修改的行数
     */
    Integer save(Company company);
    /**
     * 删除公司信息
     *
     * @param company 公司的对象
     * @return 修改的行数
     */
    Integer remove(Company company);
    /**
     * 更新公司信息
     *
     * @param company 公司的对象
     * @return 修改的行数
     */
    Integer update(Company company);

    /**
     *
     * @param query 查询条件
     * @return 查询结果
     */
    List<Company> queryByCondition(CompanyQuery query);
    /**
     * 批量删除公司
     * @param ids 要删除的公司id
     * @return 影响的行数
     */
    Integer batchRemove(Long[] ids);
}
