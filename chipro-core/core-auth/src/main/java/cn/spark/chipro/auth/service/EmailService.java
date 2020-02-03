package cn.spark.chipro.auth.service;

/**
 * @author liliguang
 * @description 邮箱登录
 * @date 2019/6/27 10:40
 */
public interface EmailService {

    /**
     * 发送邮箱验证码
     * @param email 邮箱地址
     */
    void sendEmailCode(String email);
}
