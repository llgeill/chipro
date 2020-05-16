package cn.spark.chipro.core.mq.active.config;


import cn.spark.chipro.core.mail.service.MailService;
import cn.spark.chipro.core.mq.active.constant.ActiveMQConstant;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;


@EnableJms
@Configuration
public class JmsConfig {

    /**
     * 为Queue配置Destination,轮流消费
     * @return
     */
    @Bean
    public Queue queue() {

        return new ActiveMQQueue(ActiveMQConstant.CHIPRO_SEND_MAIL_QUEUE);
    }

    /**
     * 为Topic配置Destination，一起消费
     * @return
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(ActiveMQConstant.CHIPRO_SEND_MAIL_TOPIC);
    }

    /**
     * 为Topic配置containerFactory
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory topicListenerContainer(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();
        topicListenerContainer.setPubSubDomain(true);
        topicListenerContainer.setConnectionFactory(connectionFactory);
        return topicListenerContainer;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public MailService mailService(){
        return new MailService();
    }

}


