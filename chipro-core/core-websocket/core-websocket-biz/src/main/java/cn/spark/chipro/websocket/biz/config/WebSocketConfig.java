package cn.spark.chipro.websocket.biz.config;


import cn.spark.chipro.websocket.biz.handler.WebSocketHandler;
import cn.spark.chipro.websocket.biz.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author liliguang
 * @description websocket配置类
 * @date 2020-03-22 00:41:59
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
