package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.UserRoleDto;
import com.boss.bes.system.entity.UserRole;
import com.boss.bes.system.service.UserRoleService;
import com.boss.bes.system.vo.UserRoleVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 角色用户中间表的控制器类
 * @create 2020-07-20 10:18
 * @since 1.0
 */
@RequestMapping("/system/userRole")
@RestController
@CrossOrigin
@Api("控制角色和用户多表查询的控制器类")
@ApiLog
public class UserRoleController extends AbstractController {

    @Resource
    private UserRoleService userRoleService;

    @PreAuthorize("@ss.hasPermiOr('user','role')")
    @ApiOperation("向前端传输所有中间表对象")
    @RequestMapping("/listAll")
    public CommonResponse<List<UserRoleVo>> listAll(){
        List<UserRole> userRoles = this.userRoleService.listAll();
        List<UserRoleVo> userRoleVos = this.doObjectTransf(userRoles);
        return buildCommonResponseSuccess(userRoleVos);
    }

    @PreAuthorize("@ss.hasPermiOr('user','role')")
    @ApiOperation("保存新的中间表对象")
    @RequestMapping("/add")
    public CommonResponse<Integer> save(@RequestBody @Valid CommonRequest<UserRoleDto> request){
        UserRoleDto userRoleDto = request.getBody();
        UserRole userRole = BeanUtil.copyProperties(userRoleDto, UserRole.class);
        Integer ret = this.userRoleService.save(userRole);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermiOr('user','role')")
    @ApiOperation("删除一个UserId的所有中间表对象")
    @RequestMapping("/deleteByUserId")
    public CommonResponse<Integer> deleteByUserId(@RequestBody @Valid CommonRequest<Long> request){
        Long userId = request.getBody();
        Integer ret = this.userRoleService.deleteByUserId(userId);
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("删除一个RoleId所有的中间表对象")
    @RequestMapping("/deleteByRoleId")
    public CommonResponse<Integer> deleteByRoleId(@RequestBody @Valid CommonRequest<Long> request){
        Long roleId = request.getBody();
        Integer ret = this.userRoleService.deleteByRoleId(roleId);
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("获取一个RoleId的所有UserId")
    @RequestMapping("/getUserIdsByRoleId")
    public CommonResponse<List<Long>> getUserIdsByRoleId(@RequestBody @Valid CommonRequest<Long> request){
        Long roleId = request.getBody();
        List<Long> userIds = this.userRoleService.getUserIdsByRoleId(roleId);
        return buildCommonResponseSuccess(userIds);
    }

    @ApiOperation("获取一个UserId的所有RoleId")
    @RequestMapping("/getRoleIdsByUserId")
    public CommonResponse<List<Long>> getRoleIdsByUserId(@RequestBody @Valid CommonRequest<Long> request){
        Long userId = request.getBody();
        List<Long> roleIds = this.userRoleService.getRoleIdsByUserId(userId);
        return buildCommonResponseSuccess(roleIds);
    }

    @ApiOperation("批量添加")
    @RequestMapping("/batchAdd")
    public CommonResponse<Integer> batchAdd(@RequestBody @Valid CommonRequest<List<UserRole>> request){
        List<UserRole> userRoles = request.getBody();
        Integer ret = this.userRoleService.saveList(userRoles);
        return buildCommonResponseSuccess(ret);
    }

    private List<UserRoleVo> doObjectTransf(List<UserRole> returnDto) {
        List<UserRoleVo> returnVo = new ArrayList<>();
        for(UserRole userRole: returnDto){
            returnVo.add(BeanUtil.copyProperties(userRole,UserRoleVo.class));
        }
        return returnVo;
    }
}