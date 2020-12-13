package com.boss.bes.paper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-18
 * @since
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/hi")
    public String test(){
        return "test hi~";
    }

}
