package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.base.controller.AbstractController;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.log.api.annotation.ApiLog;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.system.BasicConverter;
import com.boss.bes.system.BeanCopierUtil;
import com.boss.bes.system.constant.ErrorCodeConstant;
import com.boss.bes.system.dto.UserOnlineInfoDto;
import com.boss.bes.system.entity.UserOnlineInfo;
import com.boss.bes.system.query.UserOnlineInfoQuery;
import com.boss.bes.system.service.UserOnlineInfoService;
import com.boss.bes.system.vo.UserOnlineInfoVo;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @description 在线用户的控制器类
 * @create 2020-07-10 23:47
 * @since 1.0
 */
@Api("在线用户服务接口")
@ApiLog
@CrossOrigin
@RequestMapping("/system/online")
@RestController
public class UserOnlineInfoController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(UserOnlineInfoController.class);

    @Resource
    private UserOnlineInfoService userOnlineInfoService;


    @ApiOperation("在数据库中查询在线用户信息")
    @PostMapping("query")
    @PreAuthorize("@ss.hasPermi('onlineUser')")
    public CommonResponse<CommonPage<UserOnlineInfoVo>> query(@RequestBody @Valid CommonRequest<UserOnlineInfoQuery> request){
        UserOnlineInfoQuery userOnlineInfoQuery = request.getBody();
        Page<Object> page = startPage(userOnlineInfoQuery.getPageIndex(), userOnlineInfoQuery.getPageSize());
        List<UserOnlineInfo> returnDto = userOnlineInfoService.query(userOnlineInfoQuery);
        List<UserOnlineInfoVo> userOnlineInfoVo = doObjectTransf(returnDto);
        return  constructResponseWithPage(page, userOnlineInfoVo);
    }

    @ApiOperation("获取所有在线用户信息")
    @PostMapping("listAll")
    @PreAuthorize("@ss.hasPermi('onlineUser')")
    public CommonResponse<CommonPage<UserOnlineInfoVo>> listAll(@RequestBody @Valid CommonRequest<UserOnlineInfoQuery> request){
        UserOnlineInfoQuery userOnlineInfoQuery = request.getBody();
        Page<Object> page = startPage(userOnlineInfoQuery.getPageIndex(), userOnlineInfoQuery.getPageSize());
        List<UserOnlineInfo> userOnlineInfos = this.userOnlineInfoService.listAll();
        List<UserOnlineInfoVo> userOnlineInfoVos = this.doObjectTransf(userOnlineInfos);
        return constructResponseWithPage(page, userOnlineInfoVos);
    }

    @ApiOperation("用户登录-新增表数据")
    @PostMapping("/login")
    public CommonResponse<UserOnlineInfoVo> doLogin(@RequestBody @Valid String id) {
        logger.warn("从前端接收的id：{}",id);
        UserOnlineInfoDto userOnlineInfoDto = new UserOnlineInfoDto();
        userOnlineInfoDto.setUserId(Long.valueOf(id));
        userOnlineInfoDto = userOnlineInfoService.login(userOnlineInfoDto);
        UserOnlineInfoVo userOnlineInfoVo = new UserOnlineInfoVo();
        BeanCopierUtil.copy(userOnlineInfoDto, userOnlineInfoVo, new BasicConverter());
        return buildCommonResponseSuccess(userOnlineInfoVo);
    }

    @ApiOperation("用户退出-更新表数据")
    @PostMapping("/logout")
    public CommonResponse<Object> doLogout(@RequestBody @Valid String userOnlineId){
        logger.info("获取用户下线id：{}",userOnlineId);
        UserOnlineInfoDto userOnlineInfoDto = new UserOnlineInfoDto();
        userOnlineInfoDto.setId(Long.valueOf(userOnlineId));
        userOnlineInfoService.logout(userOnlineInfoDto);
        return buildCommonResponseSuccess(null);
    }

    @ApiOperation("强制下线")
    @PostMapping("/forceById")
    @PreAuthorize("@ss.hasPermi('onlineUser')")
    public CommonResponse<Object> doForceById(@RequestBody String id) {
        WebSocket ws = new WebSocket();
        ws.sendMessageTo(CommonResponseUtil.error(ErrorCodeConstant.USER_ONLINE_FORCE_TO_EXIT, ErrorCodeConstant.USER_ONLINE_FORCE_TO_EXIT_MSG),id);
        return buildCommonResponseSuccess(null);
    }

    @ApiOperation("批量强制下线")
    @PostMapping("/forceByIds")
    @PreAuthorize("@ss.hasPermi('onlineUser')")
    public CommonResponse<String> doForceByIds(@RequestBody List<String> ids) {
        WebSocket ws = new WebSocket();
        ws.sendMessageToList(CommonResponseUtil.error(ErrorCodeConstant.USER_ONLINE_FORCE_TO_EXIT, ErrorCodeConstant.USER_ONLINE_FORCE_TO_EXIT_MSG),ids);
        return buildCommonResponseSuccess(null);
    }

    /**
     * @param returnDto service 层返回的dto对象,将dto转为vo
     * @return dto转化为controller 层需要的VO对象
     */
    private List<UserOnlineInfoVo> doObjectTransf(List<UserOnlineInfo> returnDto) {
        List<UserOnlineInfoVo> returnVo = new ArrayList<>();
        for(UserOnlineInfo userOnlineInfo: returnDto){
            returnVo.add(BeanUtil.copyProperties(userOnlineInfo,UserOnlineInfoVo.class));
        }
        return returnVo;
    }
}