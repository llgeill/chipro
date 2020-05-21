package cn.spark.chipro.websocket.biz.mq;


import cn.spark.chipro.websocket.api.model.dto.MessageDTO;
import cn.spark.chipro.websocket.biz.handler.WebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static cn.spark.chipro.websocket.biz.config.RabbitMqConfig.MESSAGE_WEBSOCKET_TOPIC_NAME;

/**
 * @author liligunag
 * @description
 * @date 2020-03-21 16:40:19
 */
@Slf4j
@Component
public class MessageListener {


    /**
     * 监听消息
     *
     * @param msg
     * @throws IOException
     */
    @RabbitListener(queues = MESSAGE_WEBSOCKET_TOPIC_NAME)
    public void receiver(MessageDTO msg) throws Exception {
        WebSocketHandler.sendToUser(msg);
        log.info("jms发送消息，msg:{}", msg);
    }
}
