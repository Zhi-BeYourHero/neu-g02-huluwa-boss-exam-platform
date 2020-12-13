package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.log.api.annotation.ApiLog;
import com.boss.bes.system.dto.OrganizationDto;
import com.boss.bes.system.entity.Company;
import com.boss.bes.system.entity.Department;
import com.boss.bes.system.entity.Organization;

import com.boss.bes.system.query.OrganizationQuery;
import com.boss.bes.system.service.OrganizationService;
import com.boss.bes.system.vo.CompanyVo;
import com.boss.bes.system.vo.DepartmentVo;
import com.boss.bes.system.vo.OrganizationVo;

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
 * @description 组织机构控制类
 * @since 1.0
 */
@Api("组织机构服务接口")
@ApiLog
@RestController
@CrossOrigin
@RequestMapping("/system/organization")
public class OrganizationController extends AbstractController {
    @Resource
    OrganizationService organizationService;

    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("保存新增的组织机构对象")
    @PostMapping("/add")
    public CommonResponse<Integer> create(@RequestBody @Valid  OrganizationDto organizationDto){

        Integer ret = this.organizationService.save(organizationDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("删除组织机构对象")
    @PostMapping("/delete")
    public CommonResponse<Integer>delete(@RequestBody @Valid  OrganizationDto organizationDto){

        Integer ret = this.organizationService.remove(organizationDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("更新组织机构对象")
    @PostMapping("/update")
    public CommonResponse<Integer>update(@RequestBody @Valid OrganizationDto organizationDto){

        Integer ret = this.organizationService.update(organizationDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("查询组织机构对象")
    @PostMapping("/query")
    public CommonResponse<List<OrganizationVo>>query(@RequestBody @Valid OrganizationQuery organizationQuery){
        List<Organization> returnDto = this.organizationService.query(organizationQuery);
        List<OrganizationVo> organizationVo = doObjectTransf(returnDto);
        return buildCommonResponseSuccess(organizationVo);
    }

    @PreAuthorize("@ss.hasPermiOr('organization','company')")
    @ApiOperation("查询组织机构对象")
    @PostMapping("/queryId")
    public CommonResponse<OrganizationVo>query(@RequestBody @Valid Long id ){

        OrganizationQuery organizationQuery = new OrganizationQuery();
        organizationQuery.setId(id);
        List<Organization> returnDto = this.organizationService.query(organizationQuery);
        List<OrganizationVo> organizationVo = doObjectTransf(returnDto);
        OrganizationVo organizationVo1 = organizationVo.get(0);
        return buildCommonResponseSuccess(organizationVo1);
    }
    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("在数据库中获取所有组织机构信息")
    @PostMapping("/listAll")
    public CommonResponse<CommonPage<OrganizationVo>> listAll(){
        List<Organization> organizations = organizationService.listAll();
        CommonPage<OrganizationVo> commonPage = new CommonPage<>();
        List<OrganizationVo> organizationVos = doObjectTransf(organizations);
        commonPage.setRows(organizationVos);
        commonPage.setTotal(organizations.size());
        return buildCommonResponseSuccess(commonPage);
    }
    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("在数据库中获取所有组织机构名称")
    @PostMapping("/listAllName")
    public CommonResponse<List<OrganizationVo>> listAllName(){
        List<Organization> organizations = organizationService.listAll();
        List<OrganizationVo> organizationVos = new ArrayList<>();
        for (Organization organization:organizations){
            OrganizationVo organizationVo = new OrganizationVo();
            organizationVo.setName(organization.getName());
            organizationVos.add(organizationVo);
        }
        return buildCommonResponseSuccess(organizationVos);
    }
    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("批量删除")
    @PostMapping("/batchDelete")
    public CommonResponse<Integer> batchDelete(@RequestBody @Valid CommonRequest<List<Long>> request){
        List<Long> ids = request.getBody();
        Long[] idsArr = ids.toArray(new Long[]{});
        Integer ret = this.organizationService.batchRemove(idsArr);
        return buildCommonResponseSuccess(ret);
    }
    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("通过Id获取组织机构名")
    @PostMapping("/getOrganizationNameById")
    public CommonResponse<String> selectNameByPrimaryKey(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        String name = organizationService.selectNameByPrimaryKey(id);
        return buildCommonResponseSuccess(name);
    }

    @PreAuthorize("@ss.hasPermi('organization')")
    @ApiOperation("在数据库中获取所有组织机构信息 无分页")
    @PostMapping("/listAllWithoutPage")
    public CommonResponse<List<OrganizationVo>> listAllWithoutPage(){
        List<Organization> organizations = organizationService.listAll();
        List<OrganizationVo> organizationVos = doObjectTransf(organizations);
        return buildCommonResponseSuccess(organizationVos);
    }
    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<OrganizationVo> doObjectTransf(List<Organization> returnDto) {
        List<OrganizationVo> returnVo = new ArrayList<>();
        for (Organization organization:returnDto){
            returnVo.add(new OrganizationVo(organization));
        }
        return returnVo;
    }
}
