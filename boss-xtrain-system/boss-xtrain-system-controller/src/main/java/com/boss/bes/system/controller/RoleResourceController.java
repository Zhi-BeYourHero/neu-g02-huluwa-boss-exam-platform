package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.RoleResourceDto;
import com.boss.bes.system.entity.RoleResource;
import com.boss.bes.system.service.RoleResourceService;
import com.boss.bes.system.vo.RoleResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
 * @description 角色资源多表查询的控制器
 * @create 2020-07-20 10:09
 * @since 1.0
 */
@RequestMapping("/system/roleResource")
@RestController
@CrossOrigin
@Api("控制角色和资源多表查询的控制器类")
@ApiLog
@Slf4j
public class RoleResourceController extends AbstractController {

    @Resource
    private RoleResourceService roleResourceService;

    @ApiOperation("向前端传输所有资源角色中间表对象")
    @RequestMapping("/listAll")
    public CommonResponse<List<RoleResourceVo>> listAll(){
        List<RoleResource> roleResources = this.roleResourceService.listAll();
        List<RoleResourceVo> roleResourceVos = this.doObjectTransf(roleResources);
        return buildCommonResponseSuccess(roleResourceVos);
    }

    @ApiOperation("保存新的资源角色中间表对象")
    @RequestMapping("/add")
    public CommonResponse<Integer> save(@RequestBody @Valid CommonRequest<RoleResourceDto> request){
        RoleResourceDto roleResourceDto = request.getBody();
        RoleResource roleResource = BeanUtil.copyProperties(roleResourceDto, RoleResource.class);
        Integer ret = this.roleResourceService.save(roleResource)?1:0;
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("删除一个RoleId的所有资源角色中间表对象")
    @RequestMapping("/deleteByRoleId")
    public CommonResponse<Integer> delete(@RequestBody @Valid CommonRequest<Long> request){
        Long roleId = request.getBody();
        Integer ret = this.roleResourceService.delete(roleId);
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("获取一个RoleId对应的所有ResourceId")
    @RequestMapping("/getResourceIdsByRoleId")
    public CommonResponse<List<String>> getResourceIdsByRoleId(@RequestBody @Valid CommonRequest<Long> request){
        Long roleId = request.getBody();
        List<Long> resourceIds = this.roleResourceService.getResourceIdsByRoleId(roleId);
        List<String> resourceIdsStr = new ArrayList<>();
        for(Long id: resourceIds){
            resourceIdsStr.add(id+"");
        }
        return buildCommonResponseSuccess(resourceIdsStr);
    }

    @ApiOperation("批量添加RoleId对应的ResourceId")
    @RequestMapping("/batchAdd")
    public CommonResponse<Integer> batchAdd(@RequestBody @Valid CommonRequest<List<RoleResourceDto>> request){
        List<RoleResourceDto> roleResourceDtos = request.getBody();
        Integer ret = this.roleResourceService.saveList(roleResourceDtos);
        return buildCommonResponseSuccess(ret);
    }

    private List<RoleResourceVo> doObjectTransf(List<RoleResource> returnDto) {
        List<RoleResourceVo> returnVo = new ArrayList<>();
        for(RoleResource roleResource: returnDto){
            returnVo.add(BeanUtil.copyProperties(roleResource,RoleResourceVo.class));
        }
        return returnVo;
    }
}