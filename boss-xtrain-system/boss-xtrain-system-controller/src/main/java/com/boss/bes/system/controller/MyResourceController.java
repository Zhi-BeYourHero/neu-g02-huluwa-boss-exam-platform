package com.boss.bes.system.controller;

import boss.xtrain.security.domain.LoginUser;
import boss.xtrain.security.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-09
 * @since
 */
@RestController
@CrossOrigin
@RequestMapping("resources")
public class MyResourceController {

    /**
     * 要求有user权限
     * @return
     */
    @PreAuthorize("@ss.hasPermi('/basedata')")
    @GetMapping("/getUser")
    public LoginUser getUser(){
        return SecurityUtils.getLoginUser();
    }

    @GetMapping("/deleteUser")
    public String deleteUser(){
        return "恭喜，成功删除User";
    }


}
