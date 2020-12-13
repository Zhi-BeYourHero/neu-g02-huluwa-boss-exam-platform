package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;

import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.log.api.annotation.ApiLog;
import com.boss.bes.system.BasicConverter;
import com.boss.bes.system.BeanCopierUtil;
import com.boss.bes.system.TreeUtils;
import com.boss.bes.system.dto.ResourceDto;

import com.boss.bes.system.dto.TreeDto;
import com.boss.bes.system.query.ResourceQuery;

import com.boss.bes.system.service.ResourceService;

import com.boss.bes.system.vo.ResourceVo;
import com.boss.bes.system.vo.TreeVo;
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
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-10
 * @description 资源控制类
 * @since 1.0
 */
@Api("资源服务接口")
@ApiLog
@RestController
@CrossOrigin
@RequestMapping("/system/resource")
public class ResourceController extends AbstractController {
    @Resource
    private ResourceService resourceService;

    @PreAuthorize("@ss.hasPermi('resource')")
    @ApiOperation("保存新增的资源对象")
    @PostMapping("/add")
    public CommonResponse<Integer> create(@RequestBody @Valid ResourceDto resourceDto){

        Integer ret = this.resourceService.save(resourceDto);
        return buildCommonResponseSuccess(ret);
    }
    @PreAuthorize("@ss.hasPermi('resource')")
    @ApiOperation("删除资源对象")
    @PostMapping("/delete")
    public CommonResponse<Integer>delete(@RequestBody @Valid ResourceDto resourceDto){

        Integer ret = this.resourceService.remove(resourceDto);
        return buildCommonResponseSuccess(ret);
    }

    @ApiOperation("查询资源路由")
    @PostMapping("/queryRoutes")
    public CommonResponse<List<TreeVo>> doQueryRoutes(){
        List<TreeDto> treeDtos = resourceService.queryRoutes();
        List<TreeVo> treeVos = BeanCopierUtil.copyPropertiesOfList(treeDtos, TreeVo.class, new BasicConverter());
        List<TreeVo> treeList = TreeUtils.getTreeList(treeVos);
        for (TreeVo treeVo : treeList) {
            treeVo.setHidden(false);
        }
        return CommonResponseUtil.success(treeList);
    }
    @PreAuthorize("@ss.hasPermi('resource')")
    @ApiOperation("更新资源对象")
    @PostMapping("/update")
    public CommonResponse<Integer>update(@RequestBody @Valid ResourceDto resourceDto){

        Integer ret = this.resourceService.update(resourceDto);
        return buildCommonResponseSuccess(ret);
    }
    @PreAuthorize("@ss.hasPermi('resource')")
    @ApiOperation("查询资源对象")
    @PostMapping("/query")
    public CommonResponse<List<ResourceVo>>query(@RequestBody @Valid ResourceQuery resourceQuery){
        List<com.boss.bes.system.entity.Resource> returnDto = this.resourceService.query(resourceQuery);
        List<ResourceVo> resourceVo = doObjectTransf(returnDto);
        return buildCommonResponseSuccess(resourceVo);
    }
    @PreAuthorize("@ss.hasPermi('resource')")
    @ApiOperation("查询资源对象")
    @PostMapping("/queryId")
    public CommonResponse<ResourceVo>query(@RequestBody @Valid  Long id){

        ResourceQuery resourceQuery = new ResourceQuery();
        resourceQuery.setId(id);
        List<com.boss.bes.system.entity.Resource> returnDto = this.resourceService.query(resourceQuery);
        List<ResourceVo> resourceVo = doObjectTransf(returnDto);
        ResourceVo resourceVo1 = resourceVo.get(0);
        return buildCommonResponseSuccess(resourceVo1);
    }
    @PreAuthorize("@ss.hasPermi('resource')")
    @ApiOperation("在数据库中获取所有资源信息")
    @PostMapping("/listAll")
    public CommonResponse<CommonPage<ResourceVo>> listAll(@RequestBody @Valid CommonRequest<ResourceQuery> request){
        ResourceQuery resourceQuery = request.getBody();
        Page<Object> page = startPage(resourceQuery.getPageIndex(), resourceQuery.getPageSize());
        List<com.boss.bes.system.entity.Resource> resources = resourceService.listAll();
        List<ResourceVo> resourceVos = doObjectTransf(resources);
        return constructResponseWithPage(page, resourceVos);
    }
    @PreAuthorize("@ss.hasPermi('resource')")
    @ApiOperation("批量删除")
    @PostMapping("/batchDelete")
    public CommonResponse<Integer> batchDelete(@RequestBody @Valid CommonRequest<List<Long>> request){
        List<Long> ids = request.getBody();
        Long[] idsArr = ids.toArray(new Long[]{});
        Integer ret = this.resourceService.batchRemove(idsArr);
        return buildCommonResponseSuccess(ret);
    }
    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<ResourceVo> doObjectTransf(List<com.boss.bes.system.entity.Resource> returnDto) {
        List<ResourceVo> returnVo = new ArrayList<>();
        for (com.boss.bes.system.entity.Resource resource:returnDto){
            returnVo.add(new ResourceVo(resource));
        }
        return returnVo;
    }
}
