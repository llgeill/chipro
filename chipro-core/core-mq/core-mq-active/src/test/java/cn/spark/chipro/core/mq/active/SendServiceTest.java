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
        mailVo.setFrom("973067294@qq.com");
        mailVo.setTo("903857227@qq.com");
        mailVo.setSubject("Redis Desktop Manager For Mac");
        mailVo.setText("Redis 是完全开源免费的，遵守BSD协议，先进的key - value持久化产品。它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型。\n" +
                "redis 是一个高性能的key-value数据库。 redis的出现，很大程度补偿了memcached这类keyvalue存储的不足，在部 分场合可以对关系数据库起到很好的补充作用。它提供了Python，Ruby，Erlang，PHP客户端，使用很方便。问题是这个项目还很新，可能还不足够稳定，而且没有在实际的一些大型系统应用的实例。此外，缺乏mc中批量get也是比较大的问题，始终批量获取跟多次获取的网络开销是不一样的");
        jmsTemplate.convertAndSend(ActiveMQConstant.CHIPRO_SEND_MAIL_QUEUE,mailVo);
    }
}
