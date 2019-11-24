package cn.spark.chipro.core.mq.active;

import cn.spark.chipro.core.mail.vo.MailVo;
import cn.spark.chipro.core.mq.active.constant.ActiveMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SendServiceTest {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;


    @Test
    public void sendMail(){
        MailVo mailVo = new MailVo();
        mailVo.setFrom("13711919653@qq.com");
        mailVo.setTo("903857227@qq.com");
        mailVo.setSubject("MQ消息服务");
        mailVo.setText("测试能否使用MQ服务发送消息");
        jmsTemplate.convertAndSend(ActiveMQConstant.CHIPRO_SEND_MAIL_QUEUE,mailVo);
    }
}
