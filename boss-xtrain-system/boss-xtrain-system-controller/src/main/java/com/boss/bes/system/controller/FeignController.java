package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.util.mail.MailUtil;
import com.boss.bes.system.dto.MailMessageDTO;
import com.boss.bes.system.entity.User;
import com.boss.bes.system.service.UserRoleService;
import com.boss.bes.system.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 供考试模块调用
 * @create 2020-07-11 14:41
 * @since 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("feign")
public class FeignController extends AbstractController {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    /**
     * 模拟调用系统管理服务获得模糊查询的用户ID
     * @param username 参数名为 用户名 模糊查询写法
     * @return 返回值为用户Id和用户名的map
     */
    @PostMapping("getAllPublisher")
    public Map<Long, String> getAllPublisher(String username){
        List<User> users = userService.selectUserByFuzzyUsername(username);
        Map<Long, String> userMap = new HashMap<>(users.size());
        for(User user: users){
            userMap.put(user.getId(), user.getName());
        }
        return userMap;
    }

    /**
     * 调用系统管理微服务获得所有阅卷官
     * @return 返回值为所有阅卷官Id和阅卷官名称
     */
    @PostMapping("getAllReviewer")
    public Map<Long, String> getAllReviewer(){
        List<User> reviewers = userRoleService.queryAllReviewer();
        Map<Long, String> reviewerMap = new HashMap<>(reviewers.size());
        for(User reviewer: reviewers){
            reviewerMap.put(reviewer.getId(), reviewer.getName());
        }
        return reviewerMap;
    }

    /**
     * 给指定的用户发送邮件
     * @param mailMessage 包含用户ID列表和邮件信息的DTO
     * */
    @PostMapping("/sendEmailToUsers")
    public void sendEmailToUser(@RequestBody CommonRequest<MailMessageDTO> mailMessage){
        List<Long> userList = mailMessage.getBody().getUserIds();
        String content = mailMessage.getBody().getContent();
        String title = mailMessage.getBody().getTitle();
        //遍历所有的用户Id查询邮箱信息,发送邮件
        User tempUser;
        for(Long id:userList){
            tempUser = userService.getUserById(id);
            if(tempUser != null && tempUser.getEmail() != null){
                //发送邮件消息
                MailUtil.sentTextEmail(tempUser.getEmail(),content,title);
            }
        }
    }
}