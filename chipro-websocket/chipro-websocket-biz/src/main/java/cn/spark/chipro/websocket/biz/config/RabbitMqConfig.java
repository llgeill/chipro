package cn.spark.chipro.websocket.biz.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liliguang
 * @description 消息队列配置
 * @date 2019/8/20 17:30
 */
@Configuration
public class RabbitMqConfig {


    public final static String MESSAGE_WEBSOCKET_TOPIC_NAME = "MESSAGE_WEBSOCKET_TOPIC_NAME";

    // 创建一个立即消费队列
    @Bean
    public Queue immediateQueue() {
        // 第一个参数是创建的queue的名字，第二个参数是是否支持持久化
        return new Queue(MESSAGE_WEBSOCKET_TOPIC_NAME, true);
    }

    @Bean
    public DirectExchange immediateExchange() {
        // 一共有三种构造方法，可以只传exchange的名字， 第二种，可以传exchange名字，是否支持持久化，是否可以自动删除，
        //第三种在第二种参数上可以增加Map，Map中可以存放自定义exchange中的参数
        return new DirectExchange(MESSAGE_WEBSOCKET_TOPIC_NAME, true, false);
    }

    @Bean
    //把立即消费的队列和立即消费的exchange绑定在一起
    public Binding immediateBinding() {
        return BindingBuilder.bind(immediateQueue()).to(immediateExchange()).with(MESSAGE_WEBSOCKET_TOPIC_NAME);
    }

}
