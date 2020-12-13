package com.boss.bes.system.controller;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import com.alibaba.fastjson.JSON;
import com.boss.bes.system.ApplicationContextRegister;
import com.boss.bes.system.constant.ErrorCodeConstant;
import com.boss.bes.system.dto.UserOnlineInfoDto;
import com.boss.bes.system.service.UserOnlineInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 因为WebSocket是类似客户端服务端的形式(采用ws协议)，那么这里的WebSocket其实就相当于一个ws协议的Controller
 * 直接@ServerEndpoint("/imserver/{userId}") 、@Component启用即可，
 * 然后在里面实现@OnOpen开启连接，@onClose关闭连接，@onMessage接收消息等方法。
 * @create 2020-07-20
 * @since
 */
@Component
@ServerEndpoint(value = "/system/user/websocket/{userOnlineInfoId}")
@Slf4j
public class WebSocket {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;

    /** 新建一个ConcurrentHashMap webSocketMap 用于接收当前userId的WebSocket，
     * 方便IM之间对userId进行推送消息。单机版实现到这里就可以。即存放每个客户端对应的MyWebSocket对象
     * */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<>();

    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

    /**接收userOnlineInfoId*/
    private String userOnlineInfoId;

    /**
     * 连接前的操作，判断该用户是否已连接，若已登录则挤退
     */
    public void beforeOpen(){
        for (WebSocket item : clients.values()) {
            if(item.userOnlineInfoId.equals(userOnlineInfoId)){
                sendMessageTo(CommonResponseUtil.error(ErrorCodeConstant.USER_ONLINE_CROWDING_OUT, ErrorCodeConstant.USER_ONLINE_CROWDING_OUT_MSG), userOnlineInfoId);
                onClose();
                break;
            }
        }
    }

    /**
     *
     * @param userOnlineInfoId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userOnlineInfoId") String userOnlineInfoId, Session session){
        this.userOnlineInfoId = userOnlineInfoId;
        this.session = session;
        // 触发在线-登录方法：在线用户表新增一条记录，记录用户信息及上线时间
        beforeOpen();

        addOnlineCount();
        clients.put(userOnlineInfoId, this);
        log.info("{}: 上线，已连接: {}", userOnlineInfoId, getOnlineCount());
    }

    /**
     * 下线前的操作，调用退出登录请求
     * websocket无法使用注解注入bean ,因为每一次websocket的握手连接就像是new了一个对象
     */
    public void beforeClose(){
        UserOnlineInfoDto userOnlineInfoDto = new UserOnlineInfoDto();
        userOnlineInfoDto.setId(Long.parseLong(userOnlineInfoId));

        ApplicationContext act = ApplicationContextRegister.getApplicationContext();
        UserOnlineInfoService userOnlineService = act.getBean(UserOnlineInfoService.class);
        userOnlineService.logout(userOnlineInfoDto);
    }


    @OnClose
    public void onClose(){
        // 触发退出方法：更新在线用户表下线时间及本次在线时长
        beforeClose();
        clients.remove(userOnlineInfoId);
        subOnlineCount();
        log.info("{}: 下线，已连接: {}", userOnlineInfoId, getOnlineCount());
    }

    /**
     * 实现服务器主动推送，单个
     */
    public void sendMessageTo(CommonResponse commonResponse, String to){
        for (WebSocket item : clients.values()) {
            if (item.userOnlineInfoId.equals(to)){
                item.session.getAsyncRemote().sendText(JSON.toJSONString(commonResponse));
                break;
            }
        }
    }

    /**
     * 实现服务器主动推送，批量发送
     */
    public void sendMessageToList(CommonResponse commonResponse, List<String> toList){
        for (WebSocket item : clients.values()) {
            if(toList.contains(item.userOnlineInfoId)){
                item.session.getAsyncRemote().sendText(JSON.toJSONString(commonResponse));
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

}
