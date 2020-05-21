package cn.spark.chipro.core.mq.rabbit;

import cn.spark.chipro.core.mail.vo.MailVo;
import cn.spark.chipro.core.mq.rabbit.constant.RabbitMQConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SendServiceTest {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    public void sendMail(){
        MailVo mailVo = new MailVo();
        mailVo.setFrom("13711919653@qq.com");
        mailVo.setTo("903857227@qq.com");
        mailVo.setSubject("MQ消息服务");
        mailVo.setText("测试能否使用MQ服务发送消息");
        rabbitTemplate.convertAndSend(RabbitMQConstant.CHIPRO_SEND_MAIL_QUEUE,mailVo);
    }
}
