package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.RoleDto;
import com.boss.bes.system.entity.Role;
import com.boss.bes.system.query.RoleQuery;
import com.boss.bes.system.service.RoleResourceService;
import com.boss.bes.system.service.RoleService;
import com.boss.bes.system.vo.RoleVo;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色的控制器类
 * @create 2020-07-10 23:45
 * @since 1.0
 */
@ApiLog
@Api("用户角色服务接口")
@RestController
@CrossOrigin
@RequestMapping("/system/role")
public class RoleController extends AbstractController {

    @Resource
    private RoleService roleService;

    @Resource
    private RoleResourceService roleResourceService;

    @ApiOperation("在数据库中保存新增的角色信息")
    @PostMapping("/add")
    @PreAuthorize("@ss.hasPermi('role')")
    public CommonResponse<Integer> create(@RequestBody @Valid CommonRequest<RoleDto> request){
        RoleDto roleDto = request.getBody();
        Integer ret = this.roleService.save(roleDto);
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("在数据库中查询角色信息")
    @PostMapping("/query")
    @PreAuthorize("@ss.hasPermi('role')")
    public  CommonResponse<CommonPage<RoleVo>> query(@RequestBody @Valid CommonRequest<RoleQuery> request){
        RoleQuery roleQuery = request.getBody();
        Page<Object> page = startPage(roleQuery.getPageIndex(),roleQuery.getPageSize());
        page.setOrderBy("created_time desc");
        List<Role> returnDto = roleService.query(roleQuery);
        List<RoleVo> roleVo = doObjectTransf(returnDto);

        return  constructResponseWithPage(page, roleVo);
    }

    @ApiOperation("在数据库中更新角色信息")
    @PostMapping("/update")
    @PreAuthorize("@ss.hasPermi('role')")
    public  CommonResponse<Integer> update(@RequestBody @Valid CommonRequest<RoleDto> request){
        RoleDto roleDto = request.getBody();
        Integer ret = this.roleService.update(roleDto);
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("在数据库中删除角色信息")
    @PostMapping("/delete")
    @PreAuthorize("@ss.hasPermi('role')")
    public CommonResponse<Integer> delete(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        Integer ret = this.roleService.remove(id);
        this.roleResourceService.delete(id);
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("从数据库中获取全部角色信息(无分页)")
    @PostMapping("/listAll")
    @PreAuthorize("@ss.hasPermi('role')")
    public CommonResponse<List<RoleVo>> listAll(){
        List<Role> roles = this.roleService.listAll();
        List<RoleVo> roleVos = this.doObjectTransf(roles);
        return buildCommonResponseSuccess(roleVos);
    }

    @ApiOperation("批量删除角色")
    @PostMapping("/batchDelete")
    @PreAuthorize("@ss.hasPermi('role')")
    public CommonResponse<Integer> batchDelete(@RequestBody @Valid CommonRequest<List<Long>> request){
        Long[] ids = request.getBody().toArray(new Long[]{});
        Integer ret = this.roleService.batchRemove(ids);
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("通过角色id获取角色对象")
    @PostMapping("/getRoleById")
    @PreAuthorize("@ss.hasPermi('role')")
    public CommonResponse<RoleVo> getRoleById(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        Role role = this.roleService.getRoleById(id);
        RoleVo roleVo = BeanUtil.copyProperties(role, RoleVo.class);
        return buildCommonResponseSuccess(roleVo);
    }

    @ApiOperation("模糊查询角色对象")
    @PostMapping("/fuzzyQuery")
    @PreAuthorize("@ss.hasPermi('role')")
    public CommonResponse<CommonPage<RoleVo>> getRoleByFuzzyName(@RequestBody @Valid CommonRequest<RoleQuery> request){
        RoleQuery roleQuery = request.getBody();
        List<Role> roles = this.roleService.getRoleByFuzzyName(roleQuery);
        List<RoleVo> roleVos = this.doObjectTransf(roles);
        Page<Object> page = startPage(roleQuery.getPageIndex(),roleQuery.getPageSize());
        return constructResponseWithPage(page, roleVos);
    }

    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<RoleVo> doObjectTransf(List<Role> returnDto) {
        List<RoleVo> returnVo = new ArrayList<>();
        for(Role role: returnDto){
            returnVo.add(BeanUtil.copyProperties(role,RoleVo.class));
        }
        return returnVo;
    }

}