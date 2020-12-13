package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;

import boss.xtrain.log.api.annotation.ApiLog;
import com.boss.bes.system.dto.DepartmentDto;

import com.boss.bes.system.entity.Department;

import com.boss.bes.system.query.DepartmentQuery;
import com.boss.bes.system.service.DepartmentService;

import com.boss.bes.system.vo.DepartmentVo;
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
 * @create 2020-07-10
 * @description 部门控制类
 * @since 1.0
 */
@Api("部门服务接口")
@ApiLog
@RestController
@CrossOrigin
@RequestMapping("/system/department")
public class DepartmentController extends AbstractController {
    @Resource
    private DepartmentService departmentService;

    @PreAuthorize("@ss.hasPermi('department')")
    @ApiOperation("保存新增的部门对象")
    @PostMapping("/add")
    public CommonResponse<Integer> create(@RequestBody @Valid  DepartmentDto departmentDto){

        Integer ret = this.departmentService.save(departmentDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('department')")
    @ApiOperation("删除部门对象")
    @PostMapping("/delete")
    public CommonResponse<Integer>delete(@RequestBody @Valid  DepartmentDto departmentDto){

        Integer ret = this.departmentService.remove(departmentDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('department')")
    @ApiOperation("更新部门对象")
    @PostMapping("/update")
    public CommonResponse<Integer>update(@RequestBody @Valid  DepartmentDto departmentDto){

        Integer ret = this.departmentService.update(departmentDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('department')")
    @ApiOperation("查询部门对象")
    @PostMapping("/query")
    public CommonResponse<List<DepartmentVo>>query(@RequestBody @Valid DepartmentQuery departmentQuery){
        List<Department> returnDto = this.departmentService.query(departmentQuery);
        List<DepartmentVo> departmentVo = doObjectTransf(returnDto);
        return buildCommonResponseSuccess(departmentVo);
    }
    @PreAuthorize("@ss.hasPermi('department')")
    @ApiOperation("查询部门对象")
    @PostMapping("/queryId")
    public CommonResponse<DepartmentVo>query(@RequestBody @Valid Long id){

        DepartmentQuery departmentQuery = new DepartmentQuery();
        departmentQuery.setId(id);
        List<Department> returnDto = this.departmentService.query(departmentQuery);
        List<DepartmentVo> departmentVos = doObjectTransf(returnDto);
        DepartmentVo departmentVo = departmentVos.get(0);
        return buildCommonResponseSuccess(departmentVo);
    }
    @PreAuthorize("@ss.hasPermi('department')")
    @ApiOperation("在数据库中获取所有部门信息")
    @PostMapping("/listAll")
    public CommonResponse<CommonPage<DepartmentVo>> listAll(){
        List<Department> departments = departmentService.listAll();
        CommonPage<DepartmentVo> commonPage = new CommonPage<>();
        List<DepartmentVo> departmentVos = doObjectTransf(departments);
        commonPage.setRows(departmentVos);
        commonPage.setTotal(departments.size());
        return buildCommonResponseSuccess(commonPage);
    }
    @PreAuthorize("@ss.hasPermi('department')")
    @ApiOperation("在数据库中获取所有部门信息")
    @PostMapping("/listAllWithoutPage")
    public CommonResponse<List<DepartmentVo>> listAllWithoutPage(){
        List<Department> departments = departmentService.listAll();
        List<DepartmentVo> departmentVos = doObjectTransf(departments);
        return buildCommonResponseSuccess(departmentVos);
    }
    @PreAuthorize("@ss.hasPermi('department')")
    @ApiOperation("批量删除")
    @PostMapping("/batchDelete")
    public CommonResponse<Integer> batchDelete(@RequestBody @Valid CommonRequest<List<Long>> request){
        List<Long> ids = request.getBody();
        Long[] idsArr = ids.toArray(new Long[]{});
        Integer ret = this.departmentService.batchRemove(idsArr);
        return buildCommonResponseSuccess(ret);
    }
    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<DepartmentVo> doObjectTransf(List<Department> returnDto) {
        List<DepartmentVo> returnVo = new ArrayList<>();
        for (Department department:returnDto){
            returnVo.add(new DepartmentVo(department));
        }
        return returnVo;
    }
}
