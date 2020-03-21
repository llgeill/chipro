package cn.spark.chipro.core.mq.active.service.Impl;

import cn.spark.chipro.core.mail.service.MailService;
import cn.spark.chipro.core.mail.vo.MailVo;
import cn.spark.chipro.core.mq.active.constant.ActiveMQConstant;
import cn.spark.chipro.core.mq.active.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
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
    @JmsListener(destination = ActiveMQConstant.CHIPRO_SEND_MAIL_QUEUE)
    public void sendMail(MailVo mailVo) throws Exception {
        mailService.sendMail(mailVo);
    }
}
