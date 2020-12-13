package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.PositionDto;
import com.boss.bes.system.entity.Position;
import com.boss.bes.system.query.PositionQuery;
import com.boss.bes.system.service.PositionService;
import com.boss.bes.system.vo.PositionVo;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
 * @description 职位的控制器类
 * @create 2020-07-10 23:46
 * @since 1.0
 */
@ApiLog
@Api("职位服务接口")
@RequestMapping("/system/position")
@RestController
@CrossOrigin
@Slf4j
public class PositionController extends AbstractController {

    @Resource
    private PositionService positionService;

    @PreAuthorize("@ss.hasPermi('position')")
    @ApiOperation("在数据库中保存新增的职位信息")
    @PostMapping("/add")
    public CommonResponse<Integer> create(@RequestBody @Valid CommonRequest<PositionDto> request){
        PositionDto positionDto = request.getBody();
        Integer ret = this.positionService.save(positionDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('position')")
    @ApiOperation("在数据库中查询职位信息")
    @PostMapping("/query")
    public  CommonResponse<CommonPage<PositionVo>> query(@RequestBody @Valid CommonRequest<PositionQuery> request){
        PositionQuery positionQuery = request.getBody();
        Page<Object> page = startPage(positionQuery.getPageIndex(),positionQuery.getPageSize());
        page.setOrderBy("created_time DESC");
        List<Position> returnDto = positionService.query(positionQuery);
        List<PositionVo> positionVo = doObjectTransf(returnDto);
        return  constructResponseWithPage(page, positionVo);
    }

    @PreAuthorize("@ss.hasPermi('position')")
    @ApiOperation("在数据库中更新职位信息")
    @PostMapping("/update")
    public  CommonResponse<Integer> update(@RequestBody @Valid CommonRequest<PositionDto> request){
        PositionDto positionDto = request.getBody();
        Integer ret = this.positionService.update(positionDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('position')")
    @ApiOperation("在数据库中删除职位信息")
    @PostMapping("/delete")
    public CommonResponse<Integer> delete(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        Integer ret = this.positionService.remove(id);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('position')")
    @ApiOperation("向前端发送所有职位信息")
    @PostMapping("/listAll")
    public CommonResponse<List<PositionVo>> listAll(){
        List<Position> rows = this.positionService.listAll();
        List<PositionVo> positionVos = this.doObjectTransf(rows);
        return buildCommonResponseSuccess(positionVos);
    }

    @PreAuthorize("@ss.hasPermi('position')")
    @ApiOperation("通过职位id获取职位对象")
    @PostMapping("/getPositionById")
    public CommonResponse<PositionVo> findPositionById(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        Position position = this.positionService.findPositionById(id);
        PositionVo positionVo = BeanUtil.copyProperties(position, PositionVo.class);
        return buildCommonResponseSuccess(positionVo);
    }

    @PreAuthorize("@ss.hasPermi('position')")
    @ApiOperation("批量删除")
    @PostMapping("/batchDelete")
    public CommonResponse<Integer> batchDelete(@RequestBody @Valid CommonRequest<List<Long>> request){
        List<Long> ids = request.getBody();
        Long[] idsArr = ids.toArray(new Long[]{});
        Integer ret = this.positionService.batchRemove(idsArr);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('position')")
    @ApiOperation("查询一个CompanyId的所有职位")
    @PostMapping("/listAllByCompanyId")
    public CommonResponse<CommonPage<PositionVo>> listAllByCompanyId(@RequestBody @Valid CommonRequest<PositionQuery> request){
        PositionQuery positionQuery = request.getBody();
        Long companyId = positionQuery.getCompanyId();
        List<Position> positions = this.positionService.listAllByCompanyId(companyId);
        List<PositionVo> positionVos = this.doObjectTransf(positions);
        Page<Object> page = startPage(positionQuery.getPageIndex(),positionQuery.getPageSize());
        return constructResponseWithPage(page, positionVos);
    }

    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<PositionVo> doObjectTransf(List<Position> returnDto) {
        List<PositionVo> returnVo = new ArrayList<>();
        for(Position position: returnDto){
            returnVo.add(BeanUtil.copyProperties(position,PositionVo.class));
        }
        return returnVo;
    }
}