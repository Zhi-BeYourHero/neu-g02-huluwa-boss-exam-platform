package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.log.api.annotation.ApiLog;
import com.boss.bes.system.dto.CompanyDto;

import com.boss.bes.system.entity.Company;

import com.boss.bes.system.entity.Organization;
import com.boss.bes.system.query.CompanyQuery;

import com.boss.bes.system.service.CompanyService;
import com.boss.bes.system.vo.CompanyVo;

import com.boss.bes.system.vo.OrganizationVo;
import com.boss.bes.system.vo.PositionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-08
 * @description 公司控制类
 * @since 1.0
 */
@Api("公司服务接口")
@ApiLog
@RestController
@CrossOrigin
@RequestMapping("/system/company")
public class CompanyController extends AbstractController {
    @Resource
    private CompanyService companyService;

    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("保存新增的公司对象")
    @PostMapping("/add")
    public CommonResponse<Integer> create(@RequestBody @Valid CompanyDto companyDto){

        Integer ret = this.companyService.save(companyDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("删除公司对象")
    @PostMapping("/delete")
    public CommonResponse<Integer>delete(@RequestBody @Valid CompanyDto companyDto ){

        Integer ret = this.companyService.remove(companyDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("更新公司对象")
    @PostMapping("/update")
    public CommonResponse<Integer>update(@RequestBody @Valid CompanyDto companyDto){

        Integer ret = this.companyService.update(companyDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermiOr('department','company')")
    @ApiOperation("查询公司对象")
    @PostMapping("/query")
    public CommonResponse<List<CompanyVo>>query(@RequestBody @Valid CompanyQuery companyQuery){

        List<Company> returnDto = this.companyService.query(companyQuery);
        List<CompanyVo> companyVo = doObjectTransf(returnDto);
        return buildCommonResponseSuccess(companyVo);
    }
    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("查询公司对象")
    @PostMapping("/queryId")
    public CommonResponse<CompanyVo>query(@RequestBody @Valid Long id){

        CompanyQuery companyQuery = new CompanyQuery();
        companyQuery.setId(id);
        List<Company> returnDto = this.companyService.query(companyQuery);
        List<CompanyVo> companyVo = doObjectTransf(returnDto);
        CompanyVo companyVo1 = companyVo.get(0);
        return buildCommonResponseSuccess(companyVo1);
    }
    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("通过公司Id获取公司名")
    @PostMapping("/getCompanyNameByCompanyId")
    public CommonResponse<String> selectNameByPrimaryKey(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        String name = companyService.selectNameByPrimaryKey(id);
        return buildCommonResponseSuccess(name);
    }
    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("通过组织机构Id获取公司名")
    @PostMapping("/getCompanyByOrganizationId")
    public CommonResponse<List<CompanyVo>> selectByOrganizationId(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        List<Company> companies = companyService.selectByOrganizationId(id);
        List<CompanyVo> companyVo = doObjectTransf(companies);
        return buildCommonResponseSuccess(companyVo);
    }
    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("在数据库中获取所有公司信息")
    @PostMapping("/listAll")
    public CommonResponse<CommonPage<CompanyVo>> listAll(){
        List<Company> companies = companyService.listAll();
        CommonPage<CompanyVo> commonPage = new CommonPage<>();
        List<CompanyVo> companyVos = doObjectTransf(companies);
        commonPage.setRows(companyVos);
        commonPage.setTotal(companyVos.size());
        return buildCommonResponseSuccess(commonPage);
    }
    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("在数据库中获取所有公司名称")
    @PostMapping("/listAllName")
    public CommonResponse<List<CompanyVo>> listAllName(){
        List<Company> companies = companyService.listAll();
        List<CompanyVo> companyVos = new ArrayList<>();
        for (Company company:companies){
            CompanyVo companyVo = new CompanyVo();
            companyVo.setName(company.getName());
            companyVos.add(companyVo);
        }
        return buildCommonResponseSuccess(companyVos);
    }

    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("在数据库中获取所有公司信息")
    @PostMapping("/listAllWithoutPage")
    public CommonResponse<List<CompanyVo>> listAllWithoutPage(){
        List<Company> companies = companyService.listAll();
        List<CompanyVo> companyVos = doObjectTransf(companies);
        return buildCommonResponseSuccess(companyVos);
    }

    @PreAuthorize("@ss.hasPermi('company')")
    @ApiOperation("批量删除")
    @PostMapping("/batchDelete")
    public CommonResponse<Integer> batchDelete(@RequestBody @Valid CommonRequest<List<Long>> request){
        List<Long> ids = request.getBody();
        Long[] idsArr = ids.toArray(new Long[]{});
        Integer ret = this.companyService.batchRemove(idsArr);
        return buildCommonResponseSuccess(ret);
    }
    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<CompanyVo> doObjectTransf(List<Company> returnDto) {
        List<CompanyVo> returnVo = new ArrayList<>();
        for (Company company: returnDto){
            returnVo.add(new CompanyVo(company));
        }
        return returnVo;
    }

}
