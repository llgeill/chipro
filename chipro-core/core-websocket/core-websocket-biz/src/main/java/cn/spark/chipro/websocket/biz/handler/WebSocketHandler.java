package cn.spark.chipro.websocket.biz.handler;


import com.alibaba.fastjson.JSON;
import cn.spark.chipro.websocket.api.model.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import cn.spark.chipro.websocket.biz.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liliguang
 * @description websocket处理器
 * @date 2020-03-21 18:21:59
 */
@Slf4j
@ServerEndpoint("/ws/{userId}")
@Component
public class WebSocketHandler {

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private final static Map<String, WebSocketHandler> sessionMap = new ConcurrentHashMap();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //userId
    private String userId;

    private static MessageMapper messageMapper;

    /**
     * 解决单例问题（每个连接都会创建一个WebSocketHandler对象）
     *
     * @param messageMapper
     */
    @Autowired
    public void setMessageMapper(MessageMapper messageMapper) {
        WebSocketHandler.messageMapper = messageMapper;
    }


    /**
     * 连接建立成功调用的方法
     *
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        sessionMap.put(userId, this);
        try {
            //找出未发送的消息
            List<MessageDTO> messages = messageMapper.selectMessageByUserId(userId);
            //发送消息
            if (messages != null && messages.size() > 0) {
                messages.forEach(e -> {
                    try {
                        sendMessage(e);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        log.error("websocket IO异常,发送消息失败。");
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("用户断开连接 userId:[{}]", userId);
        sessionMap.remove(userId);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自userId:[{}]的信息:[{}]", userId, message);
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {

        this.session.getBasicRemote().sendText(message);
    }


    public void sendMessage(MessageDTO message) throws IOException {
        //主动向前台发送消息
        this.session.getBasicRemote().sendText(JSON.toJSONString(message));
        //数据库更新消息，状态改为已发送
        messageMapper.updateMessageUser(message.getMessageUserId());
    }

    /**
     * 群发自定义消息
     *
     * @param message 消息内容
     * @throws Exception
     */
    public static void sendToAll(String message) throws Exception {

        log.info("推送消息给所有用户推送内容:[{}]", message);

        sessionMap.forEach((k, v) -> {
            try {
                v.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("消息发送失败 userid：[{}],message:[{}]", k, message);
            }
        });

    }

    /**
     * 发送到用户
     *
     * @param message 消息
     * @throws Exception
     */
    public static boolean sendToUser(MessageDTO message) throws Exception {
        //获取需要发送用户的WebSocketHandler对象
        WebSocketHandler webSocketHandler = sessionMap.get(message.getUserId());
        if (webSocketHandler != null) {
            webSocketHandler.sendMessage(message);
            return true;
        }
        return false;
    }
}
