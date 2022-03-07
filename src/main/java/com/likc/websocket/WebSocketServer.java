package com.likc.websocket;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: likc
 * @Date: 2022/02/19/20:36
 * @Description: websocket服务端
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{id}")
public class WebSocketServer {

    public static ObjectMapper objectMapper;

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
    private static AtomicInteger onlineCount = new AtomicInteger();
    // concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    // 接收id
    private String id ="";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("id") String id) {
        this.session = session;
        this.id =id;
        if(webSocketMap.containsKey(id)){
            webSocketMap.remove(id);
            //加入set中
            webSocketMap.put(id,this);

        }else{
            //加入set中
            webSocketMap.put(id,this);
            //在线数加1
            addOnlineCount();
        }
        log.info("userin: {} and onlineCount: {}", id, onlineCount);
        sendMessage("连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(id)){
            webSocketMap.remove(id);
            //从set中删除
            subOnlineCount();
        }
        log.info("userOut: {} and onlineCount: {}", id, onlineCount);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //log.info("用户消息:"+id+",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                // 解析发送的报文
                JsonNode jsonNode = objectMapper.readTree(message);
                // 追加发送人(防止串改)
                ((ObjectNode)jsonNode).put("fromId",this.id);
                // 获取发给谁
                String toId = ((ObjectNode)jsonNode).get("toId").toString();
                //传送给对应toId用户的websocket
                if(StringUtils.isNotBlank(toId)&&webSocketMap.containsKey(toId)){
                    webSocketMap.get(toId).sendMessage(objectMapper.writeValueAsString(((ObjectNode)jsonNode)));
                }else{
                    log.error("toId: {} don't online", toId);
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            }catch (Exception e){
                log.error("inputParams: {} and errorMessage: {}", message, e.getMessage());
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("userId: {} and errorMessage: {}", this.id, error.getMessage());
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("inputParams: {} and errorMessage: {}", message, e.getMessage());
        }
    }


    /**
     * 单发消息
     * */
    public static void sendInfo(String message,@PathParam("id") String id) {
        //log.info("发送消息到:"+id+"，报文:"+message);
        if(StringUtils.isNotBlank(id) && webSocketMap.containsKey(id)){
                webSocketMap.get(id).sendMessage(message);
        }else{
            log.error("userId: {} don't online", id);
        }
    }

    /**
     * 群发消息
     */
    public static void sendAll(String message) throws IOException {
        for (Map.Entry<String, WebSocketServer> webSocketServer : webSocketMap.entrySet()) {
            webSocketServer.getValue().sendMessage(message);
        }
    }


    public static void addOnlineCount() {
        onlineCount.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineCount.decrementAndGet();
    }
}
