package com.boss.bes.system.controller;

import boss.xtrain.core.constant.Constants;
import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.security.domain.BesUserInfo;
import boss.xtrain.security.domain.LoginUser;
import boss.xtrain.security.domain.SysUser;
import boss.xtrain.security.model.UserInfo;
import boss.xtrain.security.util.SecurityUtils;
import boss.xtrain.security.util.StringUtils;
import com.alibaba.nacos.client.utils.IPUtil;
import com.boss.bes.system.IpUtils;
import com.boss.bes.system.service.SysPermissionService;
import com.boss.bes.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Set;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 认证用
 * @create 2020-07-08
 * @since
 */
@RestController
@RequestMapping("user")
@Slf4j
public class AuthenticationController extends AbstractController {

    @Autowired
    UserService userService;

    @Autowired
    SysPermissionService sysPermissionService;

    /**
     * 获取当前用户信息
     */
    @GetMapping("info/{username}")
    public CommonResponse<UserInfo> info( @PathVariable("username") String username){
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser))
        {
            return CommonResponseUtil.error(Constants.FAIL_MESSAGE,"用户名或密码错误");
        }
        //角色集合
        Set<String> roles = sysPermissionService.getRolePermission(sysUser.getId());

        //权限集合
        Set<String> resourcesPermission = sysPermissionService.getResourcePermission(sysUser.getId());
        log.info("资源权限：{}",resourcesPermission);
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        userInfo.setRoles(roles);
        userInfo.setPermissions(resourcesPermission);
        return buildCommonResponseSuccess(userInfo);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("getInfo")
    public CommonResponse<BesUserInfo> getInfo(){
        //获取请求信息
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String remoteAddr = request.getRemoteAddr();
        log.warn("当前访问用户的ip地址：{}",remoteAddr);

        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser sysUser = userService.selectUserByUserName(loginUser.getUsername());
        String companyId = String.valueOf(sysUser.getCompanyId());

        //角色集合
        Set<String> roles = sysPermissionService.getRolePermission(loginUser.getUserId());
        Set<String> orgIds = sysPermissionService.getRoleOrgIds(loginUser.getUserId());
        //权限集合
        Set<String> resourcesPermission = sysPermissionService.getResourcePermission(loginUser.getUserId());
        BesUserInfo besUserInfo = new BesUserInfo(loginUser.getUserId(),loginUser.getUsername(),roles,resourcesPermission);
        besUserInfo.setCompanyId(companyId);
        besUserInfo.setOrgIds(orgIds);
        besUserInfo.setAvatar("");
        return CommonResponseUtil.success(besUserInfo);
    }
}