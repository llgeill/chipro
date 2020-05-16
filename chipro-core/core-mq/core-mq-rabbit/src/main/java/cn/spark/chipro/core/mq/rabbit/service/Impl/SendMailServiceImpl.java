package cn.spark.chipro.core.mq.rabbit.service.Impl;

import cn.spark.chipro.core.mail.service.MailService;
import cn.spark.chipro.core.mail.vo.MailVo;
import cn.spark.chipro.core.mq.rabbit.constant.RabbitMQConstant;
import cn.spark.chipro.core.mq.rabbit.service.SendMailService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceImpl implements SendMailService {


    @Autowired
    MailService mailService;


    /**
     * 监听queue事件，发送邮箱
     * @param mailVo
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = RabbitMQConstant.CHIPRO_SEND_MAIL_QUEUE)
    public void sendMail(MailVo mailVo) {
        mailService.sendMail(mailVo);
    }
}
