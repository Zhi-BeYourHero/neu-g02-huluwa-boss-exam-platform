package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.dto.UserDto;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.query.UserQuery;
import com.boss.bes.system.service.UserOnlineInfoService;
import com.boss.bes.system.service.UserRoleService;
import com.boss.bes.system.service.UserService;
import com.boss.bes.system.vo.UserVo;
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
 * @description 用户的控制器类
 * @create 2020-07-10 23:45
 * @since 1.0
 */

@Api("用户服务接口")
@ApiLog
@RestController
@CrossOrigin
@RequestMapping("/system/user")
public class UserController extends AbstractController {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserOnlineInfoService userOnlineInfoService;

    /**
     * 要求有用户管理的权限
     * @param request 请求
     * @return CommonResponse
     */
    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("保存新增的用户对象")
    @PostMapping("/add")
    public CommonResponse<Integer> create(@RequestBody @Valid CommonRequest<UserDto> request){
        UserDto userDto = request.getBody();
        Integer ret = this.userService.save(userDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("使用Query类查询用户对象")
    @PostMapping("/query")
    public  CommonResponse<CommonPage<UserVo>> query(@RequestBody @Valid CommonRequest<UserQuery> request){
        UserQuery userQuery = request.getBody();
        Page<Object> page = startPage(userQuery.getPageIndex(), userQuery.getPageSize());
        page.setOrderBy("created_time,updated_time desc");
        List<User> returnDto = userService.query(userQuery);
        List<UserVo> userVo = doObjectTransf(returnDto);
        return  constructResponseWithPage(page,userVo);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("在数据库中更新用户对象")
    @PostMapping("/update")
    public  CommonResponse<Integer> update(@RequestBody @Valid CommonRequest<UserDto> request){
        UserDto userDto = request.getBody();
        Integer ret = this.userService.update(userDto);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("在数据库中删除用户对象")
    @PostMapping("/delete")
    public CommonResponse<Integer> delete(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        this.userRoleService.deleteByUserId(id);
        this.userOnlineInfoService.delete(id);
        Integer ret = this.userService.remove(id);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("通过用户Id获取用户名")
    @PostMapping("/getUsernameById")
    public CommonResponse<String> getUsernameById(@RequestBody @Valid CommonRequest<String> request){
        Long id = Long.parseLong(request.getBody());
        String username = this.userService.getUsernameById(id);
        return buildCommonResponseSuccess(username);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("通过用户Id获取用户对象")
    @PostMapping("/getUserById")
    public CommonResponse<UserVo> getUser(@RequestBody @Valid CommonRequest<Long> request){
        Long id = request.getBody();
        User user = this.userService.getUserById(id);
        UserVo userVo = BeanUtil.copyProperties(user,UserVo.class);
        return buildCommonResponseSuccess(userVo);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("向前端传输所有用户(无分页)")
    @PostMapping("/listAll")
    public CommonResponse<List<UserVo>> listAll(){
        List<User> users = this.userService.getUsers();
        List<UserVo> userVos = this.doObjectTransf(users);
        return buildCommonResponseSuccess(userVos);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("批量删除用户")
    @PostMapping("/batchDelete")
    public CommonResponse<Integer> batchDelete(@RequestBody @Valid CommonRequest<List<Long>> request){
        Long[] ids = request.getBody().toArray(new Long[]{});
        Integer ret = this.userService.batchRemove(ids);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("批量添加用户")
    @PostMapping("/batchAdd")
    public CommonResponse<Integer> batchAdd(@RequestBody @Valid CommonRequest<List<UserDto>> request){
        List<UserDto> userDtos = request.getBody();
        Integer ret = this.userService.batchAdd(userDtos);
        return buildCommonResponseSuccess(ret);
    }

    @PreAuthorize("@ss.hasPermi('user')")
    @ApiOperation("带有RoleId的多表查询")
    @PostMapping("/queryWithRoleId")
    public CommonResponse<CommonPage<UserVo>> queryWithRole(@RequestBody @Valid CommonRequest<UserQuery> request){
        UserQuery userQuery = request.getBody();
        Page<Object> page = startPage(userQuery.getPageIndex(), userQuery.getPageSize());
        page.setOrderBy("created_time desc");
        List<User> users = this.userService.queryWithRoleId(userQuery);
        List<UserVo> userVos = this.doObjectTransf(users);
        return constructResponseWithPage(page,userVos);
    }


    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<UserVo> doObjectTransf(List<User> returnDto) {
        List<UserVo> returnVo = new ArrayList<>();
        for(User user: returnDto){
            returnVo.add(BeanUtil.copyProperties(user,UserVo.class));
        }
        return returnVo;
    }

}