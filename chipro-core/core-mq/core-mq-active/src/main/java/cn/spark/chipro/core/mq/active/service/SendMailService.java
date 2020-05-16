package cn.spark.chipro.core.mq.active.service;

import cn.spark.chipro.core.mail.vo.MailVo;

/**
 * 发送邮件服务
 * @author liliguang
 * @Date 2019-11-23 17:27:
 */
public interface SendMailService {

    void sendMail(MailVo mailVo) throws Exception;
}
